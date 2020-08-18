package com.xwzx.equipmanager.internal.components;

import com.xwzx.equipmanager.internal.modules.MainKeeperFragmentModule;
import com.xwzx.equipmanager.internal.scopers.FragmentScope;
import com.xwzx.equipmanager.mvp.views.fragments.MainIndexKeeperFragment;

import dagger.Component;

@FragmentScope
@Component(modules = MainKeeperFragmentModule.class, dependencies = NetComponent.class)
public interface MainKeeperFragmentComponect {
    void inject(MainIndexKeeperFragment mainEquipFragment);
}
