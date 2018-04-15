package com.boreas.presenter;

import com.boreas.interactor.Music;
import javax.inject.Inject;

import rx.Subscriber;

/**
 *
 * @author admin
 * @date 2018/2/23
 */

public class MainPresenter implements PresenterContract.Presenter {

    private Music music = null;

    @Inject
    public MainPresenter(Music music){
        this.music = music;
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

}
