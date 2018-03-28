package com.boreas.ui.recycle;

import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.boreas.utils.ScreenUtil;

/**
 * 作者 boreas
 * 日期 18-3-15
 * 邮箱 13051089921@163.com
 */

public class OffsetDecoration extends RecyclerView.ItemDecoration {

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);
        int space = ScreenUtil.dp2px(parent.getContext(), 8);
        if (parent.getChildAdapterPosition(view) == 0) {
            outRect.set(space, space, space, space);
        } else {
            outRect.set(space, 0, space, space);
        }
    }

}
