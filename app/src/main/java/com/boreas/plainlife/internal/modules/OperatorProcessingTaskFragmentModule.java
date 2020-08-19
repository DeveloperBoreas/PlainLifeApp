package com.boreas.plainlife.internal.modules;

import com.boreas.plainlife.internal.scopers.FragmentScope;
import com.boreas.plainlife.mvp.views.viewinterfaces.OperatorProcessingTaskFragmentInterface;

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
