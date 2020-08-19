package com.boreas.plainlife.mvp.views.fragments;

import android.view.View;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.boreas.plainlife.R;
import com.boreas.plainlife.adapter.OperatorTaskingAdapter;
import com.boreas.plainlife.base.BaseFragment;
import com.boreas.plainlife.base.adapter.BaseRecyclerAdapter;
import com.boreas.plainlife.databinding.FragmentCommonListBinding;
import com.boreas.plainlife.mvp.models.indexoperator.TaskModel;

import java.util.ArrayList;
import java.util.List;

/**
 * 申请中
 */
public class OperatorTaskingFragment extends BaseFragment<FragmentCommonListBinding> implements BaseRecyclerAdapter.OnItemClickListener {

    private ArrayList<TaskModel.DataBeanX.DataBean.TasksBean> taskList;
    private BaseRecyclerAdapter<TaskModel.DataBeanX.DataBean.TasksBean> adapter;

    @Override
    public void lazyFetchData() {
        if(this.taskList != null && !this.taskList.isEmpty()){
            this.adapter.reset(this.taskList);
        }
    }

    @Override
    public int setContent() {
        return R.layout.fragment_common_list;
    }

    @Override
    public void initView() {
        this.taskList = new ArrayList<>();
        this.binding.list.setHasFixedSize(false);
        this.binding.list.setLayoutManager(new LinearLayoutManager(getContext()));
        this.adapter = new OperatorTaskingAdapter(getContext(), this.taskList, R.layout.recycle_process_case_item_layout).setOnItemClickListener(this);
        this.binding.list.setAdapter(this.adapter);
    }

    public void setProcessingTaskData(List<TaskModel.DataBeanX.DataBean.TasksBean> processingList){
        this.taskList.clear();
        this.taskList.addAll(processingList);
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

    @Override
    public void onItemClick(RecyclerView parent, View view, int position) {
    }
}