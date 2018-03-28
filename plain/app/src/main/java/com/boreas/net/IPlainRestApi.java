package com.boreas.net;

import android.accounts.NetworkErrorException;
import android.content.Context;
import android.database.Cursor;
import android.provider.MediaStore;

import com.boreas.App;
import com.boreas.model.entity.MusicEntity;
import com.boreas.model.entity.PicEntity;
import com.boreas.utils.NetWorkUtil;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import rx.Observable;
import rx.Subscriber;

/**
 *
 * @author boreas
 * @date 18-2-24
 */

public class IPlainRestApi implements PlainRestApi {

    private Context context = null;

    @Inject
    public IPlainRestApi(){
        this.context = App.getContext();
    }

    @Override
    public Observable<String> login(String account, String password) {
        return Observable.create((subscriber)->{
            if(NetWorkUtil.isNetWorkEnable(context)){

            }else{
                subscriber.onError(new NetworkErrorException());
            }
        });
    }

    /**
     * @return 音乐列表
     */
    @Override
    public Observable<MusicEntity> getMusicInfo() {
        return Observable.create((subscriber -> {
            if(NetWorkUtil.isNetWorkEnable(context)){

            }else{
                subscriber.onError(new NetworkErrorException());
            }
        }));
    }

    public Observable<PicEntity> getPicListInfo(){
        return Observable.create(subscriber -> {
            if(NetWorkUtil.isNetWorkEnable(context)){
                getPicList(subscriber);
            }else{
                subscriber.onError(new NetworkErrorException());
            }
        });
    }

    /**
     * 获取手机图片
     */
    private void getPicList(Subscriber subscriber){
        try {
            Cursor cursor = context.getContentResolver().query(MediaStore.Images.Media.EXTERNAL_CONTENT_URI,null,null,null,null);
            List<PicEntity.Pic> list = new ArrayList<>();
            while (cursor.moveToNext()){
                //获取图片的名称
                String name = cursor.getString(cursor.getColumnIndex(MediaStore.Images.Media.DISPLAY_NAME));
                //获取图片的生成日期
                byte[] date = cursor.getBlob(cursor.getColumnIndex(MediaStore.Images.Media.DATA));
                //获取图片的详细信息
                String desc = cursor.getString(cursor.getColumnIndex(MediaStore.Images.Media.DESCRIPTION));
                list.add(new PicEntity.Pic(name,date,desc));
            }
            PicEntity picEntity = new PicEntity();
            picEntity.setPicList(list);
            subscriber.onNext(picEntity);
        } catch (Exception e) {
            e.printStackTrace();
            subscriber.onError(e);
        }

    }
}
