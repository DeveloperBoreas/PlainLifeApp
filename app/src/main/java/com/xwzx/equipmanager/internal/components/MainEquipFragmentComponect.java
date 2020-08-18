package com.xwzx.equipmanager.internal.components;

import com.xwzx.equipmanager.internal.modules.MainEquipFragmentModule;
import com.xwzx.equipmanager.internal.scopers.FragmentScope;
import com.xwzx.equipmanager.mvp.views.fragments.MainEquipFragment;

import dagger.Component;

@FragmentScope
@Component(modules = MainEquipFragmentModule.class, dependencies = NetComponent.class)
public interface MainEquipFragmentComponect {
    void inject(MainEquipFragment mainEquipFragment);
}
