package com.xwzx.equipmanager.internal.components;

import com.xwzx.equipmanager.internal.modules.OperatorRecordActivityModule;
import com.xwzx.equipmanager.internal.scopers.ActivityScope;
import com.xwzx.equipmanager.mvp.views.activitys.OperatorMyRecordActivity;

import dagger.Component;

@ActivityScope
@Component(modules = OperatorRecordActivityModule.class, dependencies = NetComponent.class)
public interface OperatorRecordActivityComponect {
    void inject(OperatorMyRecordActivity activity);
}
