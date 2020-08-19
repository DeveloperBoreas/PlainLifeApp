package com.boreas.plainlife.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.boreas.plainlife.R;
import com.boreas.plainlife.base.adapter.BaseRecyclerAdapter;
import com.boreas.plainlife.base.adapter.BaseRecyclerHolder;

import java.util.List;

public class KeeperApplingChildAdapter extends BaseRecyclerAdapter<String> {

    public KeeperApplingChildAdapter(Context context, List<String> list, int itemLayoutId) {
        super(context, list, itemLayoutId);
    }

    @Override
    public void convert(BaseRecyclerHolder holder, String item, int position, boolean isScrolling) {
        ViewGroup.LayoutParams layoutParams = holder.itemView.getLayoutParams();
        layoutParams.height = (int)getContext().getResources().getDimension(R.dimen.main_bottomNavigtion_height_20);
        holder.itemView.setLayoutParams(layoutParams);
        TextView childCheckbox = holder.getView(R.id.childCheckbox);
        TextView currentStatu = holder.getView(R.id.currentStatu);
        ImageView childImage = holder.getView(R.id.childImage);
        TextView childTitle = holder.getView(R.id.childTitle);
        View line = holder.getView(R.id.line);
        if (getListData().size() != position + 1) {
            line.setVisibility(View.VISIBLE);
        }
        TextView childGoodsLocation = holder.getView(R.id.childGoodsLocation);
        childCheckbox.setVisibility(View.GONE);
        childTitle.setText(item);
        currentStatu.setText("出库");
        childGoodsLocation.setText(item);
    }
}
