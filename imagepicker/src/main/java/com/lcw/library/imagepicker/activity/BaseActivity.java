package com.lcw.library.imagepicker.activity;

import android.os.Bundle;
import android.view.View;
import android.view.Window;

import androidx.annotation.Nullable;

import com.gyf.immersionbar.ImmersionBar;
import com.xwzx.commonlib.swipebacklayout.lib.app.SwipeBackActivity;

/**
 * BaseActivity基类
 * Create by: chenWei.li
 * Date: 2018/10/9
 * Time: 下午11:34
 * Email: lichenwei.me@foxmail.com
 */
public abstract class BaseActivity extends SwipeBackActivity {

    private View mView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        ImmersionBar.with(this).transparentStatusBar().init();
        if (mView == null) {
            mView = View.inflate(this, bindLayout(), null);
        }
        setContentView(mView);

        initConfig();
        initView();
        initListener();
        getData();
    }


    protected abstract int bindLayout();

    protected void initConfig() {
    }

    protected abstract void initView();

    protected abstract void initListener();

    protected abstract void getData();


}
