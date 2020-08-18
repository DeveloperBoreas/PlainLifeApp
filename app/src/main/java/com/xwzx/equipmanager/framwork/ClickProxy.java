package com.xwzx.equipmanager.framwork;

import android.view.View;

import com.orhanobut.logger.Logger;

public class ClickProxy implements View.OnClickListener {
    private View.OnClickListener origin;
    private long lastclick = 0;
    private long timems = 1000;


    public ClickProxy(View.OnClickListener origin) {
        this.origin = origin;
    }
    public ClickProxy(View.OnClickListener origin,long time){
        this.origin = origin;
        this.timems = time;
    }

    @Override
    public void onClick(View v) {
        if (System.currentTimeMillis() - lastclick >= timems) {
            origin.onClick(v);
            lastclick = System.currentTimeMillis();
        }
//        else {
//            Logger.e("重复点击");
//        }
    }
}
