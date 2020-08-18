package com.xwzx.equipmanager.internal.modules;

import com.xwzx.equipmanager.internal.scopers.ActivityScope;
import com.xwzx.equipmanager.mvp.views.viewinterfaces.MainActivityInterface;

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
