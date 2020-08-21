package com.boreas.plainlife.mvp.views.activitys;

import android.content.res.Configuration;
import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;

import com.boreas.plainlife.App;
import com.boreas.plainlife.Constant;
import com.boreas.plainlife.R;
import com.boreas.plainlife.base.BaseActivity;
import com.boreas.plainlife.base.BaseFragment;
import com.boreas.plainlife.base.BaseResponse;
import com.boreas.plainlife.databinding.ActivityMainBinding;
import com.boreas.plainlife.internal.components.DaggerMainActivityComponent;
import com.boreas.plainlife.internal.modules.MainActivityModule;
import com.boreas.plainlife.mq.RabbitMQConfiguration;
import com.boreas.plainlife.mq.ResqonCallBack;
import com.boreas.plainlife.mvp.presenters.presenterimpl.MainActivityPresenter;
import com.boreas.plainlife.mvp.views.viewinterfaces.MainActivityInterface;
import com.boreas.plainlife.utils.RxTimer;
import com.boreas.plainlife.utils.ToastUtil;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.snackbar.Snackbar;
import com.lzf.easyfloat.EasyFloat;
import com.orhanobut.logger.Logger;

import java.util.ArrayList;

import javax.inject.Inject;

public class MainActivity extends BaseActivity<ActivityMainBinding> implements MainActivityInterface {

    private ActionBarDrawerToggle mDrawerToggle;

    @Inject
    MainActivityPresenter presenter;
    private ArrayList<BaseFragment> fragments;
    private RabbitMQConfiguration rabbitMQConfiguration;

    @Override
    public int setContentView() {
        return R.layout.activity_main;
    }

    @Override
    protected void initView() {
        showBackIcon(true);
        super.setSwipeBackEnable(false);
        this.binding.drawerLayout.setClipToPadding(false);
        mDrawerToggle = new ActionBarDrawerToggle(this, this.binding.drawerLayout, getToolbar(), R.string.drawer_open, R.string.drawer_close);
        mDrawerToggle.syncState();
        this.binding.drawerLayout.addDrawerListener(mDrawerToggle);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        initFloatBall();
    }

    @Override
    protected void initListener() {
        this.binding.navigationView.setNavigationItemSelectedListener(menuItem -> {
            switch (menuItem.getItemId()) {
//                case R.id.menu_github:
//                    openMyGitHub();
//                    return true;
                default:
                    Snackbar.make(this.binding.container, "click menu:" + menuItem.getTitle(), Snackbar.LENGTH_SHORT).show();
                    break;
            }
            return false;
        });
    }
    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        mDrawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        mDrawerToggle.onConfigurationChanged(newConfig);
    }

    private void initFloatBall() {
//        EasyFloat.with(this)
//                .setFilter(ShoppingActivity.class,
//                        SubmitApplyActivity.class, SubmitApplyOperationActivity.class)
//                // 设置浮窗xml布局文件，并可设置详细信息
//                .setLayout(R.layout.floatball_layout, view -> {
//                    //   TextView textView =  view.findViewById(R.id.tv_shopping_counts);
//                    view.findViewById(R.id.iv_sp_cart).setOnClickListener(new ClickProxy(v -> {
//                        Intent intent = new Intent(this, ShoppingActivity.class);
//                        startActivity(intent);
//                    }));
//                })
//                // 设置浮窗显示类型，默认只在当前Activity显示，可选一直显示、仅前台显示、仅后台显示
//                .setShowPattern(ShowPattern.FOREGROUND)
//                // 设置吸附方式，共15种模式，详情参考SidePattern
//                .setSidePattern(SidePattern.RESULT_HORIZONTAL)
//                // 设置浮窗的标签，用于区分多个浮窗
//                .setTag("floatBall")
//                // 设置浮窗是否可拖拽，默认可拖拽
//                .setDragEnable(true)
//                // 系统浮窗是否包含EditText，仅针对系统浮窗，默认不包含
//                .hasEditText(false)
//                // 设置浮窗固定坐标，ps：设置固定坐标，Gravity属性和offset属性将无效
////                .setLocation(100, 200)
//                // 设置浮窗的对齐方式和坐标偏移量
//                .setGravity(Gravity.END | Gravity.CENTER_VERTICAL, 0, 200)
//                // 设置宽高是否充满父布局，直接在xml设置match_parent属性无效
//                .setMatchParent(false, false)
//                // 设置Activity浮窗的出入动画，可自定义，实现相应接口即可（策略模式），无需动画直接设置为null
//                .setAnimator(null)
//                // 设置系统浮窗的出入动画，使用同上
//                .setAppFloatAnimator(null)
//                // 设置系统浮窗的不需要显示的页面
//                // .setFilter(MainActivity:: class.java, SecondActivity:: class.java)
//                // 设置系统浮窗的有效显示高度（不包含虚拟导航栏的高度），基本用不到，除非有虚拟导航栏适配问题
//                .setDisplayHeight(DisplayUtils.INSTANCE::rejectedNavHeight)
////    .setDisplayHeight(OnDisplayHeight {
////            context -> DisplayUtils.rejectedNavHeight(context)
////        })
//                // 浮窗的一些状态回调，如：创建结果、显示、隐藏、销毁、touchEvent、拖拽过程、拖拽结束。
//                // ps：通过Kotlin DSL实现的回调，可以按需复写方法，用到哪个写哪个
//
//                // 创建浮窗（这是关键哦😂）
//                .show();
    }


    @Override
    protected void initComponent() {
        DaggerMainActivityComponent.builder()
                .netComponent(App.getInstance().getmNetComponent())
                .mainActivityModule(new MainActivityModule(this))
                .appComponent(App.getInstance().getmAppComponent())
                .build()
                .inject(this);
    }

    @Override
    protected void initData() {
//        this.rabbitMQConfiguration = RabbitMQConfiguration.getInstance();
//        this.handlerReceiverPositionMessage();
//        RxTimer hbRxTimer = new RxTimer();
//        hbRxTimer.interval(5000, number -> {
//            this.rabbitMQConfiguration.basicPublish(() -> "测试内容");
//        });
    }
    private void handlerReceiverPositionMessage(){
        rabbitMQConfiguration.basicConsumer(new ResqonCallBack() {
            @Override
            public void onSuccess(String jsonString) {
                System.out.println("handlerReceiverPositionMessage : "+jsonString);
            }
        });
    }

    @Override
    protected void onDestroy() {
        presenter.onDestory();
        //悬浮球销毁，需要重新实例化
        EasyFloat.dismissAppFloat("floatBall");
        super.onDestroy();
    }

    /**
     * 数据请求成功回调
     */
    @Override
    public void onSuccess(BaseResponse<String> s) {
        Logger.e(s.toString());
    }

    /**
     * 数据请求失败回调
     */
    @Override
    public void onFailed() {

    }

    /**
     * 没有网络状态回调
     */
    @Override
    public void noNetWork() {

    }

    /**
     * 无数据状态回调
     */
    @Override
    public void noData() {

    }

    /**
     * 消除LoadingDialog
     */
    @Override
    public void onDisLoadingDialog() {
        this.dismissLoadingDialog();
    }

    @Override
    public void onShowLoadingDialog() {
        this.showLoadingDialog();
    }

    private long exitTime = 0;

    @Override
    public void onBackPressed() {
        if ((System.currentTimeMillis() - exitTime) > 2000) {
            ToastUtil.showBottom(this, getResources().getString(R.string.login_activity_exiting));
            exitTime = System.currentTimeMillis();
            return;
        } else {
            finish();
        }
        super.onBackPressed();
    }
}
