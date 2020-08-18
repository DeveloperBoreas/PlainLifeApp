package com.xwzx.equipmanager.mvp.views.activitys;

import android.view.View;
import android.widget.TextView;

import com.google.android.material.tabs.TabLayout;
import com.xwzx.equipmanager.App;
import com.xwzx.equipmanager.R;
import com.xwzx.equipmanager.adapter.TabLayoutViewPagerAdapter;
import com.xwzx.equipmanager.base.BaseActivity;
import com.xwzx.equipmanager.base.BaseFragment;
import com.xwzx.equipmanager.databinding.ActivityOperatorApplyBinding;
import com.xwzx.equipmanager.framwork.ClickProxy;
import com.xwzx.equipmanager.internal.components.DaggerOperatorApplyActivityComponect;
import com.xwzx.equipmanager.internal.modules.OperatorApplyActivityModule;
import com.xwzx.equipmanager.mvp.models.indexoperator.TaskModel;
import com.xwzx.equipmanager.mvp.models.precenter.OperatorApplicationModel;
import com.xwzx.equipmanager.mvp.presenters.presenterimpl.OperatorApplyPresenter;
import com.xwzx.equipmanager.mvp.views.fragments.OperatorApplingFragment;
import com.xwzx.equipmanager.mvp.views.fragments.OperatorCompletedFragment;
import com.xwzx.equipmanager.mvp.views.fragments.OperatorRehecrtFragment;
import com.xwzx.equipmanager.mvp.views.fragments.OperatorTaskCompletedFragment;
import com.xwzx.equipmanager.mvp.views.viewinterfaces.OperatorApplyActivityInterface;
import com.xwzx.equipmanager.utils.ToastUtil;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

public class OperatorApplyActivity extends BaseActivity<ActivityOperatorApplyBinding> implements OperatorApplyActivityInterface {
    //审批中
    private static final int[] APPLING_STATUS = new int[]{1};
    //已完成
    private static final int[] APPLYED_STATUS = new int[]{0,2};

    private ArrayList<BaseFragment> tabFragments;
//    private String[] titles = new String[]{"审批中 5", "已完成 5"};
    private String[] titles = new String[]{"审批中", "已完成","已驳回"};
    private OperatorApplingFragment operatorApplingFragment;
    private OperatorCompletedFragment operatorCompletedFragment;
    private OperatorRehecrtFragment operatorRehecrtFragment;

    @Inject
    OperatorApplyPresenter presenter;

    @Override
    public int setContentView() {
        return R.layout.activity_operator_apply;
    }

    @Override
    protected void initView() {
        this.binding.headLayout.headTitle.setText(getResources().getString(R.string.my_appling));
        this.binding.headLayout.rightTitle.setVisibility(View.GONE);
        this.binding.headLayout.rightBtn.setVisibility(View.GONE);
        this.tabFragments = new ArrayList<>();
        this.operatorApplingFragment = new OperatorApplingFragment();
        this.operatorCompletedFragment = new OperatorCompletedFragment();
        this.operatorRehecrtFragment = new OperatorRehecrtFragment();
        this.tabFragments.add(this.operatorApplingFragment);
        this.tabFragments.add(this.operatorCompletedFragment);
        this.tabFragments.add(this.operatorRehecrtFragment);
        TabLayoutViewPagerAdapter viewPagerAdapter = new TabLayoutViewPagerAdapter(getSupportFragmentManager(), this.tabFragments, titles);
        this.binding.viewPager.setOffscreenPageLimit(this.tabFragments.size());
        this.binding.viewPager.setAdapter(viewPagerAdapter);
        this.binding.tabLayout.setupWithViewPager(this.binding.viewPager);
        for (int i = 0; i < this.binding.tabLayout.getTabCount(); i++) {
            TabLayout.Tab tabAt = this.binding.tabLayout.getTabAt(i);
            View view = getLayoutInflater().inflate(R.layout.tab_item, null);
            TextView tab = view.findViewById(R.id.tab);
            tab.setText(titles[i]);
            tabAt.setCustomView(view);
        }
    }

    @Override
    protected void initListener() {
        this.binding.headLayout.back.setOnClickListener(new ClickProxy(v -> {
            finish();
        }));
    }
    @Override
    protected void initComponent() {
        DaggerOperatorApplyActivityComponect.builder()
                .netComponent(App.getInstance().getmNetComponent())
                .operatorApplyActivityModule(new OperatorApplyActivityModule(this))
                .build()
                .inject(this);
    }

    @Override
    protected void initData() {
        this.presenter.requestOperatorApply();
    }


    /**
     * 申请中成功回调
     *
     * @param agreeBeans
     */
    @Override
    public void onAgreeSuccess(List<OperatorApplicationModel.DataBean.AgreeBean> agreeBeans) {
        if(operatorCompletedFragment != null){
            this.operatorCompletedFragment.setAgreeData(agreeBeans);
        }
    }

    /**
     * 已完成成功回调
     *
     * @param applyBeans
     */
    @Override
    public void onApplySuccess(List<OperatorApplicationModel.DataBean.ApplyBean> applyBeans) {
        if(operatorApplingFragment != null){
            this.operatorApplingFragment.setApplintData(applyBeans);
        }
    }

    /**
     * 已完成成功回调
     *
     * @param rehecrtBeans
     */
    @Override
    public void onRehecrtSuccess(List<OperatorApplicationModel.DataBean.RehecrtBean> rehecrtBeans) {
        if(operatorRehecrtFragment != null){
            this.operatorRehecrtFragment.setRehecrtData(rehecrtBeans);
        }
    }

    /**
     * 请求数据失败回调
     *
     * @param message
     */
    @Override
    public void onFailed(String message) {
        ToastUtil.show(this,message);
    }

    /**
     * 没有网络状态回调
     */
    @Override
    public void noNetWork() {
        ToastUtil.show(this,"当前无网络！");
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

    /**
     * 显示LoadingDialog
     */
    @Override
    public void onShowLoadingDialog() {
        this.showLoadingDialog();
    }
}
