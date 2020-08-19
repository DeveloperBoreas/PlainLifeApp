package com.boreas.plainlife.internal.modules;

import com.boreas.plainlife.internal.scopers.FragmentScope;
import com.boreas.plainlife.mvp.views.viewinterfaces.EquipCommonFragmentInterface;

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
