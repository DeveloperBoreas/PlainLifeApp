package com.boreas.presenter;

import android.support.v4.app.Fragment;
import android.util.Log;

import com.boreas.api.ApiService;
import com.boreas.interactor.Music;
import com.boreas.model.entity.MusicEntity;
import com.boreas.model.entity.MusicEntityList;
import com.boreas.net.NetUtil;
import com.boreas.utils.GsonHelper;
import com.orhanobut.logger.Logger;

import java.util.List;

import javax.inject.Inject;

import rx.Subscriber;

/**
 *
 * @author admin
 * @date 2018/2/23
 */

public class MusicPresenter implements PresenterContract.Presenter {

    private Music music = null;
    private PresenterContract.MusicView musicView = null;
    @Inject
    public MusicPresenter(Music music, PresenterContract.MusicView musicView){
        this.music = music;
        this.musicView = musicView;
    }


    @Override
    public void resume() {
    }

    @Override
    public void pause() {

    }

    @Override
    public void destroy() {

    }

    public void getMusicList(Fragment fragment, int type){
        music.setType(type);
        music.setActivity(fragment);
        music.execute(new MusicSubcriber());
    }



    private class MusicSubcriber extends Subscriber<MusicEntityList>{

        @Override
        public void onCompleted() {

        }

        @Override
        public void onError(Throwable e) {

        }

        @Override
        public void onNext(MusicEntityList musicEntityList) {
//            Logger.d("音乐数据 musicEntity : " + GsonHelper.getGson().toJson(musicEntityList));
            musicView.getData(musicEntityList);
        }
    }
}
