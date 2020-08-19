package com.boreas.plainlife.mvp.views.fragments;

import android.view.View;
import android.widget.RadioGroup;

import androidx.recyclerview.widget.LinearLayoutManager;

import com.boreas.plainlife.R;
import com.boreas.plainlife.adapter.KeeperMyOperationItemAdapter;
import com.boreas.plainlife.base.BaseFragment;
import com.boreas.plainlife.databinding.FragmentCustodianMyOperationBinding;

import java.util.ArrayList;

public class keeperMyOperationFragment extends BaseFragment<FragmentCustodianMyOperationBinding> {
    @Override
    public void lazyFetchData() {

    }

    @Override
    public int setContent() {
        return R.layout.fragment_custodian_my_operation;
    }

    @Override
    public void initView() {
        ArrayList<String> dataList = new ArrayList<>();
        dataList.add("1");
        dataList.add("1");
        dataList.add("1");
        dataList.add("1");
        KeeperMyOperationItemAdapter adapter = new KeeperMyOperationItemAdapter(getActivity(),dataList,R.layout.recycle_custodian_my_operation_item);
        this.binding.recycleProcessing.setHasFixedSize(false);
        this.binding.recycleProcessing.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
        this.binding.recycleProcessing.setAdapter(adapter);



        KeeperMyOperationItemAdapter comapletedAdapter = new KeeperMyOperationItemAdapter(getActivity(),dataList,R.layout.recycle_custodian_my_operation_item);
        this.binding.recycleCompleted.setHasFixedSize(false);
        this.binding.recycleCompleted.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
        this.binding.recycleCompleted.setAdapter(comapletedAdapter);

        this.binding.radioGroup.check(R.id.rb_processing);

        this.binding.radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                if(i==R.id.rb_processing){
                    binding.recycleProcessing.setVisibility(View.VISIBLE);
                    binding.recycleCompleted.setVisibility(View.GONE);
                }else if(i==R.id.rb_completed){
                    binding.recycleProcessing.setVisibility(View.GONE);
                    binding.recycleCompleted.setVisibility(View.VISIBLE);
                }
            }
        });
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
