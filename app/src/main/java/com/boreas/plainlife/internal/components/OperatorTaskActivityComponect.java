package com.boreas.plainlife.internal.components;

import com.boreas.plainlife.internal.modules.OperatorTaskActivityModule;
import com.boreas.plainlife.internal.scopers.ActivityScope;
import com.boreas.plainlife.mvp.views.activitys.OperatorTaskActivity;

import dagger.Component;

@ActivityScope
@Component(modules = OperatorTaskActivityModule.class, dependencies = NetComponent.class)
public interface OperatorTaskActivityComponect {
    void inject(OperatorTaskActivity activity);
}
