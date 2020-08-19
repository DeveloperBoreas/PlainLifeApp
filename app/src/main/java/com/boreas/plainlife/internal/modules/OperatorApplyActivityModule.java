package com.boreas.plainlife.internal.modules;

import com.boreas.plainlife.internal.scopers.ActivityScope;
import com.boreas.plainlife.mvp.views.viewinterfaces.OperatorApplyActivityInterface;

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
