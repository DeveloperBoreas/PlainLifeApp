package com.xwzx.equipmanager.internal.modules;

import com.xwzx.equipmanager.internal.scopers.FragmentScope;
import com.xwzx.equipmanager.mvp.views.viewinterfaces.MainIndexOperatorFragmentInterface;
import com.xwzx.equipmanager.mvp.views.viewinterfaces.OperatorHistoryTaskFragmentInterface;

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
