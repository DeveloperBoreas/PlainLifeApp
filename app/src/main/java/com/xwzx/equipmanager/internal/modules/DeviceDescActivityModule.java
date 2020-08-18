package com.xwzx.equipmanager.internal.modules;

import com.xwzx.equipmanager.internal.scopers.ActivityScope;
import com.xwzx.equipmanager.mvp.views.viewinterfaces.DeviceDescActivityInterface;

import dagger.Module;
import dagger.Provides;

@ActivityScope
@Module
public class DeviceDescActivityModule {

    private DeviceDescActivityInterface descActivityInterface;

    public DeviceDescActivityModule(DeviceDescActivityInterface descActivityInterface) {
        this.descActivityInterface = descActivityInterface;
    }
    @Provides
    public DeviceDescActivityInterface provideDeviceDescActivityInterface() {
        return descActivityInterface;
    }
}
