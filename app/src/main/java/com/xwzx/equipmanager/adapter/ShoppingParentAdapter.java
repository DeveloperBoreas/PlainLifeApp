package com.xwzx.equipmanager.adapter;

import android.content.Context;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.xwzx.equipmanager.R;
import com.xwzx.equipmanager.base.adapter.BaseRecyclerAdapter;
import com.xwzx.equipmanager.base.adapter.BaseRecyclerHolder;
import com.xwzx.equipmanager.events.ShopingBottomNumEvent;
import com.xwzx.equipmanager.framwork.FullyLinearLayoutManager;
import com.xwzx.equipmanager.mvp.models.ShoppingModel;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

public class ShoppingParentAdapter extends BaseRecyclerAdapter<ShoppingModel> {

    public ShoppingParentAdapter(Context context, List<ShoppingModel> list, int itemLayoutId) {
        super(context, list, itemLayoutId);
    }

    @Override
    public void convert(BaseRecyclerHolder holder, ShoppingModel item, int position, boolean isScrolling) {
        if (item.getChildModels() == null || item.getChildModels().size() < 0) {
            return;
        }
        RecyclerView childList = holder.getView(R.id.childList);
        TextView checkBox = holder.getView(R.id.checkbox);
        TextView title = holder.getView(R.id.title);
        checkBox.setSelected(item.isChecked());
        title.setText(item.getTitle());
        childList.setLayoutManager(new FullyLinearLayoutManager(getContext()));
        childList.setHasFixedSize(false);
        ShoppingChildAdapter shoppingChildAdapter = new ShoppingChildAdapter(getContext(), item.getChildModels(), R.layout.activity_shopping_item_child);
        childList.setAdapter(shoppingChildAdapter);
        AtomicBoolean isChecked = new AtomicBoolean(false);
        shoppingChildAdapter.setOnItemClickListener((parent, view, position1) -> {
            isChecked.set(false);
            item.getChildModels().get(position1).setChecked(!item.getChildModels().get(position1).isChecked());
            for (ShoppingModel.ShoppingChildModel childModel : item.getChildModels()) {
                if (childModel.isChecked()) {
                    isChecked.set(true);
                }
            }
            item.setChecked(isChecked.get());
            shoppingChildAdapter.notifyDataSetChanged();
            EventBus.getDefault().post(new ShopingBottomNumEvent());
            this.notifyDataSetChanged();
        });
        if (position % 2 == 0) {//紫色
            holder.itemView.setBackground(getContext().getResources().getDrawable(R.drawable.shopping_parent_item_pueple_shape));
        } else {                //普通蓝色
            holder.itemView.setBackground(getContext().getResources().getDrawable(R.drawable.shopping_parent_item_shape));
        }
    }

    public void updateCheckedState(ShoppingModel shoppingModel) {
        ArrayList<ShoppingModel.ShoppingChildModel> childModels = shoppingModel.getChildModels();
        for (int j = 0, jsize = childModels.size(); j < jsize; j++) {
            childModels.get(j).setChecked(shoppingModel.isChecked());
        }
        EventBus.getDefault().post(new ShopingBottomNumEvent());
        this.notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
       return super.getListItemCount();
    }

    @Override
    public int getItemViewType(int position) {
       return super.getListItemViewType(position);
    }
}
