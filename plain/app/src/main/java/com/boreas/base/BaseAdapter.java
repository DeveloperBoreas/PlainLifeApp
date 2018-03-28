package com.boreas.base;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * 作者 boreas
 * 日期 18-3-14
 * 邮箱 13051089921@163.com
 * @author boreas
 */

public abstract class BaseAdapter<T> extends RecyclerView.Adapter {
//    private static final String TAG = BaseAdapter.class.getSimpleName();
//    private Object objectData = null;
//    private List<T> list = null;
////    public BaseAdapter(){
//////        objectData = getData();
////    }
//
//    @Override
//    public BaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
//        BaseViewHolder viewHolder = new BaseViewHolder(initItemView());
//        return viewHolder;
//    }
//
//    @Override
//    public void onBindViewHolder(BaseViewHolder holder, int position) {
//        if(isEnabledClick()) {
//            holder.itemView.setOnClickListener((view) -> onItemClick(view, position, list.get(position)));
//            holder.itemView.setOnLongClickListener(v -> onItemLongClick(v, position, list.get(position)));
//        }
//        onBindViewData(holder,list.get(position));
//    }
//
//    @Override
//    public int getItemCount() {
//        return getDataCount();
//    }
//
//    public int getDataCount() {
//        if(objectData != null){
//            if(objectData instanceof List){
//                list = (ArrayList) objectData;
//            }
//            if(list != null){
//                return list.size();
//            }
//            return 0;
//        }else{
//            throw new NullPointerException(TAG +"\t getDataCount  data is not null !!!!");
//        }
//    }
//
//    public class BaseViewHolder extends RecyclerView.ViewHolder{
//        public View itemView = null;
//        public BaseViewHolder(View itemView) {
//            super(itemView);
//            this.itemView = itemView;
//        }
//    }
//
//    /** 设置item布局 **/
//    public abstract View initItemView();
//    /** 设置items数据长度 **/
//    public abstract Object getData();
//    /** 设置item 点击事件 **/
//    public abstract void onItemClick(View itemView,int position,T t);
//    /** 设置item 长按点击事件 **/
//    public abstract boolean onItemLongClick(View itemView,int position,T t);
//
//    public abstract boolean isEnabledClick();
   // public abstract void onBindViewData(BaseAdapter.BaseViewHolder viewHolder,T t);
}
