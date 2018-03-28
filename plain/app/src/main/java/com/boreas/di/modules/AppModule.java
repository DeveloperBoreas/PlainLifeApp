package com.boreas.di.modules;

import android.content.Context;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * 作者 boreas
 * 日期 18-2-24
 * 邮箱 13051089921@163.com
 * @author boreas
 */
@Module
@Singleton
public class AppModule {

    private Context context;

    public AppModule(Context context) {
        this.context = context;
    }

    @Provides
    public Context provideApp(){
        return context;
    }
}
