package com.xwzx.equipmanager.mvp.views.activitys;

import android.content.Intent;

import com.xwzx.equipmanager.R;
import com.xwzx.equipmanager.base.BaseActivity;
import com.xwzx.equipmanager.utils.RxTimer;

public class SlapeActivity extends BaseActivity {

    @Override
    public int setContentView() {
        super.isNeedCheck = false;
        return R.layout.activity_slape;
    }

    @Override
    protected void initView() {
        super.setSwipeBackEnable(false);
    }

    @Override
    protected void onResume() {
        super.onResume();
        RxTimer rxTimer = new RxTimer();
        rxTimer.timer(3000, number -> {
            startActivity(new Intent(this, LoginActivity.class));
            rxTimer.cancel();
            finish();
        });
    }

    @Override
    protected void initListener() {

    }

    @Override
    protected void initComponent() {

    }

    @Override
    protected void initData() {

    }
}
