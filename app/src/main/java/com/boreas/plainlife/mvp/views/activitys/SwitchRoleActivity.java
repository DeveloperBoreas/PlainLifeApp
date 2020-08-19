package com.boreas.plainlife.mvp.views.activitys;

import android.content.Intent;

import com.boreas.plainlife.App;
import com.boreas.plainlife.Constant;
import com.boreas.plainlife.R;
import com.boreas.plainlife.base.BaseActivity;
import com.boreas.plainlife.databinding.ActivitySwitchroleBindingImpl;
import com.boreas.plainlife.framwork.ClickProxy;

public class SwitchRoleActivity extends BaseActivity<ActivitySwitchroleBindingImpl> {
    @Override
    public int setContentView() {
        super.isNeedCheck = false;
        return R.layout.activity_switchrole;
    }

    private Intent intent = null;

    @Override
    protected void initView() {
        super.setSwipeBackEnable(false);
        intent = new Intent(this, MainActivity.class);
    }

    @Override
    protected void initListener() {
        this.binding.keeper.setOnClickListener(new ClickProxy(v -> {
            intent.putExtra(Constant.ROLE, Constant.KEEPER_ROLE);
            startActivity(intent);
            App.getInstance().role = Constant.KEEPER_ROLE;
            finish();
        }));
        this.binding.operator.setOnClickListener(new ClickProxy(v -> {
            intent.putExtra(Constant.ROLE, Constant.OPERATOR_ROLE);
            startActivity(intent);
            App.getInstance().role = Constant.OPERATOR_ROLE;
            finish();
        }));
    }

    @Override
    protected void initComponent() {

    }

    @Override
    protected void initData() {

    }
}
