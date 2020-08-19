package com.boreas.plainlife.adapter;


import android.content.Context;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.boreas.plainlife.R;
import com.boreas.plainlife.base.adapter.BaseRecyclerAdapter;
import com.boreas.plainlife.base.adapter.BaseRecyclerHolder;
import com.boreas.plainlife.framwork.ClickProxy;
import com.boreas.plainlife.mvp.models.equipLib.EquipLibraryReceModel;
import com.boreas.plainlife.utils.ImageUtil;

import java.util.List;

public class CommonEquioDevicesAdapter extends BaseRecyclerAdapter<EquipLibraryReceModel.EquipDataListModel> {
    private CommonEquioDevicesAdapter.OnAddClickListener listener;

    public CommonEquioDevicesAdapter(Context context, List<EquipLibraryReceModel.EquipDataListModel> list, int itemLayoutId) {
        super(context, list, itemLayoutId);
    }

    @Override
    public void convert(BaseRecyclerHolder holder, EquipLibraryReceModel.EquipDataListModel item, int position, boolean isScrolling) {
        ImageView icon = holder.getView(R.id.item_img);
        TextView name = holder.getView(R.id.item_name);
        Button btnAdd = holder.getView(R.id.btn_add);
        TextView goodsLocation = holder.getView(R.id.item_goodsLocation);
        name.setText(item.getName());
        goodsLocation.setText(item.getPosition());

        btnAdd.setOnClickListener(new ClickProxy(view -> {
            if (listener != null) {
                listener.onAddClick(item);
            }
        }));
        if (item.getImageUrls().size() > 0) {
            ImageUtil.loadImg(getContext(), item.getImageUrls().get(0), icon);
        }
    }

    @Override
    public int getItemCount() {
        return super.getGridItemCount();
    }

    @Override
    public int getItemViewType(int position) {
        return super.getGridItemViewType(position);
    }

    public interface OnAddClickListener {
        void onAddClick(EquipLibraryReceModel.EquipDataListModel equipDataListModel);
    }

    public void setOnAddClickListener(OnAddClickListener listener) {
        this.listener = listener;
    }


}
