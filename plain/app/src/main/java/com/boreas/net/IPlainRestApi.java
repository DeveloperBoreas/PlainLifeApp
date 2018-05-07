package com.boreas.net;

import android.accounts.NetworkErrorException;
import android.app.Activity;
import android.content.Context;
import android.database.ContentObservable;
import android.database.Cursor;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.util.Log;

import com.boreas.App;
import com.boreas.api.Api;
import com.boreas.api.ApiService;
import com.boreas.model.entity.MusicEntity;
import com.boreas.model.entity.MusicEntityList;
import com.boreas.model.entity.PicEntity;
import com.boreas.utils.GsonHelper;
import com.boreas.utils.JsonUtil;
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
     * type = 1-新歌榜,2-热歌榜,11-摇滚榜,12-爵士,16-流行,21-欧美金曲榜,22-经典老歌榜,23-情歌对唱榜,24-影视金曲榜,25-网络歌曲榜
    // size = 10 //返回条目数量
    // offset = 0 //获取偏移
     */
    @Override
    public Observable<MusicEntityList> getMusicInfo(Activity activity,int type) {
        return Observable.create((subscriber -> {
            if(NetWorkUtil.isNetWorkEnable(context)){
                String MUSIC_LIST = "http://tingapi.ting.baidu.com/v1/restserver/ting?format=json&callback=&from=webapp_music&method=baidu.ting.billboard.billList&type=@&size=100&offset=0";
                String url = MUSIC_LIST.replace("@",String.valueOf(type));
                Log.d(" url :",url);
                NetUtil.requestForGet(activity,url,null, new NetUtil.NetCallBack() {
                    @Override
                    public void onSuccess(String result) {
                        if(!TextUtils.isEmpty(result)){
                            try {
                                MusicEntityList musicEntityList = GsonHelper.getGson().fromJson(result,MusicEntityList.class);
                                if(musicEntityList != null && musicEntityList.getSong_list().size()!= 0){
                                    subscriber.onNext(musicEntityList);
                                    subscriber.onCompleted();
                                }else{
                                    subscriber.onError(new NullPointerException("音乐类型列表解析出错。"));
                                }
                            } catch (Exception e) {
                                e.printStackTrace();
                                subscriber.onError(new NullPointerException("音乐类型列表解析出错。"));
                            }
                        }else{
                            subscriber.onError(new NullPointerException("音乐类型列表数据为空。"));
                        }
                    }
                    @Override
                    public void onError(Throwable e) {
                        subscriber.onError(e);
                    }
                });
            }else{
                subscriber.onError(new NetworkErrorException());
            }
        }));
    }

    @Override
    public Observable<PicEntity> getLocalPics() {
        return Observable.create(subscriber -> {
            if(NetWorkUtil.isNetWorkEnable(context)){
                Cursor imageCursor = context.getContentResolver().query(
                        MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                        new String[]{MediaStore.Images.Media.DATA,MediaStore.Images.Media._ID},
                        null,null,MediaStore.Images.Media._ID);
            }else{
                subscriber.onError(new NetworkErrorException());
            }
        });
    }

    public Observable<PicEntity> getPicListInfo(){
        return Observable.create(subscriber -> {
            getPicList(subscriber);
//            if(NetWorkUtil.isNetWorkEnable(context)){
//                HttpRequest.doGet(Api.GET_PICS, new CallBack() {
//                    @Override
//                    public void onSuccess(String result) {
//                        Logger.d("--------------getPicListInfo  result :" + result);
//                        PicEntity entity = GsonHelper.getGson().fromJson(result,PicEntity.class);
//                        subscriber.onNext(entity);
//                        subscriber.onCompleted();
//                    }
//
//                    @Override
//                    public void onFail(Exception e) {
//                        subscriber.onError(e);
//                    }
//                });
//            }else{
//                subscriber.onError(new NetworkErrorException());
//            }
        });
    }


    private void getPicList(Subscriber subscriber){
        try {
            Cursor cursor = context.getContentResolver().query(MediaStore.Images.Media.EXTERNAL_CONTENT_URI,null,null,null,null);
            List<PicEntity.Pic> list = new ArrayList<>();
            while (cursor.moveToNext()){
                //获取图片的名称
                String name = cursor.getString(cursor.getColumnIndex(MediaStore.Images.Media.DISPLAY_NAME));
                //获取图片的生成日期
                byte[] data = cursor.getBlob(cursor.getColumnIndex(MediaStore.Images.Media.DATA));
                //获取图片的详细信息
                String desc = cursor.getString(cursor.getColumnIndex(MediaStore.Images.Media.DESCRIPTION));
                list.add(new PicEntity.Pic(name,new String(data,0,data.length-1)));
            }
            PicEntity picEntity = new PicEntity();
//            picEntity.setPicList(list);
            picEntity.setData(list);
            subscriber.onNext(picEntity);
        } catch (Exception e) {
            e.printStackTrace();
            subscriber.onError(e);
        }

    }
}
