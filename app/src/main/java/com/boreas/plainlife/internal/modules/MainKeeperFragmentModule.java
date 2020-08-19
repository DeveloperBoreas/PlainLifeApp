package com.boreas.plainlife.internal.modules;

import com.boreas.plainlife.internal.scopers.FragmentScope;
import com.boreas.plainlife.mvp.views.viewinterfaces.KeeperFragmentInterface;

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
