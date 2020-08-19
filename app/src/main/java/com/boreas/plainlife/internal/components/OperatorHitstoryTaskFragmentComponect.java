package com.boreas.plainlife.internal.components;

import com.boreas.plainlife.internal.modules.OperatorHistoryTaskFragmentModule;
import com.boreas.plainlife.internal.scopers.FragmentScope;
import com.boreas.plainlife.mvp.views.fragments.OperatorHistoryTaskFragment;

import dagger.Component;

@FragmentScope
@Component(modules = OperatorHistoryTaskFragmentModule.class, dependencies = NetComponent.class)
public interface OperatorHitstoryTaskFragmentComponect {
    void inject(OperatorHistoryTaskFragment fragment);
}
