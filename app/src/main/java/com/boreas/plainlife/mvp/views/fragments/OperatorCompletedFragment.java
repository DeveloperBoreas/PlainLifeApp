package com.boreas.plainlife.mvp.views.fragments;

import androidx.recyclerview.widget.LinearLayoutManager;

import com.boreas.plainlife.R;
import com.boreas.plainlife.adapter.OperatorCompletedAdapter;
import com.boreas.plainlife.base.BaseFragment;
import com.boreas.plainlife.databinding.FragmentMCompletedBinding;
import com.boreas.plainlife.mvp.models.precenter.OperatorApplicationModel;

import java.util.ArrayList;
import java.util.List;

/**
 * 我的申请 ------ 已完成
 */
public class OperatorCompletedFragment extends BaseFragment<FragmentMCompletedBinding> {

    private ArrayList<OperatorApplicationModel.DataBean.AgreeBean> completeList;
    private OperatorCompletedAdapter adapter;

    @Override
    public void lazyFetchData() {
        if(this.completeList != null && !this.completeList.isEmpty()){
            this.adapter.reset(this.completeList);
        }
    }
    public void setAgreeData(List<OperatorApplicationModel.DataBean.AgreeBean> completeList){
        this.completeList.clear();
        this.completeList.addAll(completeList);
        this.lazyFetchData();
    }

    @Override
    public int setContent() {
        return R.layout.fragment_m_completed;
    }

    @Override
    public void initView() {
        this.completeList = new ArrayList<>();
        this.binding.list.setHasFixedSize(false);
        this.binding.list.setLayoutManager(new LinearLayoutManager(getContext()));
        this.adapter = new OperatorCompletedAdapter(getContext(), this.completeList, R.layout.operator_appling_item);
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

}
