package com.boreas.presenter;

import com.boreas.interactor.ChooseImgs;
import com.boreas.model.entity.PicEntity;

import java.util.ArrayList;
import java.util.HashMap;

import javax.inject.Inject;

import rx.Subscriber;

/**
 * Created by admin on 2018/5/8.
 */

public class ChooseImgsPresenter implements PresenterContract.Presenter {
    private PresenterContract.ChooseImgsView chooseImgsView = null;
    private ChooseImgs chooseImgs = null;

    @Inject
    public ChooseImgsPresenter (ChooseImgs chooseImgs,PresenterContract.ChooseImgsView chooseImgsView){
        this.chooseImgsView = chooseImgsView;
        this.chooseImgs = chooseImgs;
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
    public void getLocalImages(){
        chooseImgs.execute(new ChooseImgsSubscriber());
    }
    private class ChooseImgsSubscriber extends Subscriber<HashMap<String,ArrayList<PicEntity.Pic>>>{

        @Override
        public void onCompleted() {

        }

        @Override
        public void onError(Throwable e) {

        }

        @Override
        public void onNext(HashMap<String, ArrayList<PicEntity.Pic>> stringArrayListHashMap) {
            chooseImgsView.getLocalImgs(stringArrayListHashMap);
        }
    }
}
