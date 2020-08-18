package com.xwzx.equipmanager.internal.modules;

import com.xwzx.equipmanager.internal.scopers.ActivityScope;
import com.xwzx.equipmanager.mvp.views.viewinterfaces.SubmitApplyOperationActivityInterface;

import dagger.Module;
import dagger.Provides;

@ActivityScope
@Module
public class SubmitApplyOperationActivityModule {

    private SubmitApplyOperationActivityInterface submitApplyOperationActivityInterface;

    public SubmitApplyOperationActivityModule(SubmitApplyOperationActivityInterface submitApplyOperationActivityInterface) {
        this.submitApplyOperationActivityInterface = submitApplyOperationActivityInterface;
    }

    @Provides
    public SubmitApplyOperationActivityInterface provideSubmitApplyOperationActivityInterface() {
        return this.submitApplyOperationActivityInterface;
    }
}
