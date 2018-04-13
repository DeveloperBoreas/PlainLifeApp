package com.boreas.di.modules;

import com.boreas.di.PerFragment;
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
@PerFragment
public class MusicFragmentModule {
    private PresenterContract.MusicView musicView = null;
    public MusicFragmentModule(PresenterContract.MusicView musicView){
        this.musicView = musicView;
    }

    @Provides
    public PresenterContract.MusicView providesMusicView(){
        return this.musicView;
    }

    @Provides
    public Music providesMusic(PlainDataRepository repository){
        return new Music(repository);
    }
}
