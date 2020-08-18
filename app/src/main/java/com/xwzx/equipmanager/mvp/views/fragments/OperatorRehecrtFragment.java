package com.xwzx.equipmanager.mvp.views.fragments;

import androidx.recyclerview.widget.LinearLayoutManager;

import com.xwzx.equipmanager.R;
import com.xwzx.equipmanager.adapter.OperatorApplingAdapter;
import com.xwzx.equipmanager.adapter.OperatorCompletedAdapter;
import com.xwzx.equipmanager.adapter.OperatorRehecrtAdapter;
import com.xwzx.equipmanager.base.BaseFragment;
import com.xwzx.equipmanager.databinding.FragmentCommonListBinding;
import com.xwzx.equipmanager.databinding.FragmentMCompletedBinding;
import com.xwzx.equipmanager.mvp.models.DisposedModel;
import com.xwzx.equipmanager.mvp.models.MUpComingModel;
import com.xwzx.equipmanager.mvp.models.precenter.OperatorApplicationModel;

import java.util.ArrayList;
import java.util.List;

/**
 * 我的申请 ------ 已驳回
 */
public class OperatorRehecrtFragment extends BaseFragment<FragmentCommonListBinding> {

    private ArrayList<OperatorApplicationModel.DataBean.RehecrtBean> rehecrtBeans;
    private OperatorRehecrtAdapter adapter;

    @Override
    public void lazyFetchData() {
        if(this.rehecrtBeans != null && !this.rehecrtBeans.isEmpty()){
            this.adapter.reset(this.rehecrtBeans);
        }
    }
    public void setRehecrtData(List<OperatorApplicationModel.DataBean.RehecrtBean> rehecrtBeans){
        this.rehecrtBeans.clear();
        this.rehecrtBeans.addAll(rehecrtBeans);
        this.lazyFetchData();
    }
    @Override
    public int setContent() {
       return R.layout.fragment_common_list;
    }

    @Override
    public void initView() {
        this.rehecrtBeans = new ArrayList<>();
        this.binding.list.setHasFixedSize(false);
        this.binding.list.setLayoutManager(new LinearLayoutManager(getContext()));
        this.adapter = new OperatorRehecrtAdapter(getContext(), this.rehecrtBeans, R.layout.operator_appling_item);
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
