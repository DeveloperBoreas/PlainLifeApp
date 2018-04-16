package com.boreas.net;

import android.accounts.NetworkErrorException;
import android.content.Context;
import android.database.Cursor;
import android.provider.MediaStore;

import com.boreas.App;
import com.boreas.api.Api;
import com.boreas.api.ApiService;
import com.boreas.model.entity.MusicEntity;
import com.boreas.model.entity.PicEntity;
import com.boreas.utils.GsonHelper;
import com.boreas.utils.NetWorkUtil;
import com.orhanobut.logger.Logger;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import okhttp3.OkHttpClient;
import rx.Observable;
import rx.Subscriber;

/**
 *
 * @author boreas
 * @date 18-2-24
 */

public class IPlainRestApi implements PlainRestApi {

    private Context context = null;
    private ApiService apiService = null;
    @Inject
    public IPlainRestApi(){
        this.context = App.getContext();
        this.apiService = App.app.getNetComponent().getApiService();
        HttpRequest.init(App.app.getNetComponent().getOkHttp());
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
    public Observable<MusicEntity> getMusicInfo(int type) {
//        return apiService.getMusicList(type);
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
                HttpRequest.doGet(Api.GET_PICS, new CallBack() {
                    @Override
                    public void onSuccess(String result) {
                        Logger.d("--------------getPicListInfo  result :" + result);
                        PicEntity entity = GsonHelper.getGson().fromJson(result,PicEntity.class);
                        subscriber.onNext(entity);
                        subscriber.onCompleted();
                    }

                    @Override
                    public void onFail(Exception e) {
                        subscriber.onError(e);
                    }
                });
            }else{
                subscriber.onError(new NetworkErrorException());
            }
        });
    }

    /**
     * 获取手机图片
     */
//    private void getPicList(Subscriber subscriber){
//        try {
//            Cursor cursor = context.getContentResolver().query(MediaStore.Images.Media.EXTERNAL_CONTENT_URI,null,null,null,null);
//            List<PicEntity.Pic> list = new ArrayList<>();
//            while (cursor.moveToNext()){
//                //获取图片的名称
//                String name = cursor.getString(cursor.getColumnIndex(MediaStore.Images.Media.DISPLAY_NAME));
//                //获取图片的生成日期
//                byte[] date = cursor.getBlob(cursor.getColumnIndex(MediaStore.Images.Media.DATA));
//                //获取图片的详细信息
//                String desc = cursor.getString(cursor.getColumnIndex(MediaStore.Images.Media.DESCRIPTION));
//                list.add(new PicEntity.Pic(name,date,desc));
//            }
//            PicEntity picEntity = new PicEntity();
//            picEntity.setPicList(list);
//            subscriber.onNext(picEntity);
//        } catch (Exception e) {
//            e.printStackTrace();
//            subscriber.onError(e);
//        }
//
//    }
}
