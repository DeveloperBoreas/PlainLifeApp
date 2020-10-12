package com.boreas.plainlife.internal.components;


import com.boreas.plainlife.internal.modules.LocationLoveFragmentModule;
import com.boreas.plainlife.internal.scopers.ActivityScope;
import com.boreas.plainlife.mvp.views.fragments.location.LocationLoveFragment;

import dagger.Component;

@ActivityScope
@Component(modules = LocationLoveFragmentModule.class,dependencies = {NetComponent.class,BeansComponent.class})
public interface LocationLoveFragmentComponent {
    void inject(LocationLoveFragment locationLoveFragment);
}
