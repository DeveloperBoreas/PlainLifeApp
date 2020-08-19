package com.boreas.plainlife.internal.modules;

import com.boreas.plainlife.internal.scopers.FragmentScope;
import com.boreas.plainlife.mvp.views.viewinterfaces.MainIndexOperatorFragmentInterface;

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
