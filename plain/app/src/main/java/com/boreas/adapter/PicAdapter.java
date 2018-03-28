package com.boreas.adapter;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.boreas.R;
import com.boreas.listener.ClickListener;
import com.boreas.model.entity.MusicEntity;
import com.boreas.model.entity.PicEntity;

import java.util.List;


/**
 * 作者 boreas
 * 日期 18-3-14
 * 邮箱 13051089921@163.com
 * @author boreas
 */

public class PicAdapter extends RecyclerView.Adapter<PicAdapter.MusicViewHolder> {

    private ClickListener<PicEntity.Pic> clickListener = null;
    private List<PicEntity.Pic>  listData = null;

    public PicAdapter(List<PicEntity.Pic> list) {
        this.listData = list;
    }

    @Override
    public MusicViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        MusicViewHolder holder = new MusicViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_pic, parent
                , false));
        return holder;
    }

    @Override
    public void onBindViewHolder(MusicViewHolder holder, int position) {
        PicEntity.Pic t = listData.get(position);
        if(clickListener != null){
            holder.cardView.setOnClickListener(
                    v -> clickListener.onItemClick(holder.cardView,position,t));
            holder.cardView.setOnLongClickListener(v -> {
                clickListener.onItemLongClick(holder.cardView,position,t);
                return false;
            });
        }
    }

    @Override
    public int getItemCount() {
        if(listData != null && listData.size()>0){
            return listData.size();
        }
        return 0;
    }

    public class MusicViewHolder extends RecyclerView.ViewHolder {
        CardView cardView = null;
        ImageView imagePic = null;
        public MusicViewHolder(View itemView) {
            super(itemView);
            cardView = itemView.findViewById(R.id.card_view);
            imagePic = itemView.findViewById(R.id.item_pic);
        }
    }


    public PicAdapter setOnClickListener(ClickListener<PicEntity.Pic> clickListener){
        this.clickListener = clickListener;
        return this;
    }

}