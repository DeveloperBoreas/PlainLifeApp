package com.xwzx.equipmanager.mvp.views.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.xwzx.equipmanager.R;
import com.xwzx.equipmanager.adapter.OperatorCompletedAdapter;
import com.xwzx.equipmanager.adapter.OperatorMyRecordParentAdapter;
import com.xwzx.equipmanager.base.BaseFragment;
import com.xwzx.equipmanager.databinding.FragmentOperatorMyRecordBinding;
import com.xwzx.equipmanager.mvp.models.precenter.OperatorAllRecordModel;
import com.xwzx.equipmanager.mvp.models.precenter.OperatorApplicationModel;
import com.xwzx.equipmanager.utils.ListUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class OperatorMyRecordFragment extends BaseFragment<FragmentOperatorMyRecordBinding>{
    private ArrayList<OperatorAllRecordModel.DataBeanXXX.InnerBean> innerBeans;
    private OperatorMyRecordParentAdapter adapter;

    @Override
    public void lazyFetchData() {
        if(this.innerBeans != null && !this.innerBeans.isEmpty()){
            this.adapter.reset(this.innerBeans);
        }
    }
    public void setRecordData(List<OperatorAllRecordModel.DataBeanXXX.InnerBean> innerBeans){
        this.innerBeans.clear();
        this.innerBeans.addAll(innerBeans);
        this.lazyFetchData();
    }

    @Override
    public int setContent() {
        return R.layout.fragment_operator_my_record;
    }

    @Override
    public void initView() {
        this.innerBeans = new ArrayList<>();
        this.adapter = new OperatorMyRecordParentAdapter(getActivity(),this.innerBeans,R.layout.recycle_operator_my_record_parent_item);
        this.binding.recycleMyRecordParent.setHasFixedSize(false);
        this.binding.recycleMyRecordParent.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
        this.binding.recycleMyRecordParent.setAdapter(this.adapter);
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

