package com.xwzx.equipmanager.internal.modules;

import com.xwzx.equipmanager.internal.scopers.FragmentScope;
import com.xwzx.equipmanager.mvp.views.viewinterfaces.MainIndexOperatorFragmentInterface;
import com.xwzx.equipmanager.mvp.views.viewinterfaces.MainPerCenterOperatorFragmentInterface;

import dagger.Module;
import dagger.Provides;

@FragmentScope
@Module
public class MainIndexOperatorFragmentModule {
    private MainIndexOperatorFragmentInterface mainIndexOperatorFragmentInterface;

    public MainIndexOperatorFragmentModule(MainIndexOperatorFragmentInterface mainIndexOperatorFragmentInterface) {
        this.mainIndexOperatorFragmentInterface = mainIndexOperatorFragmentInterface;
    }
    @Provides
    public MainIndexOperatorFragmentInterface provideMainIndexOperatorFragmentInterface(){
        return this.mainIndexOperatorFragmentInterface;
    }
}
