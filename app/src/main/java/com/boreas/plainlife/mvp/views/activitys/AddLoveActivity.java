package com.boreas.plainlife.mvp.views.activitys;

import com.boreas.plainlife.R;
import com.boreas.plainlife.base.BaseActivity;
import com.boreas.plainlife.databinding.ActivityAddLoveBinding;

public class AddLoveActivity extends BaseActivity<ActivityAddLoveBinding> {


    @Override
    public int setContentView() {
        return R.layout.activity_add_love;
    }

    @Override
    protected void initView() {
        this.binding.headLayout.headTitle.setText(getString(R.string.addLovePeople));
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
