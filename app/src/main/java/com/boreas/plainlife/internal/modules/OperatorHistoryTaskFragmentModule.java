package com.boreas.plainlife.internal.modules;

import com.boreas.plainlife.internal.scopers.FragmentScope;
import com.boreas.plainlife.mvp.views.viewinterfaces.OperatorHistoryTaskFragmentInterface;

import dagger.Module;
import dagger.Provides;

@FragmentScope
@Module
public class OperatorHistoryTaskFragmentModule {
    private OperatorHistoryTaskFragmentInterface viewInterface;

    public OperatorHistoryTaskFragmentModule(OperatorHistoryTaskFragmentInterface viewInterface) {
        this.viewInterface = viewInterface;
    }
    @Provides
    public OperatorHistoryTaskFragmentInterface provideOperatorHistoryTaskFragmentInterface(){
        return this.viewInterface;
    }
}
