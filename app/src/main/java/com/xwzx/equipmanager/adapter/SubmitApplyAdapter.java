package com.xwzx.equipmanager.adapter;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.orhanobut.logger.Logger;
import com.xwzx.equipmanager.R;
import com.xwzx.equipmanager.base.adapter.BaseRecyclerAdapter;
import com.xwzx.equipmanager.base.adapter.BaseRecyclerHolder;
import com.xwzx.equipmanager.framwork.FullyLinearLayoutManager;
import com.xwzx.equipmanager.mvp.models.ShoppingModel;

import java.util.List;

public class SubmitApplyAdapter extends BaseRecyclerAdapter<ShoppingModel> {

    public SubmitApplyAdapter(Context context, List<ShoppingModel> list, int itemLayoutId) {
        super(context, list, itemLayoutId);
    }

    @Override
    public void convert(BaseRecyclerHolder holder, ShoppingModel item, int position, boolean isScrolling) {
        if (item.getChildModels() == null || item.getChildModels().size() < 0) {
            return;
        }
        RecyclerView childList = holder.getView(R.id.childList);
        TextView checkBox = holder.getView(R.id.checkbox);
        checkBox.setVisibility(View.GONE);
        TextView title = holder.getView(R.id.title);
        checkBox.setSelected(item.isChecked());
        title.setText(item.getTitle());
        childList.setLayoutManager(new FullyLinearLayoutManager(getContext()));
        childList.setHasFixedSize(false);
        childList.setAdapter(new SubmitApplyChildAdapter(getContext(), item.getChildModels(), R.layout.activity_shopping_item_child));
        holder.itemView.setBackground(getContext().getResources().getDrawable(R.drawable.applying_shape));
    }
}
