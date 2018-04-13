package com.boreas.net;

import com.boreas.model.entity.MusicEntity;

import rx.Observable;

/**
 *
 * @author boreas
 * @date 18-2-24
 */

public interface PlainRestApi {

    /**
     * 登录
     * @param account
     * @param password
     * @return
     */
    Observable<String> login(String account,String password);

    /**
     * 音乐列表信息
     * @return
     */
    Observable<MusicEntity> getMusicInfo(int type);
}
