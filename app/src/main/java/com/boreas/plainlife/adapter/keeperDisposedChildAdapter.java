package com.boreas.plainlife.adapter;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.boreas.plainlife.R;
import com.boreas.plainlife.base.adapter.BaseRecyclerAdapter;
import com.boreas.plainlife.base.adapter.BaseRecyclerHolder;
import com.boreas.plainlife.mvp.models.DisposedModel;

import java.util.List;

public class keeperDisposedChildAdapter extends BaseRecyclerAdapter<DisposedModel.DisposedChildModel> {

    public keeperDisposedChildAdapter(Context context, List<DisposedModel.DisposedChildModel> list, int itemLayoutId) {
        super(context, list, itemLayoutId);
    }

    @Override
    public void convert(BaseRecyclerHolder holder, DisposedModel.DisposedChildModel item, int position,  boolean isScrolling) {
        TextView childCheckbox = holder.getView(R.id.childCheckbox);
        ImageView childImage = holder.getView(R.id.childImage);
        TextView childTitle = holder.getView(R.id.childTitle);
        TextView childGoodsLocation = holder.getView(R.id.childGoodsLocation);
        childCheckbox.setVisibility(View.GONE);
        childTitle.setText(item.getTitle());
        childGoodsLocation.setText(item.getShoppinglocation());
    }
}
