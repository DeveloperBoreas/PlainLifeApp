package com.boreas.plainlife.mvp.views.activitys;

import android.content.Intent;
import android.view.View;

import com.boreas.plainlife.App;
import com.boreas.plainlife.R;
import com.boreas.plainlife.adapter.TabLayoutViewPagerAdapter;
import com.boreas.plainlife.base.BaseActivity;
import com.boreas.plainlife.base.BaseFragment;
import com.boreas.plainlife.databinding.ActivityOperatorMyRecordBinding;
import com.boreas.plainlife.framwork.ClickProxy;
import com.boreas.plainlife.internal.components.DaggerOperatorRecordActivityComponect;
import com.boreas.plainlife.internal.modules.OperatorRecordActivityModule;
import com.boreas.plainlife.mvp.models.precenter.OperatorAllRecordModel;
import com.boreas.plainlife.mvp.presenters.presenterimpl.OperatorRecordPresenter;
import com.boreas.plainlife.mvp.views.fragments.OperatorMyRecordFragment;
import com.boreas.plainlife.mvp.views.viewinterfaces.OperatorRecordActivityInterface;
import com.boreas.plainlife.utils.ToastUtil;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

public class OperatorMyRecordActivity extends BaseActivity<ActivityOperatorMyRecordBinding> implements OperatorRecordActivityInterface {
    private ArrayList<BaseFragment> fragments;
    String[] titles = {"全部", "待领取", "已领取", "已逾期", "已归还"};

    @Inject
    OperatorRecordPresenter presenter;
    private OperatorMyRecordFragment allRecordFragment;
    private OperatorMyRecordFragment takeRecordFragment;
    private OperatorMyRecordFragment backRecordFragment;
    private OperatorMyRecordFragment backedRecordFragment;
    private OperatorMyRecordFragment returnedRecordFragment;
    private int uptab = 0;

    @Override
    public int setContentView() {
        return R.layout.activity_operator_my_record;
    }

    @Override
    public void handlerJumpData(Intent intent) {
        this.uptab = getIntent().getIntExtra("UPTAB", 0);
    }

    @Override
    protected void initView() {
        this.binding.headLayout.headTitle.setText(getResources().getString(R.string.my_record));
        this.binding.headLayout.rightTitle.setVisibility(View.GONE);
        fragments = new ArrayList<>();
        this.allRecordFragment = new OperatorMyRecordFragment();
        this.takeRecordFragment = new OperatorMyRecordFragment();
        this.backRecordFragment = new OperatorMyRecordFragment();
        this.backedRecordFragment = new OperatorMyRecordFragment();
        this.returnedRecordFragment = new OperatorMyRecordFragment();
        fragments.add(this.allRecordFragment);
        fragments.add(this.takeRecordFragment);
        fragments.add(this.backRecordFragment);
        fragments.add(this.backedRecordFragment);
        fragments.add(this.returnedRecordFragment);
        this.binding.viewPager.setAdapter(new TabLayoutViewPagerAdapter(getSupportFragmentManager(), fragments, titles));
        this.binding.tabLayout.setupWithViewPager(this.binding.viewPager);
        this.binding.viewPager.setOffscreenPageLimit(5);
    }

    @Override
    protected void initListener() {
        this.binding.headLayout.back.setOnClickListener(new ClickProxy(v -> {
            finish();
        }));
    }

    @Override
    protected void initComponent() {
        DaggerOperatorRecordActivityComponect.builder()
                .netComponent(App.getInstance().getmNetComponent())
                .operatorRecordActivityModule(new OperatorRecordActivityModule(this))
                .build()
                .inject(this);
    }

    @Override
    protected void initData() {
        this.presenter.requestOperatorRecord();
    }

    @Override
    public void onAllRecordSuccess(List<OperatorAllRecordModel.DataBeanXXX.InnerBean> allRecord) {
        if (this.allRecordFragment != null) {
            this.allRecordFragment.setRecordData(allRecord);
        }
    }

    @Override
    public void onTakeRecordSuccess(List<OperatorAllRecordModel.DataBeanXXX.InnerBean> takeRecord) {
        if (this.takeRecordFragment != null) {
            this.takeRecordFragment.setRecordData(takeRecord);
        }
    }

    @Override
    public void onBackRecordSuccess(List<OperatorAllRecordModel.DataBeanXXX.InnerBean> backRecord) {
        if (this.backRecordFragment != null) {
            this.backRecordFragment.setRecordData(backRecord);
        }
    }

    @Override
    public void onBackedRecordSuccess(List<OperatorAllRecordModel.DataBeanXXX.InnerBean> backedRecord) {
        if (this.backedRecordFragment != null) {
            this.backedRecordFragment.setRecordData(backedRecord);
        }
    }

    @Override
    public void onReturnedRecordSuccess(List<OperatorAllRecordModel.DataBeanXXX.InnerBean> returnedRecord) {
        if (this.returnedRecordFragment != null) {
            this.returnedRecordFragment.setRecordData(returnedRecord);
        }
    }

    @Override
    public void onRecordSuccess() {
        this.binding.viewPager.setCurrentItem(this.uptab);
    }

    @Override
    public void onFailed(String message) {
        ToastUtil.show(this, message);
    }

    @Override
    public void noNetWork() {
        ToastUtil.show(this, "当前无网络");
    }

    @Override
    public void noData() {

    }

    @Override
    public void onDisLoadingDialog() {
        super.dismissLoadingDialog();
    }

    @Override
    public void onShowLoadingDialog() {
        super.showLoadingDialog();
    }
}
