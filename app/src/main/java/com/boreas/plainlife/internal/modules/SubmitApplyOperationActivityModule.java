package com.boreas.plainlife.internal.modules;

import com.boreas.plainlife.internal.scopers.ActivityScope;
import com.boreas.plainlife.mvp.views.viewinterfaces.SubmitApplyOperationActivityInterface;

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
