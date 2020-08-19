package com.boreas.plainlife.mvp.views.activitys;

import android.view.View;
import android.widget.TextView;

import com.google.android.material.tabs.TabLayout;
import com.boreas.plainlife.App;
import com.boreas.plainlife.R;
import com.boreas.plainlife.adapter.TabLayoutViewPagerAdapter;
import com.boreas.plainlife.base.BaseActivity;
import com.boreas.plainlife.base.BaseFragment;
import com.boreas.plainlife.databinding.ActivityOperatorTaskBinding;
import com.boreas.plainlife.framwork.ClickProxy;
import com.boreas.plainlife.internal.components.DaggerOperatorTaskActivityComponect;
import com.boreas.plainlife.internal.modules.OperatorTaskActivityModule;
import com.boreas.plainlife.mvp.models.indexoperator.TaskModel;
import com.boreas.plainlife.mvp.presenters.presenterimpl.OperatorTaskPresenter;
import com.boreas.plainlife.mvp.views.fragments.OperatorTaskCompletedFragment;
import com.boreas.plainlife.mvp.views.fragments.OperatorTaskingFragment;
import com.boreas.plainlife.mvp.views.viewinterfaces.OperatorTaskActivityInterface;
import com.boreas.plainlife.utils.ToastUtil;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

/**
 * 操作员， 我的任务
 */
public class OperatorTaskActivity extends BaseActivity<ActivityOperatorTaskBinding> implements OperatorTaskActivityInterface {

    //历史任务
    private static final int HISTORY_STATUS = 0;
    //在办任务
    private static final int PROCESSING_STATUS = 1;
    private ArrayList<BaseFragment> tabFragments;
    private String[] titles = new String[]{"在办任务", "历史任务"};
    private OperatorTaskingFragment operatorTaskingFragment;
    private OperatorTaskCompletedFragment operatorTaskCompletedFragment;

    @Inject
    OperatorTaskPresenter presenter;

    @Override
    public int setContentView() {
        return R.layout.activity_operator_task;
    }

    @Override
    protected void initView() {
        this.binding.headLayout.headTitle.setText(getResources().getString(R.string.my_task));
        this.binding.headLayout.rightTitle.setVisibility(View.GONE);
        this.binding.headLayout.rightBtn.setVisibility(View.GONE);
        this.tabFragments = new ArrayList<>();
        this.operatorTaskingFragment = new OperatorTaskingFragment();
        this.operatorTaskCompletedFragment = new OperatorTaskCompletedFragment();
        this.tabFragments.add(operatorTaskingFragment);
        this.tabFragments.add(operatorTaskCompletedFragment);
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
        DaggerOperatorTaskActivityComponect.builder()
                .netComponent(App.getInstance().getmNetComponent())
                .operatorTaskActivityModule(new OperatorTaskActivityModule(this))
                .build()
                .inject(this);
    }

    @Override
    protected void initData() {
        presenter.requestOperatorTask(this.PROCESSING_STATUS, this.HISTORY_STATUS);
    }


    /**
     * 在办任务成功回调
     *
     * @param processingList
     */
    @Override
    public void onProcessingTaskSuccess(List<TaskModel.DataBeanX.DataBean.TasksBean> processingList) {
        if (this.operatorTaskingFragment != null) {
            operatorTaskingFragment.setProcessingTaskData(processingList);
        }
    }

    /**
     * 历史任务成功回调
     *
     * @param historyTaskList
     */
    @Override
    public void onHistoryTaskSuccess(List<TaskModel.DataBeanX.DataBean.TasksBean> historyTaskList) {
        if (this.operatorTaskCompletedFragment != null) {
            operatorTaskCompletedFragment.setHistoryTaskData(historyTaskList);
        }
    }

    /**
     * 请求数据失败回调
     *
     * @param message
     */
    @Override
    public void onFailed(String message) {
        ToastUtil.show(this, message);
    }

    /**
     * 没有网络状态回调
     */
    @Override
    public void noNetWork() {
        ToastUtil.show(this, "当前无网络！");
    }

    /**
     * 无数据状态回调
     */
    @Override
    public void noData() {

    }

    @Override
    protected void onDestroy() {
        if (presenter != null) {
            presenter.onDestory();
        }
        super.onDestroy();
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
