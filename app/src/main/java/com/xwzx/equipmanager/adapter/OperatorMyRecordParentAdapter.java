package com.xwzx.equipmanager.adapter;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CircleCrop;
import com.bumptech.glide.request.RequestOptions;
import com.xwzx.equipmanager.R;
import com.xwzx.equipmanager.base.adapter.BaseRecyclerAdapter;
import com.xwzx.equipmanager.base.adapter.BaseRecyclerHolder;
import com.xwzx.equipmanager.framwork.FullyLinearLayoutManager;
import com.xwzx.equipmanager.mvp.models.precenter.OperatorAllRecordModel;
import com.xwzx.equipmanager.utils.ImageUtil;

import java.util.ArrayList;
import java.util.List;

public class OperatorMyRecordParentAdapter extends BaseRecyclerAdapter<OperatorAllRecordModel.DataBeanXXX.InnerBean> {
        //("待取",1),
        //("取出",2),
        //("归还",0);
        //("逾期",3);
    public static final int RETURNED = 0;
    public static  final int TAKE = 1;
    public static  final int BACK = 2;
    public static  final int BACKED = 3;
    public OperatorMyRecordParentAdapter(Context context, List<OperatorAllRecordModel.DataBeanXXX.InnerBean> list, int itemLayoutId) {
        super(context, list, itemLayoutId);
    }

    @Override
    public void convert(BaseRecyclerHolder holder, OperatorAllRecordModel.DataBeanXXX.InnerBean item, int position, boolean isScrolling) {
        TextView caseName = holder.getView(R.id.tv_case_name);
        ImageView caseImg = holder.getView(R.id.iv_applicat_img);
        TextView taskName = holder.getView(R.id.tv_applicat_name);
        TextView taskEquipMentStatus = holder.getView(R.id.tv_status);
        caseName.setText(item.getCaseName() + "");
        taskName.setText(item.getTaskName() + "");
        if (item.getType() == RETURNED) {
            taskEquipMentStatus.setText("已归还");
        }else if(item.getType() == TAKE){
            taskEquipMentStatus.setText("待领取");
        }else if(item.getType() == BACK){
            taskEquipMentStatus.setText("已领取");
        }else if(item.getType() == BACKED){
            taskEquipMentStatus.setText("已逾期");
        }else{
            taskEquipMentStatus.setVisibility(View.GONE);
        }
        Glide.with(getContext())
                .load("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1588847280973&di=58c4db7897ffcc9576fe66e252529a40&imgtype=0&src=http%3A%2F%2Fpics1.baidu.com%2Ffeed%2Fb90e7bec54e736d12fa67dcb0d3268c4d4626920.jpeg%3Ftoken%3Dd92b368c528c0d0591e59251102b1c73%26s%3Df22fb044c4d80bc43db2fd1b03008099")
                .apply(RequestOptions.bitmapTransform(new CircleCrop()))
                .into(caseImg);
        List<OperatorAllRecordModel.DataBeanXXX.InnerBean.DataBean> equipData = item.getData();
        RecyclerView childList = holder.getView(R.id.recycle_my_record_child);
        childList.setLayoutManager(new FullyLinearLayoutManager(getContext()));
        childList.setHasFixedSize(false);
        OperatorMyRecordChildAdapter adapter = new OperatorMyRecordChildAdapter(getContext(), equipData, R.layout.recycle_operator_my_record_child_item,item.getType());
        childList.setAdapter(adapter);
    }
}
