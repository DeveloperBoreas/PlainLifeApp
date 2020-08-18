package com.xwzx.equipmanager.adapter;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.xwzx.equipmanager.R;
import com.xwzx.equipmanager.base.adapter.BaseRecyclerAdapter;
import com.xwzx.equipmanager.base.adapter.BaseRecyclerHolder;
import com.xwzx.equipmanager.framwork.ClickProxy;
import com.xwzx.equipmanager.mvp.models.DisposedModel;

import java.util.List;

public class keeperDisposedTodoAdapter extends BaseRecyclerAdapter<DisposedModel.DisposedChildModel> {

    public keeperDisposedTodoAdapter(Context context, List<DisposedModel.DisposedChildModel> list, int itemLayoutId) {
        super(context, list, itemLayoutId);
    }

    @Override
    public void convert(BaseRecyclerHolder holder, DisposedModel.DisposedChildModel item, int position,  boolean isScrolling) {
        TextView childCheckbox = holder.getView(R.id.childCheckbox);
        ImageView childImage = holder.getView(R.id.childImage);
        TextView childTitle = holder.getView(R.id.childTitle);
        TextView childGoodsLocation = holder.getView(R.id.childGoodsLocation);
        childTitle.setText(item.getTitle());
        childGoodsLocation.setText(item.getShoppinglocation());
        holder.itemView.setOnClickListener(v -> {
            childCheckbox.setSelected(!childCheckbox.isSelected());
        });
    }
}
