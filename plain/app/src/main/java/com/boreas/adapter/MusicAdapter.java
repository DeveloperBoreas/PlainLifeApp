package com.boreas.adapter;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.boreas.R;
import com.boreas.base.BaseAdapter;
import com.boreas.listener.ClickListener;
import com.boreas.model.entity.MusicEntity;
import com.boreas.model.entity.MusicEntityList;
import com.bumptech.glide.Glide;

import java.util.List;


/**
 * 作者 boreas
 * 日期 18-3-14
 * 邮箱 13051089921@163.com
 * @author boreas
 */

public class MusicAdapter<T> extends RecyclerView.Adapter<MusicAdapter.MusicViewHolder> {

    private ClickListener<T> clickListener = null;
    private List<T>  listData = null;
    private Context context;
    public MusicAdapter(Context context,MusicEntityList musicEntityList) {
        this.listData = (List<T>) musicEntityList.getSong_list();
        this.context = context;
    }

    @Override
    public MusicViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        MusicViewHolder holder = new MusicViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_music, parent
                , false));
        return holder;
    }

    @Override
    public void onBindViewHolder(MusicAdapter.MusicViewHolder holder, int position) {
        MusicEntityList.SongListBean bean = (MusicEntityList.SongListBean) listData.get(position);
        holder.cardView.setBackgroundColor(0);
        Glide.with(context)
                .load(bean.getPic_small())
                .asBitmap()
                .into(holder.imageIcon);
        holder.musicName.setText(bean.getAuthor()+"");
        holder.soundName.setText(bean.getAlbum_title());
        if(clickListener != null){
            holder.cardView.setOnClickListener(
                    v -> clickListener.onItemClick(holder.cardView,position,listData.get(position)));
            holder.cardView.setOnLongClickListener(v -> {
                clickListener.onItemLongClick(holder.cardView,position,listData.get(position));
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
        ImageView imageIcon = null;
        TextView soundName = null;
        TextView musicName = null;
        public MusicViewHolder(View itemView) {
            super(itemView);
            cardView = itemView.findViewById(R.id.card_view);
            imageIcon = itemView.findViewById(R.id.music_icon);
            soundName = itemView.findViewById(R.id.music_soundname);
            musicName = itemView.findViewById(R.id.music_name);
        }
    }


    public MusicAdapter setOnClickListener(ClickListener<T> clickListener){
        this.clickListener = clickListener;
        return this;
    }

}
