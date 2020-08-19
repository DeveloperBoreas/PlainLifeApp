package com.boreas.plainlife.internal.modules;

import com.boreas.plainlife.internal.scopers.ActivityScope;
import com.boreas.plainlife.mvp.views.viewinterfaces.MainActivityInterface;

import dagger.Module;
import dagger.Provides;

@ActivityScope
@Module
public class MainActivityModule {
    private MainActivityInterface mainActivityInterface;

    public MainActivityModule(MainActivityInterface mainActivityInterface) {
        this.mainActivityInterface = mainActivityInterface;
    }

    @Provides
    public MainActivityInterface provideMainActivityInterface(){
        return this.mainActivityInterface;
    }
}
