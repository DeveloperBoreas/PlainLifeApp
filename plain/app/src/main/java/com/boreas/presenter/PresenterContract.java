package com.boreas.presenter;

import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;

import com.boreas.interactor.Music;
import com.boreas.model.entity.MusicEntity;
import com.boreas.model.entity.PicEntity;

import java.util.List;

/**
 * Created by admin on 2018/2/23.
 */

public interface PresenterContract {

    interface Presenter{
        void resume();

        void pause();

        void destroy();
    }

    interface BaseView{
        void showLoading();
        void dismissLoading();
    }
    interface LoginView{
        void setContentBg(int resId);
    }
    interface MainView{

    }
    interface MusicView<T>{
        void getData(T t);
    }
    interface PicView{
        void getPicList(List<PicEntity.Pic> list);
    }
}
