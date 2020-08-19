package com.boreas.plainlife.mvp.views.fragments;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.boreas.plainlife.R;
import com.boreas.plainlife.adapter.OperatorMyRecordParentAdapter;
import com.boreas.plainlife.base.BaseFragment;
import com.boreas.plainlife.databinding.FragmentOperatorMyRecordBinding;
import com.boreas.plainlife.mvp.models.precenter.OperatorAllRecordModel;

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

