package com.xwzx.equipmanager.internal.modules;

import com.xwzx.equipmanager.internal.scopers.FragmentScope;
import com.xwzx.equipmanager.mvp.views.viewinterfaces.OperatorHistoryTaskFragmentInterface;
import com.xwzx.equipmanager.mvp.views.viewinterfaces.OperatorProcessingTaskFragmentInterface;

import dagger.Module;
import dagger.Provides;

@FragmentScope
@Module
public class OperatorProcessingTaskFragmentModule {
    private OperatorProcessingTaskFragmentInterface viewInterface;

    public OperatorProcessingTaskFragmentModule(OperatorProcessingTaskFragmentInterface viewInterface) {
        this.viewInterface = viewInterface;
    }
    @Provides
    public OperatorProcessingTaskFragmentInterface provideOperatorProcessingTaskFragmentInterface(){
        return this.viewInterface;
    }
}
