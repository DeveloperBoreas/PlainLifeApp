package com.xwzx.equipmanager.internal.modules;

import com.xwzx.equipmanager.internal.scopers.ActivityScope;
import com.xwzx.equipmanager.mvp.views.viewinterfaces.OperatorTaskActivityInterface;

import dagger.Module;
import dagger.Provides;

@ActivityScope
@Module
public class OperatorTaskActivityModule {
    private OperatorTaskActivityInterface operatorTaskActivityInterface;

    public OperatorTaskActivityModule(OperatorTaskActivityInterface operatorTaskActivityInterface) {
        this.operatorTaskActivityInterface = operatorTaskActivityInterface;
    }
    @Provides
    public OperatorTaskActivityInterface provideOperatorTaskActivityInterface(){
        return this.operatorTaskActivityInterface;
    }
}
