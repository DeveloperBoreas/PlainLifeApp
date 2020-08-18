package com.xwzx.equipmanager.internal.modules;

import com.xwzx.equipmanager.internal.scopers.ActivityScope;
import com.xwzx.equipmanager.mvp.views.viewinterfaces.OperatorApplyActivityInterface;
import com.xwzx.equipmanager.mvp.views.viewinterfaces.OperatorTaskActivityInterface;

import dagger.Module;
import dagger.Provides;

@ActivityScope
@Module
public class OperatorApplyActivityModule {
    private OperatorApplyActivityInterface operatorApplyActivityInterface;

    public OperatorApplyActivityModule(OperatorApplyActivityInterface operatorApplyActivityInterface) {
        this.operatorApplyActivityInterface = operatorApplyActivityInterface;
    }
    @Provides
    public OperatorApplyActivityInterface provideOperatorApplyActivityInterface(){
        return this.operatorApplyActivityInterface;
    }
}
