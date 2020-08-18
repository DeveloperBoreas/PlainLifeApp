package com.xwzx.equipmanager.internal.components;

import com.xwzx.equipmanager.internal.modules.OperatorApplyActivityModule;
import com.xwzx.equipmanager.internal.scopers.ActivityScope;
import com.xwzx.equipmanager.mvp.views.activitys.OperatorApplyActivity;

import dagger.Component;

@ActivityScope
@Component(modules = OperatorApplyActivityModule.class, dependencies = NetComponent.class)
public interface OperatorApplyActivityComponect {
    void inject(OperatorApplyActivity activity);
}
