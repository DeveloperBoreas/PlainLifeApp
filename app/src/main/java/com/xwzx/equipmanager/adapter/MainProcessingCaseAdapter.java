package com.xwzx.equipmanager.adapter;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.xwzx.equipmanager.R;
import com.xwzx.equipmanager.base.adapter.BaseRecyclerAdapter;
import com.xwzx.equipmanager.base.adapter.BaseRecyclerHolder;
import com.xwzx.equipmanager.mvp.models.indexoperator.TaskModel;

import java.util.ArrayList;
import java.util.List;

public class MainProcessingCaseAdapter extends BaseRecyclerAdapter<TaskModel.DataBeanX.DataBean.TasksBean> {
    public MainProcessingCaseAdapter(Context context, List<TaskModel.DataBeanX.DataBean.TasksBean> list, int itemLayoutId) {
        super(context, list, itemLayoutId);
    }

    @Override
    public void convert(BaseRecyclerHolder holder, TaskModel.DataBeanX.DataBean.TasksBean item, int position, boolean isScrolling) {
        RecyclerView recyclerView = holder.getView(R.id.rv_case_equip_imglist);
        TextView caseName = holder.getView(R.id.tv_case_name);
        TextView status = holder.getView(R.id.tv_case_status);
        status.setVisibility(View.GONE);
        TextView returnTime = holder.getView(R.id.tv_return_time);
        caseName.setText(item.getName()+"");
        returnTime.setText("创建时间："+item.getCreate_time()+"");
        recyclerView.setHasFixedSize(true);
        ArrayList<String> imgUrls = new ArrayList<>();
        for (TaskModel.DataBeanX.DataBean.TasksBean.EquipmentBean equipmentBean : item.getEquipment()) {
            imgUrls.add(equipmentBean.getEquipment_img());
        }
        ShowImgAdapter adapter = new ShowImgAdapter(getContext(), imgUrls, R.layout.recycle_case_child_item_layout);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
        recyclerView.setAdapter(adapter);

    }
}
