package com.boreas.plainlife.mvp.views.fragments.location;

import android.content.Context;
import android.widget.TextView;

import com.boreas.plainlife.R;
import com.boreas.plainlife.base.adapter.BaseRecyclerAdapter;
import com.boreas.plainlife.base.adapter.BaseRecyclerHolder;
import com.boreas.plainlife.mvp.models.location.LocationUserListModel;
import com.boreas.plainlife.utils.ImageUtil;
import com.boreas.plainlife.widget.CircleImageView;

import java.util.List;

public class LovePeopleAdapter extends BaseRecyclerAdapter<LocationUserListModel.Data> {

    public LovePeopleAdapter(Context context, List<LocationUserListModel.Data> list, int itemLayoutId) {
        super(context, list, itemLayoutId);
    }

    @Override
    public void convert(BaseRecyclerHolder holder, LocationUserListModel.Data item, int position, boolean isScrolling) {
        TextView nickName = holder.getView(R.id.nickName);
        TextView lastTime = holder.getView(R.id.lastTime);
        TextView location = holder.getView(R.id.location);
        TextView isOnline = holder.getView(R.id.isOnline);
        CircleImageView avatar = holder.getView(R.id.avatar);
        nickName.setText(item.getNickName());
        lastTime.setText(item.getNickName());
        location.setText(item.getLocation());
        ImageUtil.loadImg(super.getContext(), item.getAvatar(), avatar);
        if(item.isOnline()){
            isOnline.setText("在线");
            isOnline.setTextColor(getContext().getResources().getColor(R.color.light23_blue));
        }else{
            isOnline.setText("离线");
            isOnline.setTextColor(getContext().getResources().getColor(R.color.light3_gray));
        }
    }
}
