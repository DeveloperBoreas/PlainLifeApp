package com.boreas.api;

/**
 * 作者 boreas
 * 日期 18-2-24
 * 邮箱 13051089921@163.com
 */

import com.boreas.model.entity.MusicEntity;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * 通过retrofit + RxJava 进行网络封装
 */
public interface ApiService {
    /**
     * 获取音乐列表json串
     */
    @GET("/music/musicList")
    Observable<MusicEntity> getMusicList(@Query("type") int type);


}
