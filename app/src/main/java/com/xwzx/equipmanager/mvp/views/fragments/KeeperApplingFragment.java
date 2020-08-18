package com.xwzx.equipmanager.mvp.views.fragments;

import android.content.Intent;
import android.view.View;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.xwzx.equipmanager.R;
import com.xwzx.equipmanager.adapter.KeeperApplingAdapter;
import com.xwzx.equipmanager.base.BaseFragment;
import com.xwzx.equipmanager.base.adapter.BaseRecyclerAdapter;
import com.xwzx.equipmanager.databinding.FragmentCommonListBinding;
import com.xwzx.equipmanager.mvp.models.MUpComingModel;
import com.xwzx.equipmanager.mvp.views.activitys.DisposeTodoActivity;

import java.util.ArrayList;

/**
 * 申请中
 */
public class KeeperApplingFragment extends BaseFragment<FragmentCommonListBinding> implements BaseRecyclerAdapter.OnItemClickListener {

    private ArrayList<MUpComingModel> comingModels;

    @Override
    public void lazyFetchData() {

    }

    @Override
    public int setContent() {
        return R.layout.fragment_common_list;
    }

    @Override
    public void initView() {
        this.initData();
        this.binding.list.setHasFixedSize(false);
        this.binding.list.setLayoutManager(new LinearLayoutManager(getContext()));
        this.binding.list.setAdapter(new KeeperApplingAdapter(getContext(), this.comingModels,R.layout.keeper_appling_item).setOnItemClickListener(this));
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
