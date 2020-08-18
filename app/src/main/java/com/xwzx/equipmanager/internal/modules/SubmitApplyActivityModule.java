package com.xwzx.equipmanager.internal.modules;

import com.xwzx.equipmanager.internal.scopers.ActivityScope;
import com.xwzx.equipmanager.mvp.views.viewinterfaces.SubmitApplyActivityInterface;

import dagger.Module;
import dagger.Provides;

@ActivityScope
@Module
public class SubmitApplyActivityModule {
    private SubmitApplyActivityInterface submitApplyActivityInterface;

    public SubmitApplyActivityModule(SubmitApplyActivityInterface submitApplyActivityInterface) {
        this.submitApplyActivityInterface = submitApplyActivityInterface;
    }

    @Provides
    public SubmitApplyActivityInterface provideSubmitApplyActivityInterface(){
        return this.submitApplyActivityInterface;
    }
}
