package com.boreas.plainlife.internal.components;

import com.boreas.plainlife.internal.modules.OperatorRecordActivityModule;
import com.boreas.plainlife.internal.scopers.ActivityScope;
import com.boreas.plainlife.mvp.views.activitys.OperatorMyRecordActivity;

import dagger.Component;

@ActivityScope
@Component(modules = OperatorRecordActivityModule.class, dependencies = NetComponent.class)
public interface OperatorRecordActivityComponect {
    void inject(OperatorMyRecordActivity activity);
}
