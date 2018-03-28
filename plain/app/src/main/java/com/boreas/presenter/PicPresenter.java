package com.boreas.presenter;

import com.boreas.interactor.Pic;
import com.boreas.model.entity.PicEntity;
import com.orhanobut.logger.Logger;

import java.util.List;

import javax.inject.Inject;

import rx.Subscriber;

/**
 * 作者 boreas
 * 日期 18-3-28
 * 邮箱 13051089921@163.com
 */

public class PicPresenter implements PresenterContract.Presenter {

    private Pic pic = null;
    private PresenterContract.PicView picView = null;
    @Inject
    public  PicPresenter(Pic pic, PresenterContract.PicView picView){
        this.pic = pic;
        this.picView = picView;
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
    public void getPicList(){
        pic.execute(new PicSubcriber());
    }
    private class PicSubcriber extends Subscriber<PicEntity>{

        @Override
        public void onCompleted() {
            Logger.d("PicSubcriber onCompleted");
        }

        @Override
        public void onError(Throwable e) {
            Logger.d("PicSubcriber onError");
        }

        @Override
        public void onNext(PicEntity picEntity) {
            Logger.d("PicSubcriber onNext");
            List<PicEntity.Pic> list = picEntity.getPicList();
            picView.getPicList(list);
        }
    }
}
