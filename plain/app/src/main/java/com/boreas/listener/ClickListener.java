package com.boreas.listener;

import android.view.View;

/**
 * 作者 boreas
 * 日期 18-3-15
 * 邮箱 13051089921@163.com
 * @author boreas
 */

public interface ClickListener<T> {
    void onItemClick(View itemView,int position,T t);
    void onItemLongClick(View itemView,int position,T t);
}
