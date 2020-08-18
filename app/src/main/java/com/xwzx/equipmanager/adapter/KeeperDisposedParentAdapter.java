package com.xwzx.equipmanager.adapter;

import android.content.Context;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.xwzx.equipmanager.R;
import com.xwzx.equipmanager.base.adapter.BaseRecyclerAdapter;
import com.xwzx.equipmanager.base.adapter.BaseRecyclerHolder;
import com.xwzx.equipmanager.framwork.FullyLinearLayoutManager;
import com.xwzx.equipmanager.mvp.models.DisposedModel;

import java.util.List;

public class KeeperDisposedParentAdapter extends BaseRecyclerAdapter<DisposedModel> {

    public KeeperDisposedParentAdapter(Context context, List<DisposedModel> list, int itemLayoutId) {
        super(context, list, itemLayoutId);
    }

    @Override
    public void convert(BaseRecyclerHolder holder, DisposedModel item, int position,  boolean isScrolling) {
        ImageView personIcon = holder.getView(R.id.personIcon);
        TextView statu = holder.getView(R.id.statu);
        TextView title = holder.getView(R.id.title);
        TextView proposer = holder.getView(R.id.proposer);
        RecyclerView childList = holder.getView(R.id.childList);
        title.setText(item.getTitle());
        proposer.setText(item.getPorposer());
        childList.setLayoutManager(new FullyLinearLayoutManager(getContext()));
        childList.setHasFixedSize(false);
        keeperDisposedChildAdapter shoppingChildAdapter = new keeperDisposedChildAdapter(getContext(), item.getDisposedDeivceList(), R.layout.disposed_item_child);
        childList.setAdapter(shoppingChildAdapter);
    }
}
