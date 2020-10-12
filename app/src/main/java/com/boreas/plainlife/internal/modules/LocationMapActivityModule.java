package com.boreas.plainlife.internal.modules;

import com.boreas.plainlife.internal.scopers.ActivityScope;
import com.boreas.plainlife.mvp.views.viewinterfaces.LocationMapActivityInterface;

import dagger.Module;
import dagger.Provides;

@ActivityScope
@Module
public class LocationMapActivityModule {
    private LocationMapActivityInterface locationMapActivityInterface;

    public LocationMapActivityModule(LocationMapActivityInterface locationMapActivityInterface) {
        this.locationMapActivityInterface = locationMapActivityInterface;
    }

    @Provides
    public LocationMapActivityInterface provideLocationMapActivityInterface(){
        return this.locationMapActivityInterface;
    }
}
