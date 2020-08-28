package com.boreas.plainlife.mvp.views.fragments.location;

import android.content.Context;

import com.boreas.plainlife.base.adapter.BaseRecyclerAdapter;
import com.boreas.plainlife.base.adapter.BaseRecyclerHolder;

import java.util.List;

public class LovePeopleAdapter extends BaseRecyclerAdapter<String> {

    public LovePeopleAdapter(Context context, List<String> list, int itemLayoutId) {
        super(context, list, itemLayoutId);
    }

    @Override
    public void convert(BaseRecyclerHolder holder, String item, int position, boolean isScrolling) {

    }
}
