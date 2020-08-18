package com.xwzx.equipmanager.internal.components;

import com.xwzx.equipmanager.internal.modules.OperatorHistoryTaskFragmentModule;
import com.xwzx.equipmanager.internal.scopers.FragmentScope;
import com.xwzx.equipmanager.mvp.views.fragments.OperatorHistoryTaskFragment;

import dagger.Component;

@FragmentScope
@Component(modules = OperatorHistoryTaskFragmentModule.class, dependencies = NetComponent.class)
public interface OperatorHitstoryTaskFragmentComponect {
    void inject(OperatorHistoryTaskFragment fragment);
}
