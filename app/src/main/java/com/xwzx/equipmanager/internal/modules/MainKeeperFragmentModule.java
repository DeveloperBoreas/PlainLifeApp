package com.xwzx.equipmanager.internal.modules;

import com.xwzx.equipmanager.internal.scopers.FragmentScope;
import com.xwzx.equipmanager.mvp.views.viewinterfaces.KeeperFragmentInterface;

import dagger.Module;
import dagger.Provides;

@FragmentScope
@Module
public class MainKeeperFragmentModule {
    private KeeperFragmentInterface keeperFragmentInterface;

    public MainKeeperFragmentModule(KeeperFragmentInterface keeperFragmentInterface) {
        this.keeperFragmentInterface = keeperFragmentInterface;
    }
    @Provides
    public KeeperFragmentInterface provideKeeperFragmentInterface(){
        return this.keeperFragmentInterface;
    }
}
