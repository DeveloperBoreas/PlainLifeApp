package com.boreas.di.modules;

import com.boreas.interactor.Music;
import com.boreas.interactor.Pic;
import com.boreas.presenter.PresenterContract;
import com.boreas.repository.PlainDataRepository;

import dagger.Module;
import dagger.Provides;

/**
 * 作者 boreas
 * 日期 18-3-7
 * 邮箱 13051089921@163.com
 * @author boreas
 */
@Module
public class PicFragmentModule {
    private PresenterContract.PicView picView = null;
    public PicFragmentModule(PresenterContract.PicView picView){
        this.picView = picView;
    }

    @Provides
    public PresenterContract.PicView providesMusicView(){
        return this.picView;
    }

    @Provides
    public Pic providesPicUseCase(PlainDataRepository repository){
        return new Pic(repository);
    }
}
