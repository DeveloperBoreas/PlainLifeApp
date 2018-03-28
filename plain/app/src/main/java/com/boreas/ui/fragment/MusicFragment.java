package com.boreas.ui.fragment;

import android.databinding.DataBindingUtil;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.boreas.R;
import com.boreas.adapter.MusicAdapter;
import com.boreas.base.BaseFragment;
import com.boreas.databinding.FragmentMusicBinding;
import com.boreas.di.componects.DaggerMusicFragmentComponent;
import com.boreas.di.modules.MusicFragmentModule;
import com.boreas.listener.ClickListener;
import com.boreas.model.entity.MusicEntity;
import com.boreas.presenter.MusicPresenter;
import com.boreas.presenter.PresenterContract;
import com.boreas.ui.recycle.OffsetDecoration;

import java.util.List;

import javax.inject.Inject;

/**
 * 作者 boreas
 * 日期 18-3-14
 * 邮箱 13051089921@163.com
 *
 * @author boreas
 */

public class MusicFragment extends BaseFragment implements PresenterContract.MusicView,ClickListener<MusicEntity> {

    private FragmentMusicBinding binding = null;
    protected OffsetDecoration decoration = new OffsetDecoration();

    @Inject
    MusicPresenter presenter;

    @Override
    public View initView(LayoutInflater inflater, ViewGroup container) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_music, container, false);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity().getBaseContext());
        binding.fragmentMusicRecycle.setLayoutManager(linearLayoutManager);
        binding.fragmentMusicRecycle.setHasFixedSize(false);
        binding.fragmentMusicRecycle.removeItemDecoration(decoration);
        binding.fragmentMusicRecycle.addItemDecoration(decoration);
        initDagger();
        initPresenter();
        return binding.getRoot();
    }

    private void initPresenter() {
    }

    private void initDagger() {
        DaggerMusicFragmentComponent.builder().musicFragmentModule(new MusicFragmentModule(this)).build().inject(this);
    }

    @Override
    public void lazyFetchData() {
        presenter.getMusicList();
    }

    @Override
    public void getData(List list) {
        MusicAdapter adapter = new MusicAdapter(list);
        adapter.setOnClickListener(this);
        binding.fragmentMusicRecycle.setAdapter(adapter);
    }

    @Override
    public void onItemClick(View itemView, int position, MusicEntity musicEntity) {

    }

    @Override
    public void onItemLongClick(View itemView, int position, MusicEntity musicEntity) {

    }
}
