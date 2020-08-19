package com.boreas.plainlife.mvp.views.fragments;

import android.content.Intent;
import android.view.View;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.boreas.plainlife.R;
import com.boreas.plainlife.adapter.KeeperUpComingAdapter;
import com.boreas.plainlife.base.BaseFragment;
import com.boreas.plainlife.base.adapter.BaseRecyclerAdapter;
import com.boreas.plainlife.databinding.FragmentMUpcomingBinding;
import com.boreas.plainlife.mvp.models.MUpComingModel;
import com.boreas.plainlife.mvp.views.activitys.DisposeTodoActivity;

import java.util.ArrayList;

public class keeperUpComingFragment extends BaseFragment<FragmentMUpcomingBinding> implements BaseRecyclerAdapter.OnItemClickListener {

    private ArrayList<MUpComingModel> comingModels;

    @Override
    public void lazyFetchData() {

    }

    @Override
    public int setContent() {
        return R.layout.fragment_m_upcoming;
    }

    @Override
    public void initView() {
        this.initData();
        this.binding.gridList.setHasFixedSize(false);
        this.binding.gridList.setLayoutManager(new GridLayoutManager(getContext(), 2));
        this.binding.gridList.setAdapter(new KeeperUpComingAdapter(getContext(), this.comingModels,R.layout.upcoming_list_item).setOnItemClickListener(this));
    }

    private void initData() {
        this.comingModels = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            comingModels.add(new MUpComingModel(i, i < 2 ? 1:0,"标题"+i,"张三",System.currentTimeMillis()));
        }
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

    @Override
    public void onItemClick(RecyclerView parent, View view, int position) {
        Intent intent = new Intent(getActivity(), DisposeTodoActivity.class);
        startActivity(intent);
    }
}
