package com.xwzx.equipmanager.internal.modules;

import com.xwzx.equipmanager.internal.scopers.ActivityScope;
import com.xwzx.equipmanager.mvp.views.viewinterfaces.OperatorRecordActivityInterface;
import com.xwzx.equipmanager.mvp.views.viewinterfaces.OperatorTaskActivityInterface;

import dagger.Module;
import dagger.Provides;

@ActivityScope
@Module
public class OperatorRecordActivityModule {
    private OperatorRecordActivityInterface operatorRecordActivityInterface;

    public OperatorRecordActivityModule(OperatorRecordActivityInterface operatorRecordActivityInterface) {
        this.operatorRecordActivityInterface = operatorRecordActivityInterface;
    }
    @Provides
    public OperatorRecordActivityInterface provideOperatorRecordActivityInterface(){
        return this.operatorRecordActivityInterface;
    }
}
