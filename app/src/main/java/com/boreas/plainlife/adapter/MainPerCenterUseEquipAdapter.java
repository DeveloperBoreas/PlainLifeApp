package com.boreas.plainlife.adapter;

import android.content.Context;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.boreas.plainlife.R;
import com.boreas.plainlife.base.adapter.BaseRecyclerAdapter;
import com.boreas.plainlife.base.adapter.BaseRecyclerHolder;
import com.boreas.plainlife.mvp.models.precenter.KeeperUsedDevicesModel;
import com.boreas.plainlife.utils.ScreenUtil;

import java.util.List;

public class MainPerCenterUseEquipAdapter extends BaseRecyclerAdapter<KeeperUsedDevicesModel.DataBean.RecentDevicesBean> {

    public MainPerCenterUseEquipAdapter(Context context, List<KeeperUsedDevicesModel.DataBean.RecentDevicesBean> list, int itemLayoutId) {
        super(context, list, itemLayoutId);
    }

    @Override
    public void convert(BaseRecyclerHolder holder, KeeperUsedDevicesModel.DataBean.RecentDevicesBean item, int position, boolean isScrolling) {
        ((TextView)holder.getView(R.id.iv_use_equip_devicename)).setText(item.getEquipmentName());
        Glide.with(getContext())
                .load("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1588847280973&di=58c4db7897ffcc9576fe66e252529a40&imgtype=0&src=http%3A%2F%2Fpics1.baidu.com%2Ffeed%2Fb90e7bec54e736d12fa67dcb0d3268c4d4626920.jpeg%3Ftoken%3Dd92b368c528c0d0591e59251102b1c73%26s%3Df22fb044c4d80bc43db2fd1b03008099")
//                .load(item.getEquipmentImg())
                .apply(RequestOptions.bitmapTransform(new RoundedCorners(ScreenUtil.dp2px(getContext(),2))))
                .into((ImageView) holder.getView(R.id.iv_use_equip_img));

    }
}
