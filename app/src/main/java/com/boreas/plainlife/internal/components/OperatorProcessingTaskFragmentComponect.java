package com.boreas.plainlife.internal.components;

import com.boreas.plainlife.internal.modules.OperatorProcessingTaskFragmentModule;
import com.boreas.plainlife.internal.scopers.FragmentScope;
import com.boreas.plainlife.mvp.views.fragments.OperatorProcessingCaseFragment;

import dagger.Component;

@FragmentScope
@Component(modules = OperatorProcessingTaskFragmentModule.class, dependencies = NetComponent.class)
public interface OperatorProcessingTaskFragmentComponect {
    void inject(OperatorProcessingCaseFragment fragment);
}
