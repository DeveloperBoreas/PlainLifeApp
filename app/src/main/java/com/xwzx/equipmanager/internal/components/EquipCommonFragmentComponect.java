package com.xwzx.equipmanager.internal.components;

import com.xwzx.equipmanager.internal.modules.EquipCommonFragmentModule;
import com.xwzx.equipmanager.internal.scopers.FragmentScope;
import com.xwzx.equipmanager.mvp.views.fragments.EquipCommonFragment;

import dagger.Component;

@FragmentScope
@Component(modules = EquipCommonFragmentModule.class, dependencies = NetComponent.class)
public interface EquipCommonFragmentComponect {
    void inject(EquipCommonFragment equipCommonFragment);
}
