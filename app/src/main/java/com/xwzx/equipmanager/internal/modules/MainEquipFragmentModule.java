package com.xwzx.equipmanager.internal.modules;

import com.xwzx.equipmanager.internal.scopers.FragmentScope;
import com.xwzx.equipmanager.mvp.views.viewinterfaces.EquipFragmentInterface;

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
