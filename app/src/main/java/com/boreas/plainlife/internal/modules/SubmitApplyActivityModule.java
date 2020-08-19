package com.boreas.plainlife.internal.modules;

import com.boreas.plainlife.internal.scopers.ActivityScope;
import com.boreas.plainlife.mvp.views.viewinterfaces.SubmitApplyActivityInterface;

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
