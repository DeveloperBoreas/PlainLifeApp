package com.boreas.plainlife.internal.components;

import com.boreas.plainlife.internal.modules.EquipCommonFragmentModule;
import com.boreas.plainlife.internal.scopers.FragmentScope;
import com.boreas.plainlife.mvp.views.fragments.EquipCommonFragment;

import dagger.Component;

@FragmentScope
@Component(modules = EquipCommonFragmentModule.class, dependencies = NetComponent.class)
public interface EquipCommonFragmentComponect {
    void inject(EquipCommonFragment equipCommonFragment);
}
