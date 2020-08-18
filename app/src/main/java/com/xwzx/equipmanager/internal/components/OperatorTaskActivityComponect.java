package com.xwzx.equipmanager.internal.components;

import com.xwzx.equipmanager.internal.modules.OperatorTaskActivityModule;
import com.xwzx.equipmanager.internal.scopers.ActivityScope;
import com.xwzx.equipmanager.mvp.views.activitys.OperatorTaskActivity;

import dagger.Component;

@ActivityScope
@Component(modules = OperatorTaskActivityModule.class, dependencies = NetComponent.class)
public interface OperatorTaskActivityComponect {
    void inject(OperatorTaskActivity activity);
}
