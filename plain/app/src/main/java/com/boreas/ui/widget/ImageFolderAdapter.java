package com.boreas.ui.widget;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.boreas.R;
import com.boreas.model.entity.ImageFolderEntity;
import com.bumptech.glide.Glide;

import java.util.ArrayList;

/**
 * Created by yinwei on 2015-11-16.
 */
public class ImageFolderAdapter extends BaseAdapter {

    private ArrayList<ImageFolderEntity> marrayList;

    private Context context;

    public ImageFolderAdapter(Context context) {
        super();
        this.context=context;
    }


    public void setArrayList(ArrayList<ImageFolderEntity> arrayList) {

        this.marrayList = arrayList;
        notifyDataSetChanged();
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public Object getItem(int position) {
        return marrayList.get(position);
    }

    @Override
    public int getCount() {

        return marrayList==null?0:marrayList.size();
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder holder;

        if(convertView==null){
            holder=new ViewHolder();
            convertView= LayoutInflater.from(context).inflate(R.layout.adapter_image_folder_item,null);
            holder.firstImage=(ImageView)convertView.findViewById(R.id.firstImage);
            holder.folderName=(TextView)convertView.findViewById(R.id.folderName);
            holder.imagesSize=(TextView)convertView.findViewById(R.id.imagesSize);
            holder.isSelected=(ImageView)convertView.findViewById(R.id.isSelected);

            convertView.setTag(holder);

        }else{

            holder=(ViewHolder)convertView.getTag();

        }
        Glide.with(context).load("file://" + marrayList.get(position).firstImage).asBitmap().into(holder.firstImage);
        holder.folderName.setText(marrayList.get(position).folerName);
        holder.imagesSize.setText(String.valueOf(marrayList.get(position).imgs));
            if(marrayList.get(position).isSelected){
            holder.isSelected.setVisibility(View.VISIBLE);
        }else{
            holder.isSelected.setVisibility(View.GONE);
        }
        return convertView;
    }

    private class ViewHolder{

        private ImageView firstImage;

        private TextView folderName,imagesSize;

        private ImageView isSelected;


    }

}
