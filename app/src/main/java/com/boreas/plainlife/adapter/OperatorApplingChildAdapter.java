package com.boreas.plainlife.adapter;


import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.boreas.plainlife.R;
import com.boreas.plainlife.base.adapter.BaseRecyclerAdapter;
import com.boreas.plainlife.base.adapter.BaseRecyclerHolder;

import java.util.List;

public class OperatorApplingChildAdapter extends BaseRecyclerAdapter<String> {

    public OperatorApplingChildAdapter(Context context, List<String> list, int itemLayoutId) {
        super(context, list, itemLayoutId);
    }

    @Override
    public void convert(BaseRecyclerHolder holder, String item, int position, boolean isScrolling) {
        ImageView childImage = holder.getView(R.id.childImage);
        TextView childCheckbox = holder.getView(R.id.childCheckbox);
        TextView childTitle = holder.getView(R.id.childTitle);
        TextView childGoodsLocation = holder.getView(R.id.childGoodsLocation);
        TextView currentStatu = holder.getView(R.id.currentStatu);
        View line = holder.getView(R.id.line);
        if (getListData().size() != position + 1) {
            line.setVisibility(View.VISIBLE);
        }
        childCheckbox.setVisibility(View.GONE);
        currentStatu.setVisibility(View.GONE);

    }
}
