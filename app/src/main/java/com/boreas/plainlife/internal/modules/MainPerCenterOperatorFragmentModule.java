package com.boreas.plainlife.internal.modules;

import com.boreas.plainlife.internal.scopers.FragmentScope;
import com.boreas.plainlife.mvp.views.viewinterfaces.MainPerCenterOperatorFragmentInterface;

import dagger.Module;
import dagger.Provides;

@FragmentScope
@Module
public class MainPerCenterOperatorFragmentModule {
    private MainPerCenterOperatorFragmentInterface mainPerCenterOperatorFragmentInterface;

    public MainPerCenterOperatorFragmentModule(MainPerCenterOperatorFragmentInterface mainPerCenterOperatorFragmentInterface) {
        this.mainPerCenterOperatorFragmentInterface = mainPerCenterOperatorFragmentInterface;
    }
    @Provides
    public MainPerCenterOperatorFragmentInterface provideMainPerCenterOperatorFragmentInterface(){
        return this.mainPerCenterOperatorFragmentInterface;
    }
}
