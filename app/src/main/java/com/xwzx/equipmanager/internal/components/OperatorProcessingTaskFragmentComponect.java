package com.xwzx.equipmanager.internal.components;

import com.xwzx.equipmanager.internal.modules.OperatorHistoryTaskFragmentModule;
import com.xwzx.equipmanager.internal.modules.OperatorProcessingTaskFragmentModule;
import com.xwzx.equipmanager.internal.scopers.FragmentScope;
import com.xwzx.equipmanager.mvp.views.fragments.OperatorHistoryTaskFragment;
import com.xwzx.equipmanager.mvp.views.fragments.OperatorProcessingCaseFragment;

import dagger.Component;

@FragmentScope
@Component(modules = OperatorProcessingTaskFragmentModule.class, dependencies = NetComponent.class)
public interface OperatorProcessingTaskFragmentComponect {
    void inject(OperatorProcessingCaseFragment fragment);
}
