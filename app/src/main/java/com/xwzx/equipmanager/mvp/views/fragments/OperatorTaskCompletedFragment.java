package com.xwzx.equipmanager.mvp.views.fragments;

import androidx.recyclerview.widget.LinearLayoutManager;

import com.xwzx.equipmanager.R;
import com.xwzx.equipmanager.adapter.KeeperCompletedAdapter;
import com.xwzx.equipmanager.adapter.OperatorCompletedAdapter;
import com.xwzx.equipmanager.adapter.OperatorTaskCompletedAdapter;
import com.xwzx.equipmanager.base.BaseFragment;
import com.xwzx.equipmanager.databinding.FragmentMCompletedBinding;
import com.xwzx.equipmanager.mvp.models.DisposedModel;
import com.xwzx.equipmanager.mvp.models.MUpComingModel;
import com.xwzx.equipmanager.mvp.models.indexoperator.TaskModel;

import java.util.ArrayList;
import java.util.List;

/**
 * 我的申请 ------ 已完成
 */
public class OperatorTaskCompletedFragment extends BaseFragment<FragmentMCompletedBinding> {

    private ArrayList<TaskModel.DataBeanX.DataBean.TasksBean> taskList;
    private OperatorTaskCompletedAdapter adapter;

    @Override
    public void lazyFetchData() {
        if(this.taskList != null && !this.taskList.isEmpty()){
            this.adapter.reset(this.taskList);
        }
    }

    @Override
    public int setContent() {
        return R.layout.fragment_m_completed;
    }

    @Override
    public void initView() {
        this.taskList = new ArrayList<>();
        this.binding.list.setHasFixedSize(false);
        this.binding.list.setLayoutManager(new LinearLayoutManager(getContext()));
        this.adapter = new OperatorTaskCompletedAdapter(getContext(), this.taskList, R.layout.recycle_history_case_item_layout);
        this.binding.list.setAdapter(this.adapter);
    }

    public void setHistoryTaskData(List<TaskModel.DataBeanX.DataBean.TasksBean> historyTaskData){
        this.taskList.clear();
        this.taskList.addAll(historyTaskData);
        this.lazyFetchData();
    }

    @Override
    public void initListener() {

    }

    @Override
    public void initComponent() {

    }

    @Override
    public boolean onBackPressed() {
        return false;
    }

}
