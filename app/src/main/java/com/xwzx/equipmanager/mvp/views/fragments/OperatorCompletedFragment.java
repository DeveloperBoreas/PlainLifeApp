package com.xwzx.equipmanager.mvp.views.fragments;

import androidx.recyclerview.widget.LinearLayoutManager;

import com.xwzx.equipmanager.R;
import com.xwzx.equipmanager.adapter.OperatorApplingAdapter;
import com.xwzx.equipmanager.adapter.OperatorCompletedAdapter;
import com.xwzx.equipmanager.base.BaseFragment;
import com.xwzx.equipmanager.databinding.FragmentMCompletedBinding;
import com.xwzx.equipmanager.mvp.models.DisposedModel;
import com.xwzx.equipmanager.mvp.models.MUpComingModel;
import com.xwzx.equipmanager.mvp.models.precenter.OperatorApplicationModel;

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
