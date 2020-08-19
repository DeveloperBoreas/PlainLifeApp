package com.boreas.plainlife.internal.modules;

import com.boreas.plainlife.internal.scopers.FragmentScope;
import com.boreas.plainlife.mvp.views.viewinterfaces.EquipFragmentInterface;

import dagger.Module;
import dagger.Provides;

@FragmentScope
@Module
public class MainEquipFragmentModule {
    private EquipFragmentInterface equipFragmentInterface;

    public MainEquipFragmentModule(EquipFragmentInterface equipFragmentInterface) {
        this.equipFragmentInterface = equipFragmentInterface;
    }
    @Provides
    public EquipFragmentInterface provideEquipFragmentInterface(){
        return this.equipFragmentInterface;
    }
}
