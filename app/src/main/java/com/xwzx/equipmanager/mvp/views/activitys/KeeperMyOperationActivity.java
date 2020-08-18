package com.xwzx.equipmanager.mvp.views.activitys;

import android.view.View;

import com.xwzx.equipmanager.R;
import com.xwzx.equipmanager.adapter.TabLayoutViewPagerAdapter;
import com.xwzx.equipmanager.base.BaseActivity;
import com.xwzx.equipmanager.base.BaseFragment;
import com.xwzx.equipmanager.databinding.ActivityCustodianMyOperationLayoutBinding;
import com.xwzx.equipmanager.framwork.ClickProxy;
import com.xwzx.equipmanager.mvp.views.fragments.keeperMyOperationFragment;

import java.util.ArrayList;

public class KeeperMyOperationActivity extends BaseActivity<ActivityCustodianMyOperationLayoutBinding> {
    private ArrayList<BaseFragment> fragments;
    String[] titles = {"全部","出库","盘库","维修","检测","报废"};
    @Override
    public int setContentView() {
        return R.layout.activity_custodian_my_operation_layout;
    }

    @Override
    protected void initView() {
        int uptab = getIntent().getIntExtra("UPTAB", 0);

        this.binding.headLayout.headTitle.setText(getResources().getString(R.string.my_operation));
        this.binding.headLayout.rightTitle.setVisibility(View.GONE);
        fragments = new ArrayList<>();
        fragments.add(new keeperMyOperationFragment());
        fragments.add(new keeperMyOperationFragment());
        fragments.add(new keeperMyOperationFragment());
        fragments.add(new keeperMyOperationFragment());
        fragments.add(new keeperMyOperationFragment());
        fragments.add(new keeperMyOperationFragment());
        this.binding.viewPager.setAdapter(new TabLayoutViewPagerAdapter(getSupportFragmentManager(), fragments,titles));
        this.binding.tabLayout.setupWithViewPager(this.binding.viewPager);
        this.binding.viewPager.setCurrentItem(uptab);
    }

    @Override
    protected void initListener() {
        this.binding.headLayout.back.setOnClickListener(new ClickProxy(v -> {
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
