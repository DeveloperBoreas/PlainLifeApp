package com.xwzx.equipmanager.mvp.views.fragments;

import android.content.Intent;

import com.xwzx.equipmanager.App;
import com.xwzx.equipmanager.R;
import com.xwzx.equipmanager.adapter.TabLayoutViewPagerAdapter;
import com.xwzx.equipmanager.base.BaseFragment;
import com.xwzx.equipmanager.databinding.FragmentMainOperatorBinding;
import com.xwzx.equipmanager.framwork.ClickProxy;
import com.xwzx.equipmanager.internal.components.DaggerMainIndexOperatorFragmentComponect;
import com.xwzx.equipmanager.internal.modules.MainIndexOperatorFragmentModule;
import com.xwzx.equipmanager.mvp.models.precenter.PersonalBaseMsgModel;
import com.xwzx.equipmanager.mvp.presenters.presenterimpl.MainIndexOperatorPresenter;
import com.xwzx.equipmanager.mvp.views.activitys.MainHistoryCaseActivity;
import com.xwzx.equipmanager.mvp.views.activitys.MainProcessingCaseActivity;
import com.xwzx.equipmanager.mvp.views.activitys.MessageActivity;
import com.xwzx.equipmanager.mvp.views.activitys.OperatorApplyActivity;
import com.xwzx.equipmanager.mvp.views.activitys.OperatorMyRecordActivity;
import com.xwzx.equipmanager.mvp.views.viewinterfaces.MainIndexOperatorFragmentInterface;
import com.xwzx.equipmanager.utils.ToastUtil;


import java.util.ArrayList;

import javax.inject.Inject;


public class MainIndexOperatorFragment extends BaseFragment<FragmentMainOperatorBinding> implements MainIndexOperatorFragmentInterface {

    @Inject
    MainIndexOperatorPresenter presenter;
    private ArrayList<BaseFragment> fragments;

    @Override
    public void lazyFetchData() {
        //请求申请进度登信息.
        presenter.requestPersonalBase();
    }

    @Override
    public int setContent() {
        return R.layout.fragment_main_operator;
    }

    @Override
    public void initView() {
        fragments = new ArrayList<>();
        OperatorHistoryTaskFragment historyTaskFragment = new OperatorHistoryTaskFragment();
        OperatorProcessingCaseFragment processingCaseFragment = new OperatorProcessingCaseFragment();
        fragments.add(historyTaskFragment);//0
        fragments.add(processingCaseFragment);//1
        this.binding.viewPager.setAdapter(new TabLayoutViewPagerAdapter(getChildFragmentManager(), fragments, new String[]{"历史任务", "在办任务"}));
        this.binding.tabLayout.setupWithViewPager(this.binding.viewPager);
        this.binding.tvCaseMore.setOnClickListener(new ClickProxy(view -> {
            int selectedTabPosition = this.binding.tabLayout.getSelectedTabPosition();
            if (selectedTabPosition == 0) {
                Intent intent = new Intent(getActivity(), MainHistoryCaseActivity.class);
                if(historyTaskFragment.getTasksBeans() != null){
                    intent.putExtra("taskBeans",historyTaskFragment.getTasksBeans());
                }
                startActivity(intent);
            } else if (selectedTabPosition == 1) {
                Intent intent = new Intent(getActivity(), MainProcessingCaseActivity.class);
                if(processingCaseFragment.getTasksBeans() != null){
                    intent.putExtra("taskBeans",processingCaseFragment.getTasksBeans());
                }
                startActivity(intent);
            }

        }));
    }


    @Override
    public void initListener() {
        this.binding.llMyMessage.setOnClickListener(new ClickProxy(v -> startActivity(new Intent(getActivity(), MessageActivity.class))));
        this.binding.llMyRecord.setOnClickListener(new ClickProxy(v -> startActivity(new Intent(getActivity(), OperatorMyRecordActivity.class))));
        this.binding.llApplyProgress.setOnClickListener(new ClickProxy(v -> startActivity(new Intent(getActivity(), OperatorApplyActivity.class))));
    }

    @Override
    public void initComponent() {
        DaggerMainIndexOperatorFragmentComponect.builder()
                .netComponent(App.getInstance().getmNetComponent())
                .mainIndexOperatorFragmentModule(new MainIndexOperatorFragmentModule(this))
                .build()
                .inject(this);
    }

    @Override
    public boolean onBackPressed() {
        return false;
    }

    /**
     * 请求个人基本信息成功回调
     *
     * @param model
     */
    @Override
    public void onPersonalBaseMsgSuccess(PersonalBaseMsgModel model) {
        if (model.getData() != null && model.getData().getResult() != null) {
            PersonalBaseMsgModel.DataBean.ResultBean result = model.getData().getResult();
            this.binding.mApplication.setText(result.getApplicationNum() + "");
            this.binding.mRecord.setText(result.getHistoryNum() + "");
            this.binding.mMessage.setText(result.getAlarmNum() + "");
        }
    }

    /**
     * 请求数据失败回调
     *
     * @param message
     */
    @Override
    public void onFailed(String message) {
        ToastUtil.show(getActivity(),message);
    }

    /**
     * 没有网络状态回调
     */
    @Override
    public void noNetWork() {
        ToastUtil.show(getActivity(),"当前无网络！");
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

    @Override
    public void onDestroyView() {
        if(presenter != null){
            presenter.onDestory();
        }
        super.onDestroyView();
    }
}
