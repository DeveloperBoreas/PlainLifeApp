package com.xwzx.equipmanager.internal.modules;

import android.content.Context;

import com.xwzx.equipmanager.App;

import dagger.Module;
import dagger.Provides;

@Module
public class AppModule {

    private App app;

    public AppModule(App app) {
        this.app = app;
    }

    @Provides
    public App provideApp() {
        return this.app;
    }

    @Provides
    public Context provideContext() {
        return this.app.getApplicationContext();
    }
}
