package com.xwzx.equipmanager.internal.components;

import com.xwzx.equipmanager.internal.modules.MainActivityModule;
import com.xwzx.equipmanager.internal.scopers.ActivityScope;
import com.xwzx.equipmanager.mvp.views.activitys.MainActivity;

import dagger.Component;

@ActivityScope
@Component(modules = MainActivityModule.class,dependencies = {NetComponent.class,AppComponent.class})
public interface MainActivityComponent {
    void inject(MainActivity mainActivity);
}
