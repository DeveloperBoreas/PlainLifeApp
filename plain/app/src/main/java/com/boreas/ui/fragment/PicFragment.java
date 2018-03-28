package com.boreas.ui.fragment;

import android.databinding.DataBindingUtil;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.boreas.R;
import com.boreas.adapter.PicAdapter;
import com.boreas.base.BaseFragment;
import com.boreas.databinding.FragmentPicBinding;
import com.boreas.di.componects.DaggerPicFragmentComponent;
import com.boreas.di.modules.PicFragmentModule;
import com.boreas.listener.ClickListener;
import com.boreas.model.entity.PicEntity;
import com.boreas.presenter.PicPresenter;
import com.boreas.presenter.PresenterContract;
import com.boreas.ui.recycle.OffsetDecoration;
import com.orhanobut.logger.Logger;

import java.util.List;

import javax.inject.Inject;

/**
 * 作者 boreas
 * 日期 18-3-28
 * 邮箱 13051089921@163.com
 * @author boreas
 */

public class PicFragment extends BaseFragment implements PresenterContract.PicView,ClickListener<PicEntity.Pic>{

    private FragmentPicBinding binding = null;
    protected OffsetDecoration decoration = new OffsetDecoration();

    @Inject
    PicPresenter presenter;
    @Override
    public View initView(LayoutInflater inflater, ViewGroup container) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_pic,container,false);
        StaggeredGridLayoutManager layoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        binding.fragmentPicRecycle.setLayoutManager(layoutManager);
        binding.fragmentPicRecycle.setHasFixedSize(false);
        binding.fragmentPicRecycle.removeItemDecoration(decoration);
        binding.fragmentPicRecycle.addItemDecoration(decoration);
        initDagger();
        initPresenter();
        return binding.getRoot();
    }

    private void initDagger() {
        DaggerPicFragmentComponent.builder().picFragmentModule(new PicFragmentModule(this)).build().inject(this);
    }

    private void initPresenter() {
    }



    @Override
    public void lazyFetchData() {
        presenter.getPicList();
    }

    @Override
    public void getPicList(List<PicEntity.Pic> list) {
        Logger.d("-------pic -------------- :" +list.size());
        Log.e("-----------","pic list:" + list.size());
        PicAdapter adapter = new PicAdapter(list);
        adapter.setOnClickListener(this);
        binding.fragmentPicRecycle.setAdapter(adapter);
    }

    @Override
    public void onItemClick(View itemView, int position, PicEntity.Pic pic) {

    }

    @Override
    public void onItemLongClick(View itemView, int position, PicEntity.Pic pic) {

    }
}
