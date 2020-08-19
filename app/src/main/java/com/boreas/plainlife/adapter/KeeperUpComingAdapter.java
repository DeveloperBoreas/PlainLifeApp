package com.boreas.plainlife.adapter;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.boreas.plainlife.R;
import com.boreas.plainlife.base.adapter.BaseRecyclerAdapter;
import com.boreas.plainlife.base.adapter.BaseRecyclerHolder;
import com.boreas.plainlife.framwork.ClickProxy;
import com.boreas.plainlife.mvp.models.MUpComingModel;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class KeeperUpComingAdapter extends BaseRecyclerAdapter<MUpComingModel> {

    public KeeperUpComingAdapter(Context context, List<MUpComingModel> list, int itemLayoutId) {
        super(context, list, itemLayoutId);
    }

    @Override
    public void convert(BaseRecyclerHolder holder, MUpComingModel item, int position, boolean isScrolling) {
        TextView title = holder.getView(R.id.title);
        TextView poster = holder.getView(R.id.poster);
        TextView applyDate = holder.getView(R.id.applyDate);
        ImageView marker = holder.getView(R.id.marker);
        marker.setVisibility(item.getUrgent() == 1? View.VISIBLE:View.GONE);
        title.setText(item.getTitle());
        poster.setText(item.getProposer());
        applyDate.setText(new SimpleDateFormat("yyyy.MM.dd").format(new Date(item.getApplyDate())));
        holder.getView(R.id.gotoDispose).setOnClickListener(new ClickProxy(v -> {

        }));
    }
}