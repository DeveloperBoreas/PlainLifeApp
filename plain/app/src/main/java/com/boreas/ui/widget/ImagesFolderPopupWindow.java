package com.boreas.ui.widget;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.PopupWindow;


import com.boreas.R;
import com.boreas.model.entity.ImageFolderEntity;
import com.boreas.utils.ScreenUtil;

import java.util.ArrayList;


/**
 * @author admin
 */
public class ImagesFolderPopupWindow extends PopupWindow implements AdapterView.OnItemClickListener{

    private ListView listView;

    private ArrayList<ImageFolderEntity> marrayList;

    private ImageFolderAdapter imageFolderAdapter;

    private FinishOnItemClickListener finishOnItemClickListener;

    private MyHandler handler;

    public ImagesFolderPopupWindow(Context context) {
        super(context);

        View v= LayoutInflater.from(context).inflate(R.layout.popu_images_folder, null);
        setContentView(v);

        //设置SelectPicPopupWindow弹出窗体的宽
        this.setWidth(ViewGroup.LayoutParams.MATCH_PARENT);
        //设置SelectPicPopupWindow弹出窗体的高
        this.setHeight(ScreenUtil.getScreenHeight(context) -
                ScreenUtil.dp2px(context, 119) - ScreenUtil.getStatusHeight(context));

        //设置SelectPicPopupWindow弹出窗体可点击
        this.setTouchable(true);
        this.setFocusable(true);
        this.setOutsideTouchable(true);

        // 刷新状态
        this.update();
        //设置SelectPicPopupWindow弹出窗体动画效果
        this.setAnimationStyle(R.style.popuwindow_from_bottom);
        //实例化一个ColorDrawable颜色为半透明
        ColorDrawable dw = new ColorDrawable(0x50000000);
        //设置SelectPicPopupWindow弹出窗体的背景
        this.setBackgroundDrawable(dw);

        listView=(ListView)v.findViewById(R.id.imagesFolders);

        imageFolderAdapter=new ImageFolderAdapter(context);

        listView.setAdapter(imageFolderAdapter);

        listView.setOnItemClickListener(this);

        handler=new MyHandler();


    }


    /**
     * 设置popouwindow的适配器的数据源
     * @param arrayList
     */
    public void setArrayList(ArrayList<ImageFolderEntity> arrayList) {

        this.marrayList=arrayList;
        imageFolderAdapter.setArrayList(arrayList);

    }

    /***
     * 设置回调接口
     * @param finishOnItemClickListener
     */
    public void setFinishOnItemClickListener(FinishOnItemClickListener finishOnItemClickListener) {
        this.finishOnItemClickListener = finishOnItemClickListener;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {


        new Thread(new Runnable() {
            @Override
            public void run() {

                for (int i=0;i<marrayList.size();i++){

                    if(i==position){

                        marrayList.get(i).isSelected=true;
                    }else{

                        marrayList.get(i).isSelected=false;
                    }

                }
                Message message=new Message();
                message.obj=marrayList.get(position).folerName;

                handler.sendMessage(message);



            }
        }).start();


    }


    private class MyHandler extends Handler {

        @Override
        public void handleMessage(Message msg) {

            imageFolderAdapter.setArrayList(marrayList);

            finishOnItemClickListener.OnFinishedClick(msg.obj.toString());//调用接口内的方法

            ImagesFolderPopupWindow.this.dismiss();

            super.handleMessage(msg);
        }
    }

    /**
     * 显示popupwindow
     *
     * @param parent
     */
    public void showPopupWindow(View parent) {

        if (!this.isShowing()) {

            //也可以使用这种方法
//            int[] location=new int[2];
//
//            parent.getLocationOnScreen(location);

//            this.showAtLocation(parent, Gravity.NO_GRAVITY, location[0], location[1]-this.getHeight());

            this.showAsDropDown(parent,0,0);


        } else {

            this.dismiss();


        }
    }




    //建立一个回调接口
    public  interface FinishOnItemClickListener{


      void  OnFinishedClick(String name);

    }


}
