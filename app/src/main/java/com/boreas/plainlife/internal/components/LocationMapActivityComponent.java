package com.boreas.plainlife.internal.components;

import com.boreas.plainlife.internal.modules.LocationMapActivityModule;
import com.boreas.plainlife.internal.scopers.ActivityScope;
import com.boreas.plainlife.mvp.views.activitys.LocationMapActivity;

import dagger.Component;

@ActivityScope
@Component(modules = LocationMapActivityModule.class,dependencies = {NetComponent.class,AppComponent.class,BeansComponent.class})
public interface LocationMapActivityComponent {
    void inject(LocationMapActivity locationMapActivity);
}
