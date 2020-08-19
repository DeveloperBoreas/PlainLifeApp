package com.boreas.plainlife.internal.components;

import com.boreas.plainlife.internal.modules.SubmitApplyOperationActivityModule;
import com.boreas.plainlife.internal.scopers.ActivityScope;
import com.boreas.plainlife.mvp.views.activitys.SubmitApplyOperationActivity;

import dagger.Component;

@ActivityScope
@Component(modules = SubmitApplyOperationActivityModule.class,dependencies = {NetComponent.class,AppComponent.class})
public interface SubmitApplyOperationActivityComponent {
    void inject(SubmitApplyOperationActivity submitApplyOperationActivity);
}
