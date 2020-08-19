package com.boreas.plainlife.mvp.views.fragments;

import androidx.recyclerview.widget.LinearLayoutManager;

import com.boreas.plainlife.R;
import com.boreas.plainlife.adapter.KeeperCompletedAdapter;
import com.boreas.plainlife.base.BaseFragment;
import com.boreas.plainlife.databinding.FragmentMCompletedBinding;
import com.boreas.plainlife.mvp.models.DisposedModel;
import com.boreas.plainlife.mvp.models.MUpComingModel;

import java.util.ArrayList;

/**
 * 我的申请 ------ 已完成
 */
public class KeeperCompletedFragment extends BaseFragment<FragmentMCompletedBinding> {

    private ArrayList<MUpComingModel> completeList;

    @Override
    public void lazyFetchData() {

    }

    @Override
    public int setContent() {
        return R.layout.fragment_m_completed;
    }

    @Override
    public void initView() {
        this.initData();
        this.binding.list.setHasFixedSize(false);
        this.binding.list.setLayoutManager(new LinearLayoutManager(getContext()));
        this.binding.list.setAdapter(new KeeperCompletedAdapter(getContext(), this.completeList, R.layout.keeper_appling_item));
    }

    private void initData() {
        this.completeList = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            completeList.add(new MUpComingModel(i, i < 2 ? 1:0,"标题"+i,"张三",System.currentTimeMillis()));
        }
    }

    private ArrayList<DisposedModel.DisposedChildModel> getChildDisposedModel() {
        ArrayList<DisposedModel.DisposedChildModel> disposedChildModels = new ArrayList<>();
        for (int i = 0; i < 2; i++) {
            DisposedModel.DisposedChildModel disposedChildModel = new DisposedModel.DisposedChildModel(i, null, "子标题" + i, "一架3列", i == 0 ? 0 : 1);
            disposedChildModels.add(disposedChildModel);
        }
        return disposedChildModels;
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
