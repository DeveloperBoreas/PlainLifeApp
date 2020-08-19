package com.boreas.plainlife.internal.components;

import com.boreas.plainlife.internal.modules.MainPerCenterKeeperFragmentModule;
import com.boreas.plainlife.internal.scopers.FragmentScope;
import com.boreas.plainlife.mvp.views.fragments.MainPerCenterKeeperFragment;

import dagger.Component;

@FragmentScope
@Component(modules = MainPerCenterKeeperFragmentModule.class, dependencies = NetComponent.class)
public interface MainPerCenterKeeperFragmentComponect {
    void inject(MainPerCenterKeeperFragment mainPerCenterKeeperFragment);
}
