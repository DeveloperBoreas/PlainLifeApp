package com.boreas.plainlife.adapter;

import android.content.Context;
import android.widget.CheckBox;

import com.boreas.plainlife.R;
import com.boreas.plainlife.base.adapter.BaseRecyclerAdapter;
import com.boreas.plainlife.base.adapter.BaseRecyclerHolder;
import com.boreas.plainlife.mvp.models.submit.FindCaseByUserModel;

import java.util.ArrayList;

public class ApplyTaskListAdapter extends BaseRecyclerAdapter<FindCaseByUserModel.TaskItemModel> {

    public ApplyTaskListAdapter(Context context, ArrayList<FindCaseByUserModel.TaskItemModel> list, int itemLayoutId) {
        super(context, list, itemLayoutId);
    }

    @Override
    public void convert(BaseRecyclerHolder holder, FindCaseByUserModel.TaskItemModel item, int position, boolean isScrolling) {
        CheckBox checkBox = holder.getView(R.id.cb_item);
        checkBox.setText(item.getName());
        checkBox.setChecked(item.isCheck());
    }
}
