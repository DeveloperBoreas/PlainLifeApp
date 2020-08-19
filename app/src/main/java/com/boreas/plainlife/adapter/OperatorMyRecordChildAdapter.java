package com.boreas.plainlife.adapter;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.boreas.plainlife.R;
import com.boreas.plainlife.base.adapter.BaseRecyclerAdapter;
import com.boreas.plainlife.base.adapter.BaseRecyclerHolder;
import com.boreas.plainlife.mvp.models.precenter.OperatorAllRecordModel;
import com.boreas.plainlife.utils.ImageUtil;

import java.util.List;

import static com.boreas.plainlife.adapter.OperatorMyRecordParentAdapter.*;

public class OperatorMyRecordChildAdapter extends BaseRecyclerAdapter<OperatorAllRecordModel.DataBeanXXX.InnerBean.DataBean> {
    private int type;
    public OperatorMyRecordChildAdapter(Context context, List<OperatorAllRecordModel.DataBeanXXX.InnerBean.DataBean> list, int itemLayoutId,int type) {
        super(context, list, itemLayoutId);
        this.type = type;
    }

    @Override
    public void convert(BaseRecyclerHolder holder, OperatorAllRecordModel.DataBeanXXX.InnerBean.DataBean item, int position, boolean isScrolling) {
        TextView deviceStatus = holder.getView(R.id.deviceStatus);
        TextView devicePosition = holder.getView(R.id.tv_equip_position);
        TextView deviceOperater = holder.getView(R.id.tv_return);
        ImageView deviceImg = holder.getView(R.id.iv_equip_img);
        TextView deviceName = holder.getView(R.id.tv_equip_name);
        deviceName.setText(item.getEquipmentName() + "");
        devicePosition.setText("暂无");
        if(type == BACK || type == BACKED){
            deviceOperater.setVisibility(View.VISIBLE);
        }
        ImageUtil.loadImg(getContext(), item.getEquipmentImg(), deviceImg);
        if (position == 0) {
            holder.getView(R.id.v_line).setVisibility(View.INVISIBLE);
        } else {
            holder.getView(R.id.v_line).setVisibility(View.VISIBLE);
        }
    }
}
