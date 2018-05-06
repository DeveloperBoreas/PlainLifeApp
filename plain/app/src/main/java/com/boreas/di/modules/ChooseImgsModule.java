package com.boreas.di.modules;

import com.boreas.di.PerActivity;
import com.boreas.di.PerFragment;
import com.boreas.interactor.ChooseImgs;
import com.boreas.interactor.Music;
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
@PerActivity
public class ChooseImgsModule {
    private PresenterContract.ChooseImgsView chooseImgsView = null;
    public ChooseImgsModule(PresenterContract.ChooseImgsView chooseImgsView){
        this.chooseImgsView = chooseImgsView;
    }

    @Provides
    public PresenterContract.ChooseImgsView providesMusicView(){
        return this.chooseImgsView;
    }

    @Provides
    public ChooseImgs providesChooseImgs(PlainDataRepository repository){
        return new ChooseImgs(repository);
    }
}
