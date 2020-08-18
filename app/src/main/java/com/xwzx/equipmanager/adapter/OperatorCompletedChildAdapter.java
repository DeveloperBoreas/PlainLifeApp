package com.xwzx.equipmanager.adapter;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.xwzx.equipmanager.R;
import com.xwzx.equipmanager.base.adapter.BaseRecyclerAdapter;
import com.xwzx.equipmanager.base.adapter.BaseRecyclerHolder;

import java.util.List;

public class OperatorCompletedChildAdapter extends BaseRecyclerAdapter<String> {

    public OperatorCompletedChildAdapter(Context context, List<String> list, int itemLayoutId) {
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
