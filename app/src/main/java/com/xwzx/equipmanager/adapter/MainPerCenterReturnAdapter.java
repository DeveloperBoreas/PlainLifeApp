package com.xwzx.equipmanager.adapter;

import android.content.Context;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CircleCrop;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.xwzx.equipmanager.R;
import com.xwzx.equipmanager.base.adapter.BaseRecyclerAdapter;
import com.xwzx.equipmanager.base.adapter.BaseRecyclerHolder;
import com.xwzx.equipmanager.utils.ScreenUtil;

import java.util.List;

public class MainPerCenterReturnAdapter extends BaseRecyclerAdapter<String> {


    public MainPerCenterReturnAdapter(Context context, List<String> list, int itemLayoutId) {
        super(context, list, itemLayoutId);
    }

    @Override
    public void convert(BaseRecyclerHolder holder, String item, int position, boolean isScrolling) {
        Glide.with(getContext())
                .load("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1588847280973&di=58c4db7897ffcc9576fe66e252529a40&imgtype=0&src=http%3A%2F%2Fpics1.baidu.com%2Ffeed%2Fb90e7bec54e736d12fa67dcb0d3268c4d4626920.jpeg%3Ftoken%3Dd92b368c528c0d0591e59251102b1c73%26s%3Df22fb044c4d80bc43db2fd1b03008099")
                .apply(RequestOptions.bitmapTransform(new RoundedCorners(ScreenUtil.dp2px(getContext(),2))))
                .into((ImageView) holder.getView(R.id.iv_equip_img));
    }
}
