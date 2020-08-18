package com.xwzx.equipmanager.mvp.views.fragments;

import android.view.View;

import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.orhanobut.logger.Logger;
import com.xwzx.equipmanager.App;
import com.xwzx.equipmanager.Constant;
import com.xwzx.equipmanager.R;
import com.xwzx.equipmanager.adapter.ShowImgAdapter;
import com.xwzx.equipmanager.base.BaseFragment;
import com.xwzx.equipmanager.databinding.FragmentOperatorProcessCaseBinding;
import com.xwzx.equipmanager.databinding.RecycleProcessCaseItemLayoutBinding;
import com.xwzx.equipmanager.internal.components.DaggerOperatorProcessingTaskFragmentComponect;
import com.xwzx.equipmanager.internal.modules.OperatorProcessingTaskFragmentModule;
import com.xwzx.equipmanager.mvp.models.indexoperator.TaskModel;
import com.xwzx.equipmanager.mvp.presenters.presenterimpl.OperatorProcessingTaskPresenter;
import com.xwzx.equipmanager.mvp.views.viewinterfaces.OperatorProcessingTaskFragmentInterface;
import com.xwzx.equipmanager.utils.ToastUtil;


import java.util.ArrayList;
import java.util.Collections;

import javax.inject.Inject;


public class OperatorProcessingCaseFragment extends BaseFragment<FragmentOperatorProcessCaseBinding> implements OperatorProcessingTaskFragmentInterface {

    //在办任务
    private static final int STATUS = 1;
    @Inject
    OperatorProcessingTaskPresenter presenter;
    private ArrayList<TaskModel.DataBeanX.DataBean.TasksBean> tasksBeans;

    @Override
    public void lazyFetchData() {
        presenter.requestHistoryTask(STATUS);
    }

    @Override
    public int setContent() {
        return R.layout.fragment_operator_process_case;
    }

    @Override
    public void initView() {

    }

    @Override
    public void initListener() {

    }

    @Override
    public void initComponent() {
        DaggerOperatorProcessingTaskFragmentComponect.builder()
                .netComponent(App.getInstance().getmNetComponent())
                .operatorProcessingTaskFragmentModule(new OperatorProcessingTaskFragmentModule(this))
                .build().inject(this);
    }

    @Override
    public boolean onBackPressed() {
        return false;
    }

    /**
     * 请求历史任务信息
     *
     * @param tasksBeans
     */
    @Override
    public void onProcessingTaskMessageSuccess(ArrayList<TaskModel.DataBeanX.DataBean.TasksBean> tasksBeans) {
        if (tasksBeans != null && !tasksBeans.isEmpty()) {
            this.binding.llContain.removeAllViews();
            Collections.reverse(tasksBeans);
            this.tasksBeans = tasksBeans;
            Logger.e("onProcessingTaskMessageSuccess " + tasksBeans.size());
            if (tasksBeans.size() > 2) {
                for (int i = 0; i < 3; i++) {
                    this.binding.llContain.addView(this.createView(tasksBeans.get(i)));
                }
            } else {
                for (int i = 0, size = tasksBeans.size(); i < size; i++) {
                    this.binding.llContain.addView(this.createView(tasksBeans.get(i)));
                }
            }
        }
    }

    public ArrayList<TaskModel.DataBeanX.DataBean.TasksBean> getTasksBeans() {
        return tasksBeans == null ? null : tasksBeans;
    }

    /**
     * 创建视图
     */
    private View createView(TaskModel.DataBeanX.DataBean.TasksBean taskBean) {
        RecycleProcessCaseItemLayoutBinding binding = DataBindingUtil.inflate(getLayoutInflater(), R.layout.recycle_process_case_item_layout, null, false);
        binding.tvCaseName.setText(taskBean.getName() + "");
        binding.tvCaseStatus.setVisibility(View.GONE);
        binding.tvReturnTime.setText("创建时间：" + taskBean.getCreate_time() + "");
        ArrayList<String> imgUrls = new ArrayList<>();
        for (TaskModel.DataBeanX.DataBean.TasksBean.EquipmentBean equipmentBean : taskBean.getEquipment()) {
            imgUrls.add(equipmentBean.getEquipment_img());
        }
        binding.rvCaseEquipImglist.setHasFixedSize(true);
        ShowImgAdapter adapter = new ShowImgAdapter(getActivity(), imgUrls, R.layout.recycle_case_child_item_layout);
        binding.rvCaseEquipImglist.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false));
        binding.rvCaseEquipImglist.setAdapter(adapter);
        return binding.getRoot();
    }

    /**
     * 请求数据失败回调
     *
     * @param message
     */
    @Override
    public void onFailed(String message) {
        ToastUtil.show(getActivity(), message);
    }

    /**
     * 没有网络状态回调
     */
    @Override
    public void noNetWork() {
        ToastUtil.show(getActivity(), "当前无网络");
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
        if (presenter != null) {
            presenter.onDestory();
        }
        super.onDestroyView();
    }
}
