package com.boreas.plainlife.adapter;

import android.content.Context;

import com.boreas.plainlife.R;
import com.boreas.plainlife.base.adapter.BaseRecyclerAdapter;
import com.boreas.plainlife.base.adapter.BaseRecyclerHolder;

import java.util.List;

public class ShowImgAdapter extends BaseRecyclerAdapter<String> {

    public ShowImgAdapter(Context context, List<String> list, int itemLayoutId) {
        super(context, list, itemLayoutId);
    }

    @Override
    public void convert(BaseRecyclerHolder holder, String item, int position, boolean isScrolling) {
        holder.setImageByUrl(R.id.iv_equip_item_img,item);
    }
}
