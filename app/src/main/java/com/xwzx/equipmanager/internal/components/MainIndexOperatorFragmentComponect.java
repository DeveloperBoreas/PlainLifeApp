package com.xwzx.equipmanager.internal.components;

import com.xwzx.equipmanager.internal.modules.MainIndexOperatorFragmentModule;
import com.xwzx.equipmanager.internal.scopers.FragmentScope;
import com.xwzx.equipmanager.mvp.views.fragments.MainIndexOperatorFragment;

import dagger.Component;

@FragmentScope
@Component(modules = MainIndexOperatorFragmentModule.class, dependencies = NetComponent.class)
public interface MainIndexOperatorFragmentComponect {
    void inject(MainIndexOperatorFragment mainPerCenterOperatorFragment);
}
