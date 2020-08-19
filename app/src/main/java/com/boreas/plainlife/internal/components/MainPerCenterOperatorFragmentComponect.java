package com.boreas.plainlife.internal.components;

import com.boreas.plainlife.internal.modules.MainPerCenterOperatorFragmentModule;
import com.boreas.plainlife.internal.scopers.FragmentScope;
import com.boreas.plainlife.mvp.views.fragments.MainPerCenterOperatorFragment;

import dagger.Component;

@FragmentScope
@Component(modules = MainPerCenterOperatorFragmentModule.class, dependencies = NetComponent.class)
public interface MainPerCenterOperatorFragmentComponect {
    void inject(MainPerCenterOperatorFragment mainPerCenterOperatorFragment);
}
