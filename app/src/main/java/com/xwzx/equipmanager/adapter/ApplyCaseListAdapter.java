package com.xwzx.equipmanager.adapter;

import android.content.Context;
import android.widget.CheckBox;

import com.xwzx.equipmanager.R;
import com.xwzx.equipmanager.base.adapter.BaseRecyclerAdapter;
import com.xwzx.equipmanager.base.adapter.BaseRecyclerHolder;
import com.xwzx.equipmanager.mvp.models.submit.FindCaseByUserModel;

import java.util.ArrayList;

public class ApplyCaseListAdapter extends BaseRecyclerAdapter<FindCaseByUserModel.CaseItemModel> {

    public ApplyCaseListAdapter(Context context, ArrayList<FindCaseByUserModel.CaseItemModel> list, int itemLayoutId) {
        super(context, list, itemLayoutId);
    }

    @Override
    public void convert(BaseRecyclerHolder holder, FindCaseByUserModel.CaseItemModel item, int position, boolean isScrolling) {
        CheckBox checkBox = holder.getView(R.id.cb_item);
        checkBox.setText(item.getName());
        checkBox.setChecked(item.isCheck());
    }
}
