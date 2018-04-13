package com.boreas.repository;

import com.boreas.model.entity.MusicEntity;
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
    Observable<MusicEntity> getMusicInfo(int type);

    /**
     * 获取图片
     */
    Observable<PicEntity> getPicListInfo();
}
