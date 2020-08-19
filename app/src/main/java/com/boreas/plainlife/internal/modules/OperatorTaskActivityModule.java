package com.boreas.plainlife.internal.modules;

import com.boreas.plainlife.internal.scopers.ActivityScope;
import com.boreas.plainlife.mvp.views.viewinterfaces.OperatorTaskActivityInterface;

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
