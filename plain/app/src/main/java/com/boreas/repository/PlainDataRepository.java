package com.boreas.repository;

import android.app.Activity;

import com.boreas.model.entity.MusicEntity;
import com.boreas.model.entity.MusicEntityList;
import com.boreas.model.entity.PicEntity;
import com.boreas.net.IPlainRestApi;

import javax.inject.Inject;

import rx.Observable;

/**
 *
 * @author admin
 * @date 2018/2/23
 */

public class PlainDataRepository implements PlainRepository {

    private IPlainRestApi iPlainRestApi = null;

    @Inject
    public PlainDataRepository(IPlainRestApi iPlainRestApi){
        this.iPlainRestApi = iPlainRestApi;
    }

    /**登录**/
    @Override
    public Observable<String> login(String account, String password) {
        return iPlainRestApi.login(account,password);
    }
    /**获取音乐**/
    @Override
    public Observable<MusicEntityList> getMusicInfo(Activity activity,int type) {
        return iPlainRestApi.getMusicInfo(activity,type);
    }

    /**
     * 获取图片
     */
    @Override
    public Observable<PicEntity> getPicListInfo() {
        return iPlainRestApi.getPicListInfo();
    }


    /**
     * 获取本地图片
     * @return
     */
    @Override
    public Observable<PicEntity> getLocalPics() {
        return iPlainRestApi.getLocalPics();
    }
}
