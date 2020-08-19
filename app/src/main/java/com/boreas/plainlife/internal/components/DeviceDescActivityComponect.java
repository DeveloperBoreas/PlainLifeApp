package com.boreas.plainlife.internal.components;

import com.boreas.plainlife.internal.modules.DeviceDescActivityModule;
import com.boreas.plainlife.internal.scopers.ActivityScope;
import com.boreas.plainlife.mvp.views.activitys.DeviceDescActivity;

import dagger.Component;

@ActivityScope
@Component(modules = DeviceDescActivityModule.class,dependencies = NetComponent.class)
public interface DeviceDescActivityComponect {
    void inject(DeviceDescActivity deviceDescActivity);
}
