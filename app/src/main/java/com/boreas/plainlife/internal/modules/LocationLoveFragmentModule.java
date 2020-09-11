package com.boreas.plainlife.internal.modules;

import com.boreas.plainlife.internal.scopers.ActivityScope;
import com.boreas.plainlife.mvp.views.viewinterfaces.AddLoveActivityInterface;
import com.boreas.plainlife.mvp.views.viewinterfaces.LocationLoveFragmentInterface;

import dagger.Module;
import dagger.Provides;

@ActivityScope
@Module
public class LocationLoveFragmentModule {
    private LocationLoveFragmentInterface locationLoveFragmentInterface;

    public LocationLoveFragmentModule(LocationLoveFragmentInterface locationLoveFragmentInterface) {
        this.locationLoveFragmentInterface = locationLoveFragmentInterface;
    }

    @Provides
    public LocationLoveFragmentInterface provideLocationLoveFragmentInterface(){
        return this.locationLoveFragmentInterface;
    }
}
