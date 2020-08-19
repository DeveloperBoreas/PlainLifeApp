package com.boreas.plainlife.internal.components;

import com.boreas.plainlife.internal.modules.MainIndexOperatorFragmentModule;
import com.boreas.plainlife.internal.scopers.FragmentScope;
import com.boreas.plainlife.mvp.views.fragments.MainIndexOperatorFragment;

import dagger.Component;

@FragmentScope
@Component(modules = MainIndexOperatorFragmentModule.class, dependencies = NetComponent.class)
public interface MainIndexOperatorFragmentComponect {
    void inject(MainIndexOperatorFragment mainPerCenterOperatorFragment);
}
