package com.boreas.plainlife.mvp.views.fragments;

import androidx.recyclerview.widget.LinearLayoutManager;

import com.boreas.plainlife.R;
import com.boreas.plainlife.adapter.OperatorTaskCompletedAdapter;
import com.boreas.plainlife.base.BaseFragment;
import com.boreas.plainlife.databinding.FragmentMCompletedBinding;
import com.boreas.plainlife.mvp.models.indexoperator.TaskModel;

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
