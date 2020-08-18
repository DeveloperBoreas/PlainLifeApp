package com.xwzx.equipmanager.internal.modules;

import com.xwzx.equipmanager.internal.scopers.FragmentScope;
import com.xwzx.equipmanager.mvp.views.viewinterfaces.KeeperFragmentInterface;
import com.xwzx.equipmanager.mvp.views.viewinterfaces.MainPerCenterKeeperFragmentInterface;

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
