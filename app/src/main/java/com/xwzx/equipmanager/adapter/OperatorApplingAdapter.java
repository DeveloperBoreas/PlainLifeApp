package com.xwzx.equipmanager.adapter;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.xwzx.equipmanager.R;
import com.xwzx.equipmanager.base.adapter.BaseRecyclerAdapter;
import com.xwzx.equipmanager.base.adapter.BaseRecyclerHolder;
import com.xwzx.equipmanager.framwork.FullyLinearLayoutManager;
import com.xwzx.equipmanager.mvp.models.MUpComingModel;
import com.xwzx.equipmanager.mvp.models.precenter.OperatorApplicationModel;

import java.util.ArrayList;
import java.util.List;

public class OperatorApplingAdapter extends BaseRecyclerAdapter<OperatorApplicationModel.DataBean.ApplyBean> {


    public OperatorApplingAdapter(Context context, List<OperatorApplicationModel.DataBean.ApplyBean> list, int itemLayoutId) {
        super(context, list, itemLayoutId);
    }

    @Override
    public void convert(BaseRecyclerHolder holder, OperatorApplicationModel.DataBean.ApplyBean item, int position, boolean isScrolling) {
        ImageView childImage = holder.getView(R.id.childImage);
        TextView childTitle = holder.getView(R.id.childTitle);
        TextView childGoodsLocation = holder.getView(R.id.childGoodsLocation);
//        childTitle.setText("20200978入室抢劫");
        childTitle.setText(item.getCaseName()+"");
        childGoodsLocation.setText("申请人：李国良");
        TextView applyDate = holder.getView(R.id.applyDate);
        TextView cuiban = holder.getView(R.id.cuiban);
        TextView chexiao = holder.getView(R.id.chexiao);
        RecyclerView recycleItem = holder.getView(R.id.list);
        setData(recycleItem);
    }

    private void setData(RecyclerView recyclerView) {
        ArrayList<String> dataList = new ArrayList<>();
        dataList.add("1");
        dataList.add("1");
        dataList.add("1");
        dataList.add("1");
        recyclerView.setLayoutManager(new FullyLinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        recyclerView.setHasFixedSize(false);
        OperatorApplingChildAdapter adapter = new OperatorApplingChildAdapter(getContext(),dataList,R.layout.disposed_item_child);
        recyclerView.setAdapter(adapter);
    }
}
