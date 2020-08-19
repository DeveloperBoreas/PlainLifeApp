package com.boreas.plainlife.internal.modules;

import com.boreas.plainlife.internal.scopers.ActivityScope;
import com.boreas.plainlife.mvp.views.viewinterfaces.DeviceDescActivityInterface;

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
