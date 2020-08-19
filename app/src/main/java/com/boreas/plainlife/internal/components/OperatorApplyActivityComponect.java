package com.boreas.plainlife.internal.components;

import com.boreas.plainlife.internal.modules.OperatorApplyActivityModule;
import com.boreas.plainlife.internal.scopers.ActivityScope;
import com.boreas.plainlife.mvp.views.activitys.OperatorApplyActivity;

import dagger.Component;

@ActivityScope
@Component(modules = OperatorApplyActivityModule.class, dependencies = NetComponent.class)
public interface OperatorApplyActivityComponect {
    void inject(OperatorApplyActivity activity);
}
