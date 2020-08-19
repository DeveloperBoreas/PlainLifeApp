package com.boreas.plainlife.internal.modules;

import com.boreas.plainlife.internal.scopers.ActivityScope;
import com.boreas.plainlife.mvp.views.viewinterfaces.OperatorRecordActivityInterface;

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
