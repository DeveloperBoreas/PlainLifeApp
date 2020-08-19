package com.boreas.plainlife.adapter;



import android.content.Context;

import com.boreas.plainlife.R;
import com.boreas.plainlife.base.adapter.BaseRecyclerAdapter;
import com.boreas.plainlife.base.adapter.BaseRecyclerHolder;
import com.boreas.plainlife.widget.mIndicator.IndicatorSeekBar;

import java.util.List;


public class KeeperMyOperationItemAdapter extends BaseRecyclerAdapter {
    String[] arr = {"提交申请 ", "提交申请 ", "提交申请 ", "提交申请 ", "提交申请 ","完成"};
    String[] arr1 = {"04.01 10:30:21","04.01 10:30:21", "04.01 10:30:21", "04.01 10:30:21", "04.01 10:30:21", ""};
    public KeeperMyOperationItemAdapter(Context context, List list, int itemLayoutId) {
        super(context, list, itemLayoutId);
    }

    @Override
    public void convert(BaseRecyclerHolder holder, Object item, int position, boolean isScrolling) {
        IndicatorSeekBar seekBar = holder.getView(R.id.custom_text);
        seekBar.setTickCount(6);
        seekBar.setMax(6*10);
        seekBar.setProgress(6*10/3);
        seekBar.customTickTexts(arr);
        seekBar.customTickTimeTexts(arr1);

    }

}
