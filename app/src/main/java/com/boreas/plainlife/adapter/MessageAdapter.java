package com.boreas.plainlife.adapter;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.boreas.plainlife.R;
import com.boreas.plainlife.base.adapter.BaseRecyclerAdapter;
import com.boreas.plainlife.base.adapter.BaseRecyclerHolder;
import com.boreas.plainlife.mvp.views.activitys.MessageActivity;

import java.util.List;

public class MessageAdapter extends BaseRecyclerAdapter<MessageActivity.MessageModel> {

    public MessageAdapter(Context context, List<MessageActivity.MessageModel> list, int itemLayoutId) {
        super(context, list, itemLayoutId);
    }

    @Override
    public void convert(BaseRecyclerHolder holder, MessageActivity.MessageModel item, int position, boolean isScrolling) {
        ImageView isNew = holder.getView(R.id.isNew);
        ImageView tip = holder.getView(R.id.tip);
        TextView title = holder.getView(R.id.title);
        TextView date = holder.getView(R.id.date);
        TextView message = holder.getView(R.id.message);
        title.setText(item.getTitle());
        date.setText(item.getDate());
        message.setText(item.getMessage());
        isNew.setVisibility(item.isNew() ? View.VISIBLE : View.GONE);
        if (position % 2 == 0) {//紫色
            holder.itemView.setBackground(getContext().getResources().getDrawable(R.drawable.shopping_parent_item_shape));
        } else {                //普通蓝色
            holder.itemView.setBackground(getContext().getResources().getDrawable(R.drawable.shopping_parent_item_pueple_shape));
        }
    }
}
