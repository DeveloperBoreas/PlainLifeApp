package com.boreas.plainlife.mvp.views.fragments;

import androidx.recyclerview.widget.LinearLayoutManager;

import com.boreas.plainlife.R;
import com.boreas.plainlife.adapter.KeeperDisposedParentAdapter;
import com.boreas.plainlife.base.BaseFragment;
import com.boreas.plainlife.databinding.FragmentMDisposedBinding;
import com.boreas.plainlife.mvp.models.DisposedModel;

import java.util.ArrayList;

public class keeperDisposedFragment extends BaseFragment<FragmentMDisposedBinding> {

    private ArrayList<DisposedModel> disposedModels;

    @Override
    public void lazyFetchData() {

    }

    @Override
    public int setContent() {
        return R.layout.fragment_m_disposed;
    }

    @Override
    public void initView() {
        this.initData();
        this.binding.list.setHasFixedSize(false);
        this.binding.list.setLayoutManager(new LinearLayoutManager(getContext()));
        this.binding.list.setAdapter(new KeeperDisposedParentAdapter(getContext(), this.disposedModels, R.layout.disposed_item_parent));
    }

    private void initData() {
        this.disposedModels = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            disposedModels.add(new DisposedModel(i, null, "标题" + i, "张三", 1, this.getChildDisposedModel()));
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
