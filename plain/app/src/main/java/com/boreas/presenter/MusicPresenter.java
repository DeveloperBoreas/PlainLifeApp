package com.boreas.presenter;

import com.boreas.interactor.Music;
import com.boreas.model.entity.MusicEntity;

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

    public void getMusicList(){
        music.execute(new MusicSubcriber());
    }



    private class MusicSubcriber extends Subscriber<MusicEntity>{

        @Override
        public void onCompleted() {

        }

        @Override
        public void onError(Throwable e) {

        }

        @Override
        public void onNext(MusicEntity musicEntity) {
            List list = (List) musicEntity;
            musicView.getData(list);
        }
    }
}
