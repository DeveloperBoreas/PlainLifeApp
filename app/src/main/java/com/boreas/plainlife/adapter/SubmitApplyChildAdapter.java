package com.boreas.plainlife.adapter;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.boreas.plainlife.R;
import com.boreas.plainlife.base.adapter.BaseRecyclerAdapter;
import com.boreas.plainlife.base.adapter.BaseRecyclerHolder;
import com.boreas.plainlife.mvp.models.ShoppingModel;
import com.boreas.plainlife.utils.ImageUtil;

import java.util.List;

public class SubmitApplyChildAdapter extends BaseRecyclerAdapter<ShoppingModel.ShoppingChildModel> {

    public SubmitApplyChildAdapter(Context context, List<ShoppingModel.ShoppingChildModel> list, int itemLayoutId) {
        super(context, list, itemLayoutId);
    }

    @Override
    public void convert(BaseRecyclerHolder holder, ShoppingModel.ShoppingChildModel item, int position, boolean isScrolling) {
        TextView childCheckbox = holder.getView(R.id.childCheckbox);
        childCheckbox.setVisibility(View.GONE);
        ImageView childImage = holder.getView(R.id.childImage);
        TextView childTitle = holder.getView(R.id.childTitle);
        TextView childGoodsLocation = holder.getView(R.id.childGoodsLocation);
        childCheckbox.setSelected(item.isChecked());
        childTitle.setText(item.getTitle());
        childGoodsLocation.setText(item.getGoodsLocation());
        ImageUtil.loadImg(getContext(),item.getImgUrl(),childImage);
    }
}
