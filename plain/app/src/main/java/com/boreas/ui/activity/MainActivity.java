package com.boreas.ui.activity;


import android.annotation.SuppressLint;
import android.app.Service;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.databinding.DataBindingUtil;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.design.widget.Snackbar;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;


import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;
import com.boreas.App;
import com.boreas.Constants;
import com.boreas.IMusicPlayer;
import com.boreas.R;
import com.boreas.base.BaseActivity;
import com.boreas.databinding.ActivityMainBinding;
import com.boreas.databinding.ChooseHeadImgBinding;
import com.boreas.databinding.NavHeaderMainBinding;
import com.boreas.di.componects.DaggerMainComponent;
import com.boreas.di.modules.MainModule;
import com.boreas.presenter.MainPresenter;
import com.boreas.presenter.PresenterContract;
import com.boreas.service.MusicService;
import com.boreas.ui.fragment.MusicFragment;
import com.boreas.ui.fragment.PicFragment;
import com.boreas.ui.notification.MusicNotification;
import com.example.lyf.yflibrary.Permission;
import com.example.lyf.yflibrary.PermissionResult;
import com.orhanobut.logger.Logger;

import javax.inject.Inject;

import static com.boreas.Constants.REQUEST_PERMISSIONS;

/**
 * @author boreas
 */
@RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
public class MainActivity extends BaseActivity
        implements NavigationView.OnNavigationItemSelectedListener,
        PresenterContract.MainView,View.OnClickListener{

    private static final String TAG = MainActivity.class.getSimpleName();
    private ActivityMainBinding binding;
    private NavHeaderMainBinding navHeadBinding;
    private String currentFragmentTag = null;
    private FragmentManager fragmentManager = null;

    public static boolean linkSuccess;
    int isCurrentRunningForeground;
    private IMusicPlayer mMusicPlayerService;
    private MusicNotification mMusicNotification;
    public static MainActivity main;
    @Inject
    MainPresenter presenter;

    public AMapLocationClient mLocationClient = null;
    public AMapLocationClientOption mLocationOption = null;
    private AlertDialog alertDialog;

    private static final int CHOOSE_PICTURE = 3333;
    private static final int TAKE_PICTURE = 7777;
    private static final int CROP_SMALL_PICTURE = 8888;

    private Uri tempImgUri = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        main = this;
        fragmentManager = getSupportFragmentManager();
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        initComponent();
        initPresenter();
        initView();
        checkPermissions();
    }

    private void checkPermissions() {
        Permission.checkPermisson(this, REQUEST_PERMISSIONS, new PermissionResult() {
            @Override
            public void success() {
                System.out.printf("成功");
                initLocation();
            }

            @Override
            public void fail() {
                //失败
                System.out.printf("失败");
                Log.d(TAG, "失败");
            }
        });
    }



    private void initPresenter() {

    }

    private void initComponent() {
        DaggerMainComponent.builder().mainModule(new MainModule(App.getContext())).build().inject(this);
    }

    private void initView() {
        setSupportActionBar(binding.toolbar);
        binding.fab.setOnClickListener(this);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, binding.drawerLayout, binding.toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        this.binding.drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        binding.navView.setNavigationItemSelectedListener(this);
        switchFragment(Constants.MUSIC);
        View headView = binding.navView.getHeaderView(0);
        navHeadBinding = DataBindingUtil.bind(headView);
        navHeadBinding.navHeadImageView.setOnClickListener(this);
        navHeadBinding.navHeadAuthor.setOnClickListener(this);
        navHeadBinding.navHeadMotto.setOnClickListener(this);
        bindService();
        binding.fab.setVisibility(View.INVISIBLE);
    }

    /**
     * Service
     **/

    public IMusicPlayer getMusicPlayerService() {
        return mMusicPlayerService;
    }

    private void bindService() {
        Intent intent = new Intent(App.app, MusicService.class);
        bindService(intent, mServiceConnection, Service.BIND_AUTO_CREATE);
    }

    ServiceConnection mServiceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            mMusicPlayerService = IMusicPlayer.Stub.asInterface(service);
            linkSuccess = true;
            Logger.d("onServiceConnected   Service 连接成功！");
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            bindService();
            Logger.d("onServiceConnected   Service 连接失败！");
        }
    };

    /**
     * Service
     **/

    @Override
    protected void onStart() {
        super.onStart();
        if (isCurrentRunningForeground == 0) {// front
            if (mMusicNotification != null) {
                mMusicNotification.unregisterListener();
            }
        }
        isCurrentRunningForeground++;
    }

    @Override
    protected void onResume() {
        super.onResume();
        presenter.resume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        presenter.pause();
    }

    @Override
    protected void onStop() {
        super.onStop();
        isCurrentRunningForeground--;
        if (isCurrentRunningForeground == 0) { // back
            try {
                showNotification();
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
    }


    private void showNotification() throws RemoteException {
        if (mMusicNotification == null) {
            mMusicNotification = new MusicNotification(this, mMusicPlayerService);
        }
        mMusicNotification.registerListener();
        mMusicNotification.notifyMusic();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.destroy();
        if (mServiceConnection != null) {
            unbindService(mServiceConnection);
            mServiceConnection = null;
            linkSuccess = false;
        }


    }

    @Override
    public void onBackPressed() {
        if (binding.drawerLayout.isDrawerOpen(GravityCompat.START)) {
            binding.drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            Intent intent = new Intent();
            intent.setAction(Intent.ACTION_MAIN);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            intent.addCategory(Intent.CATEGORY_HOME);
            startActivity(intent);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            showChooseHeadImgDialog();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.nav_music) {
            this.switchFragment(Constants.MUSIC);
        } else if (id == R.id.nav_pic) {
            this.switchFragment(Constants.PIC);
        } else if (id == R.id.nav_weather) {
            this.switchFragment(Constants.WEATHER);
        }else if (id == R.id.nav_share) {
            this.switchFragment(Constants.SHARE);
        } else if (id == R.id.nav_send) {
        }
//        else if (id == R.id.nav_them) {
//        }
        binding.drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }

    private void switchFragment(String name) {
        if (currentFragmentTag != null && currentFragmentTag.equals(name)) {
            return;
        }
        FragmentTransaction ft = fragmentManager.beginTransaction();
        ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_CLOSE);
        Fragment currentFragment = fragmentManager.findFragmentByTag(currentFragmentTag);
        if (currentFragment != null) {
            ft.hide(currentFragment);
        }

        Fragment foundFragment = fragmentManager.findFragmentByTag(name);
        if (foundFragment == null) {
            if (name.equals(Constants.MUSIC)) {
                foundFragment = new MusicFragment();
            } else if (name.equals(Constants.PIC)) {
                foundFragment = new PicFragment();
            } else if(name.equals(Constants.WEATHER)){

            }
        }
        if (foundFragment == null) {

        } else if (foundFragment.isAdded()) {
            ft.show(foundFragment);
        } else {
            ft.add(R.id.container, foundFragment, name);
        }
        ft.commit();
        currentFragmentTag = name;
    }

    private void initLocation(){
        mLocationOption = new AMapLocationClientOption();
        mLocationOption.setLocationPurpose(AMapLocationClientOption.AMapLocationPurpose.SignIn);
        mLocationOption.setLocationMode(AMapLocationClientOption.AMapLocationMode.Hight_Accuracy);
        mLocationOption.setOnceLocation(true);
        mLocationOption.setOnceLocationLatest(true);
        mLocationOption.setInterval(5000);
        mLocationOption.setNeedAddress(true);
        mLocationOption.setMockEnable(true);
        mLocationOption.setHttpTimeOut(10000);
        mLocationOption.setLocationCacheEnable(false);
        mLocationClient = new AMapLocationClient(getApplicationContext());
        mLocationClient.setLocationListener(aMapLocationListener);

        if (null != mLocationClient) {
            mLocationClient.setLocationOption(mLocationOption);
            //设置场景模式后最好调用一次stop，再调用start以保证场景模式生效
            mLocationClient.stopLocation();
            mLocationClient.startLocation();
        }
    }

    private AMapLocationListener aMapLocationListener = new AMapLocationListener() {
        @Override
        public void onLocationChanged(AMapLocation aMapLocation) {
            if (aMapLocation != null) {
                if (aMapLocation.getErrorCode() == 0) {
                    Logger.d("定位:" + aMapLocation.getAddress());
                } else {
                    Log.e("AmapError", "location Error, ErrCode:"
                            + aMapLocation.getErrorCode() + ", errInfo:"
                            + aMapLocation.getErrorInfo());
                }
            }
        }
    };

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.fab:
                break;
            case R.id.nav_head_author:
                break;
            case R.id.nav_head_imageView:
                showChooseHeadImgDialog();
                break;
            case R.id.nav_head_motto:
                break;
        }
    }

    private void showChooseHeadImgDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        View headImgView = View.inflate(this,R.layout.choose_head_img,null);
        ChooseHeadImgBinding chooseHeadImgBinding = DataBindingUtil.bind(headImgView);
        chooseHeadImgBinding.takePicture.setOnClickListener(view->{

        });
        chooseHeadImgBinding.photoAlbum.setOnClickListener(view->{
            Intent intent = new Intent(this,ChoosePicsActivity.class);
            startActivityForResult(intent,CHOOSE_PICTURE);
            this.alertDialog.dismiss();
        });
        chooseHeadImgBinding.cancel.setOnClickListener(view->{
            if(alertDialog != null){
                if(alertDialog.isShowing()){
                    alertDialog.dismiss();
                    alertDialog = null;
                }
            }
        });
        builder.setView(headImgView);
        builder.setCancelable(true);
        alertDialog = builder.create();
        alertDialog.show();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode==RESULT_OK){
            switch (requestCode){
                case CHOOSE_PICTURE: //选择图片
                    
                    break;
                case TAKE_PICTURE:
                    chooseImaes(data.getData());
                    break;
                case CROP_SMALL_PICTURE:
                    if(data != null) {
                        setImageToView(data);
                    }
                    break;
            }

        }
    }

    private void  setImageToView(Intent data) {
        Bundle extras = data.getExtras();
        if(extras != null){
            Bitmap bitmap = extras.getParcelable("");
            this.navHeadBinding.navHeadImageView.setImageBitmap(bitmap);
        }
    }

    private void chooseImaes(Uri uri) {
        if(uri == null){
            Log.d(TAG,"tempImgUri 地址为空");
        }
        tempImgUri = uri;
        Intent intent = new Intent("com.android.camera.action.CROP");
        intent.setDataAndType(uri,"image/*");
        intent.putExtra("crop","true");
        intent.putExtra("aspetX",1);
        intent.putExtra("aspectY",1);
        intent.putExtra("outputX",150);
        intent.putExtra("outputY",150);
        intent.putExtra("return-data",true);
        startActivityForResult(intent,CROP_SMALL_PICTURE);
    }
}
