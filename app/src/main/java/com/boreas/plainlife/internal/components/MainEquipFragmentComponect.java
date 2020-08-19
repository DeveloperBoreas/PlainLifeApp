package com.boreas.plainlife.internal.components;

import com.boreas.plainlife.internal.modules.MainEquipFragmentModule;
import com.boreas.plainlife.internal.scopers.FragmentScope;
import com.boreas.plainlife.mvp.views.fragments.MainEquipFragment;

import dagger.Component;

@FragmentScope
@Component(modules = MainEquipFragmentModule.class, dependencies = NetComponent.class)
public interface MainEquipFragmentComponect {
    void inject(MainEquipFragment mainEquipFragment);
}
