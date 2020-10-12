package com.boreas.plainlife.internal.components;

import com.boreas.plainlife.internal.modules.MainActivityModule;
import com.boreas.plainlife.internal.scopers.ActivityScope;
import com.boreas.plainlife.mvp.views.activitys.MainActivity;

import dagger.Component;

@ActivityScope
@Component(modules = MainActivityModule.class,dependencies = {BeansComponent.class,NetComponent.class,AppComponent.class})
public interface MainActivityComponent {
    void inject(MainActivity mainActivity);
}
