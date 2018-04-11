package com.boreas.ui.activity;


import android.app.Service;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.RemoteException;
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
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.boreas.App;
import com.boreas.Constants;
import com.boreas.IMusicPlayer;
import com.boreas.R;
import com.boreas.base.BaseActivity;
import com.boreas.databinding.ActivityMainBinding;
import com.boreas.databinding.NavHeaderMainBinding;
import com.boreas.di.componects.DaggerMainComponent;
import com.boreas.di.modules.MainModule;
import com.boreas.presenter.MainPresenter;
import com.boreas.presenter.PresenterContract;
import com.boreas.service.MusicService;
import com.boreas.ui.fragment.MusicFragment;
import com.boreas.ui.fragment.PicFragment;
import com.boreas.ui.notification.MusicNotification;
import com.orhanobut.logger.Logger;

import javax.inject.Inject;

/**
 * @author boreas
 */
@RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
public class MainActivity extends BaseActivity
        implements NavigationView.OnNavigationItemSelectedListener,
        PresenterContract.MainView {

    private ActivityMainBinding binding;
    private NavHeaderMainBinding navHeadBinding;
    private String currentFragmentTag = null;
    private FragmentManager fragmentManager = null;

    @Inject
    MainPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        fragmentManager = getSupportFragmentManager();
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        initComponent();
        initPresenter();
        initView();
    }

    private void initPresenter() {

    }

    private void initComponent() {
        DaggerMainComponent.builder().mainModule(new MainModule(App.getContext())).build().inject(this);
    }

    private void initView() {
        setSupportActionBar(binding.toolbar);
        binding.fab.setOnClickListener(
                view ->
                        Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                                .setAction("Action", null)
                                .show());
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, binding.drawerLayout, binding.toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        this.binding.drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        binding.navView.setNavigationItemSelectedListener(this);
        switchFragment(Constants.MUSIC);
        View headView = binding.navView.getHeaderView(0);
        navHeadBinding =  DataBindingUtil.bind(headView);
    }

    @Override
    protected void onStart() {
        super.onStart();
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
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.destroy();
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
        } else if (id == R.id.nav_share) {
            this.switchFragment(Constants.SHARE);
        } else if (id == R.id.nav_send) {
        } else if (id == R.id.nav_them) {
        }
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
            } else {
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

}
