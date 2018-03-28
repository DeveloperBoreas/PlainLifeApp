package com.boreas.di.modules;

import android.content.Context;

import com.boreas.interactor.Music;
import com.boreas.repository.PlainDataRepository;

import dagger.Module;
import dagger.Provides;

/**
 * 作者 boreas
 * 日期 18-3-12
 * 邮箱 13051089921@163.com
 */
@Module
public class MainModule {

    private Context context = null;

    public MainModule(Context context){
        this.context = context;
    }
    @Provides
    public Context providesContext(){
        return this.context;
    }

//    @Provides
//    public Music providesMusicUseCase(PlainDataRepository repository){
//        return new Music(repository);
//    }


}
