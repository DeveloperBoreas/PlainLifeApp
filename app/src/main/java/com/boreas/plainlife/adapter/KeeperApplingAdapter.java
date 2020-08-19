package com.boreas.plainlife.adapter;

import android.content.Context;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.boreas.plainlife.R;
import com.boreas.plainlife.base.adapter.BaseRecyclerAdapter;
import com.boreas.plainlife.base.adapter.BaseRecyclerHolder;
import com.boreas.plainlife.mvp.models.MUpComingModel;

import java.util.ArrayList;
import java.util.List;

public class KeeperApplingAdapter extends BaseRecyclerAdapter<MUpComingModel> {

    public KeeperApplingAdapter(Context context, List<MUpComingModel> list, int itemLayoutId) {
        super(context, list, itemLayoutId);
    }

    @Override
    public void convert(BaseRecyclerHolder holder, MUpComingModel item, int position, boolean isScrolling) {
        TextView chexiao = holder.getView(R.id.chexiao);
        TextView cuiban  = holder.getView(R.id.cuiban);
        TextView applyDate  = holder.getView(R.id.applyDate);
        RecyclerView list= holder.getView(R.id.list);
        list.setHasFixedSize(false);
        list.setLayoutManager(new LinearLayoutManager(getContext()));
        List<String> stringList = new ArrayList<>();
        stringList.add("设备");
        stringList.add("设备");
        list.setAdapter(new KeeperApplingChildAdapter(getContext(),stringList,R.layout.disposed_item_child));
    }
}
