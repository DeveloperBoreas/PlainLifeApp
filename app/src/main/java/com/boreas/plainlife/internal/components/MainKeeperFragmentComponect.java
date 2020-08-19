package com.boreas.plainlife.internal.components;

import com.boreas.plainlife.internal.modules.MainKeeperFragmentModule;
import com.boreas.plainlife.internal.scopers.FragmentScope;
import com.boreas.plainlife.mvp.views.fragments.MainIndexKeeperFragment;

import dagger.Component;

@FragmentScope
@Component(modules = MainKeeperFragmentModule.class, dependencies = NetComponent.class)
public interface MainKeeperFragmentComponect {
    void inject(MainIndexKeeperFragment mainEquipFragment);
}
