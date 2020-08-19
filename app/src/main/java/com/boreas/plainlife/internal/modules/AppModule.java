package com.boreas.plainlife.internal.modules;

import android.content.Context;

import com.boreas.plainlife.App;

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
