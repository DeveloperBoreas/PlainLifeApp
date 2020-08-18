package com.xwzx.equipmanager.internal.components;

import com.xwzx.equipmanager.internal.modules.MainPerCenterKeeperFragmentModule;
import com.xwzx.equipmanager.internal.modules.MainPerCenterOperatorFragmentModule;
import com.xwzx.equipmanager.internal.scopers.FragmentScope;
import com.xwzx.equipmanager.mvp.views.fragments.MainPerCenterKeeperFragment;
import com.xwzx.equipmanager.mvp.views.fragments.MainPerCenterOperatorFragment;

import dagger.Component;

@FragmentScope
@Component(modules = MainPerCenterOperatorFragmentModule.class, dependencies = NetComponent.class)
public interface MainPerCenterOperatorFragmentComponect {
    void inject(MainPerCenterOperatorFragment mainPerCenterOperatorFragment);
}
