package com.boreas.repository;

import android.app.Activity;

import com.boreas.model.entity.MusicEntity;
import com.boreas.model.entity.MusicEntityList;
import com.boreas.model.entity.PicEntity;

import rx.Observable;

/**
 * Created by admin on 2018/2/23.
 */

public interface PlainRepository {

    /**
     * 登录
     * @param account
     * @param password
     * @return
     */
    Observable<String> login(String account, String password);


    /**
     * 获取音乐列表
     */
    Observable<MusicEntityList> getMusicInfo(Activity activity,int type);

    /**
     * 获取图片
     */
    Observable<PicEntity> getPicListInfo();

    /**
     * 获取Local图片
     */
    Observable<PicEntity.Pic> getLocalPics();
}
