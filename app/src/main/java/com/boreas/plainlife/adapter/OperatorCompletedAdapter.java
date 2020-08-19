package com.boreas.plainlife.adapter;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.boreas.plainlife.R;
import com.boreas.plainlife.base.adapter.BaseRecyclerAdapter;
import com.boreas.plainlife.base.adapter.BaseRecyclerHolder;
import com.boreas.plainlife.framwork.FullyLinearLayoutManager;
import com.boreas.plainlife.mvp.models.precenter.OperatorApplicationModel;

import java.util.ArrayList;
import java.util.List;

public class OperatorCompletedAdapter extends BaseRecyclerAdapter<OperatorApplicationModel.DataBean.AgreeBean> {

    public OperatorCompletedAdapter(Context context, List<OperatorApplicationModel.DataBean.AgreeBean> list, int itemLayoutId) {
        super(context, list, itemLayoutId);
    }

    @Override
    public void convert(BaseRecyclerHolder holder, OperatorApplicationModel.DataBean.AgreeBean item, int position, boolean isScrolling) {
        ImageView childImage = holder.getView(R.id.childImage);
        TextView childTitle = holder.getView(R.id.childTitle);
        TextView childGoodsLocation = holder.getView(R.id.childGoodsLocation);
        childTitle.setText("20200978入室抢劫");
        childGoodsLocation.setText("申请人：李国良");
        TextView applyDate = holder.getView(R.id.applyDate);
        TextView cuiban = holder.getView(R.id.cuiban);
        TextView chexiao = holder.getView(R.id.chexiao);
        cuiban.setVisibility(View.GONE);
        chexiao.setVisibility(View.GONE);
        RecyclerView recycleItem = holder.getView(R.id.list);
        setData(recycleItem);
    }

    private void setData(RecyclerView recyclerView) {
        ArrayList<String> dataList = new ArrayList<>();
        dataList.add("1");
        dataList.add("2");
        dataList.add("3");
        dataList.add("4");
        recyclerView.setLayoutManager(new FullyLinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        recyclerView.setHasFixedSize(false);
        OperatorCompletedChildAdapter adapter = new OperatorCompletedChildAdapter(getContext(), dataList, R.layout.disposed_item_child);
        recyclerView.setAdapter(adapter);
    }
}
