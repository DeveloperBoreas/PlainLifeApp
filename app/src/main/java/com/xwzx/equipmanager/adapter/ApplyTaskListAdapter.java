package com.xwzx.equipmanager.adapter;

import android.content.Context;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.xwzx.equipmanager.R;
import com.xwzx.equipmanager.base.adapter.BaseRecyclerAdapter;
import com.xwzx.equipmanager.base.adapter.BaseRecyclerHolder;
import com.xwzx.equipmanager.mvp.models.submit.FindCaseByUserModel;
import com.xwzx.equipmanager.mvp.views.activitys.MessageActivity;

import java.util.ArrayList;
import java.util.List;

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
