package com.xwzx.equipmanager.internal.modules;

import com.xwzx.equipmanager.internal.scopers.FragmentScope;
import com.xwzx.equipmanager.mvp.views.viewinterfaces.EquipCommonFragmentInterface;

import dagger.Module;
import dagger.Provides;

@FragmentScope
@Module
public class EquipCommonFragmentModule {
    private EquipCommonFragmentInterface equipCommonFragmentInterface;

    public EquipCommonFragmentModule(EquipCommonFragmentInterface equipCommonFragmentInterface) {
        this.equipCommonFragmentInterface = equipCommonFragmentInterface;
    }
    @Provides
    public EquipCommonFragmentInterface provideEquipCommonFragmentInterface(){
        return this.equipCommonFragmentInterface;
    }
}
