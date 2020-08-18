package com.xwzx.equipmanager.mvp.views.fragments;

import android.content.Intent;
import android.view.View;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.xwzx.equipmanager.R;
import com.xwzx.equipmanager.adapter.OperatorApplingAdapter;
import com.xwzx.equipmanager.base.BaseFragment;
import com.xwzx.equipmanager.base.adapter.BaseRecyclerAdapter;
import com.xwzx.equipmanager.databinding.FragmentCommonListBinding;
import com.xwzx.equipmanager.mvp.models.MUpComingModel;
import com.xwzx.equipmanager.mvp.models.indexoperator.TaskModel;
import com.xwzx.equipmanager.mvp.models.precenter.OperatorApplicationModel;
import com.xwzx.equipmanager.mvp.views.activitys.DisposeTodoActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * 申请中
 */
public class OperatorApplingFragment extends BaseFragment<FragmentCommonListBinding> implements BaseRecyclerAdapter.OnItemClickListener {

    private ArrayList<OperatorApplicationModel.DataBean.ApplyBean> applingList;
    private OperatorApplingAdapter adapter;

    @Override
    public void lazyFetchData() {
        if(this.applingList != null && !this.applingList.isEmpty()){
            this.adapter.reset(this.applingList);
        }
    }
    public void setApplintData(List<OperatorApplicationModel.DataBean.ApplyBean> processingList){
        this.applingList.clear();
        this.applingList.addAll(processingList);
        this.lazyFetchData();
    }

    @Override
    public int setContent() {
        return R.layout.fragment_common_list;
    }

    @Override
    public void initView() {
        this.applingList = new ArrayList<>();
        this.binding.list.setHasFixedSize(false);
        this.binding.list.setLayoutManager(new LinearLayoutManager(getContext()));
        this.adapter = new OperatorApplingAdapter(getContext(), this.applingList, R.layout.operator_appling_item);
        this.binding.list.setAdapter(this.adapter);
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
//        Intent intent = new Intent(getActivity(), DisposeTodoActivity.class);
//        startActivity(intent);
    }
}
