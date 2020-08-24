package com.boreas.plainlife.mvp.views.fragments.picnote;

import android.content.Context;
import android.widget.TextView;

import com.boreas.plainlife.R;
import com.boreas.plainlife.base.adapter.BaseRecyclerAdapter;
import com.boreas.plainlife.base.adapter.BaseRecyclerHolder;

import java.util.List;

public class NoteListAdapter extends BaseRecyclerAdapter<String> {

    public NoteListAdapter(Context context, List<String> list, int itemLayoutId) {
        super(context, list, itemLayoutId);
    }

    @Override
    public void convert(BaseRecyclerHolder holder, String item, int position, boolean isScrolling) {
        ((TextView)holder.itemView).setText(item);
    }
}
