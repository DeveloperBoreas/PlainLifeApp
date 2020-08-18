package com.xwzx.equipmanager.internal.modules;

import com.xwzx.equipmanager.internal.scopers.FragmentScope;
import com.xwzx.equipmanager.mvp.views.viewinterfaces.MainPerCenterKeeperFragmentInterface;
import com.xwzx.equipmanager.mvp.views.viewinterfaces.MainPerCenterOperatorFragmentInterface;

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
