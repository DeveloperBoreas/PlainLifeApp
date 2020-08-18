package com.xwzx.equipmanager.internal.components;

import com.xwzx.equipmanager.internal.modules.DeviceDescActivityModule;
import com.xwzx.equipmanager.internal.scopers.ActivityScope;
import com.xwzx.equipmanager.mvp.views.activitys.DeviceDescActivity;

import dagger.Component;

@ActivityScope
@Component(modules = DeviceDescActivityModule.class,dependencies = NetComponent.class)
public interface DeviceDescActivityComponect {
    void inject(DeviceDescActivity deviceDescActivity);
}
