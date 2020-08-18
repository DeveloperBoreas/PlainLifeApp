package com.xwzx.equipmanager.internal.components;

import com.xwzx.equipmanager.internal.modules.SubmitApplyOperationActivityModule;
import com.xwzx.equipmanager.internal.scopers.ActivityScope;
import com.xwzx.equipmanager.mvp.views.activitys.SubmitApplyOperationActivity;

import dagger.Component;

@ActivityScope
@Component(modules = SubmitApplyOperationActivityModule.class,dependencies = {NetComponent.class,AppComponent.class})
public interface SubmitApplyOperationActivityComponent {
    void inject(SubmitApplyOperationActivity submitApplyOperationActivity);
}
