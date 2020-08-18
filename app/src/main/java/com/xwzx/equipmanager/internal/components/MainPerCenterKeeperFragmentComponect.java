package com.xwzx.equipmanager.internal.components;

import com.xwzx.equipmanager.internal.modules.MainKeeperFragmentModule;
import com.xwzx.equipmanager.internal.modules.MainPerCenterKeeperFragmentModule;
import com.xwzx.equipmanager.internal.scopers.FragmentScope;
import com.xwzx.equipmanager.mvp.views.fragments.MainIndexKeeperFragment;
import com.xwzx.equipmanager.mvp.views.fragments.MainPerCenterKeeperFragment;

import dagger.Component;

@FragmentScope
@Component(modules = MainPerCenterKeeperFragmentModule.class, dependencies = NetComponent.class)
public interface MainPerCenterKeeperFragmentComponect {
    void inject(MainPerCenterKeeperFragment mainPerCenterKeeperFragment);
}
