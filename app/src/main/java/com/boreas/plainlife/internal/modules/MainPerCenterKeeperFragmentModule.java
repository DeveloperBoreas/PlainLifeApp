package com.boreas.plainlife.internal.modules;

import com.boreas.plainlife.internal.scopers.FragmentScope;
import com.boreas.plainlife.mvp.views.viewinterfaces.MainPerCenterKeeperFragmentInterface;

import dagger.Module;
import dagger.Provides;

@FragmentScope
@Module
public class MainPerCenterKeeperFragmentModule {
    private MainPerCenterKeeperFragmentInterface mainPerCenterKeeperFragmentInterface;

    public MainPerCenterKeeperFragmentModule(MainPerCenterKeeperFragmentInterface mainPerCenterKeeperFragmentInterface) {
        this.mainPerCenterKeeperFragmentInterface = mainPerCenterKeeperFragmentInterface;
    }
    @Provides
    public MainPerCenterKeeperFragmentInterface provideMainPerCenterKeeperFragmentInterface(){
        return this.mainPerCenterKeeperFragmentInterface;
    }
}
