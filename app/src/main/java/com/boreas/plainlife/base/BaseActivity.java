package com.boreas.plainlife.base;

import android.content.Intent;
import android.os.Bundle;
import android.view.Window;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;

import com.boreas.commonlib.xskinloader.SkinInflaterFactory;
import com.boreas.plainlife.Constant;
import com.boreas.plainlife.R;
import com.boreas.plainlife.widget.loading.LoadingView;
import com.gyf.immersionbar.ImmersionBar;
import com.tbruyelle.rxpermissions2.RxPermissions;
import com.boreas.commonlib.swipebacklayout.lib.SwipeBackLayout;
import com.boreas.commonlib.swipebacklayout.lib.app.SwipeBackActivity;
import com.boreas.plainlife.utils.ToastUtil;


public abstract class BaseActivity<T extends ViewDataBinding> extends SwipeBackActivity {
    public T binding;
    private LoadingView mDialog;
    public boolean isNeedCheck = true;

    private SwipeBackLayout mSwipeBackLayout;
    private Toolbar mToolbar;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        SkinInflaterFactory.setFactory(this);
        super.onCreate(savedInstanceState);
        this.doBeforeSetcontentView();
        this.binding = DataBindingUtil.setContentView(this, setContentView());
        this.mSwipeBackLayout = getSwipeBackLayout();
        this.mSwipeBackLayout.setEdgeTrackingEnabled(SwipeBackLayout.EDGE_LEFT);
        if (isNeedCheck) {
            checkPermissions(Constant.Permissions);
        }
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        if (mToolbar != null) {
            setSupportActionBar(mToolbar);
        }
        this.initComponent();
        this.handlerJumpData(getIntent());
        this.handlerJumpBundle(savedInstanceState);
        this.initView();
        this.initListener();
        this.initData();
    }

    public Toolbar getToolbar() {
        return mToolbar;
    }

    public void showBackIcon(boolean flag) {
        if (mToolbar != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(flag);
        }
    }

    public void setToolbarTitle(int resId) {
        if (mToolbar != null) {
            getSupportActionBar().setTitle(resId);
        }
    }

    public void setToolbarTitle(String res) {
        if (mToolbar != null) {
            getSupportActionBar().setTitle(res);
        }
    }


    public void setToolbarSubTitle(int resId) {
        if (mToolbar != null) {
            getSupportActionBar().setSubtitle(resId);
        }
    }

    public void setToolbarSubTitle(String res) {
        if (mToolbar != null) {
            getSupportActionBar().setSubtitle(res);
        }
    }

    private void checkPermissions(String... permissions) {
        if (permissions.length > 0) {
            RxPermissions rxPermissions = new RxPermissions(this);
            rxPermissions.request(permissions)
                    .subscribe(isAllow -> {
                        if (isAllow) {
                            //全部权限通过
                        } else {
                            //部分权限未通过
                            ToastUtil.show(this, "部分权限没有获得");
                        }
                    });
        }
    }

    public void handlerJumpBundle(Bundle bundle) {

    }

    public void handlerJumpData(Intent intent) {

    }

    public void showLoadingDialog() {
        if (mDialog == null && !isFinishing()) {
            mDialog = new LoadingView(this,R.style.CustomDialog);
            mDialog.show();
        }
    }

    public void showLoadingDialog(boolean onTouchOutside) {
        if (mDialog == null && !isFinishing()) {
            mDialog = new LoadingView(this,R.style.CustomDialog);
            mDialog.setCanceledOnTouchOutside(onTouchOutside);
            mDialog.setCancelable(onTouchOutside);
            mDialog.show();
        }
    }

    public void showLoadingDialog(String msg) {
        if (mDialog == null && msg != null) {
            mDialog = new LoadingView(this,R.style.CustomDialog);
            mDialog.show();
        }
    }

    public void dismissLoadingDialog() {
        if (mDialog != null && !isFinishing()) {
            mDialog.dismiss();
        }
        mDialog = null;
    }

    protected void doBeforeSetcontentView() {
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        ImmersionBar.with(this).transparentStatusBar().init();
    }

    public abstract int setContentView();

    protected abstract void initView();

    protected abstract void initListener();

    protected abstract void initComponent();

    protected abstract void initData();
}
