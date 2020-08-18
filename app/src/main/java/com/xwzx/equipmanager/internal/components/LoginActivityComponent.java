package com.xwzx.equipmanager.internal.components;

import com.xwzx.equipmanager.internal.modules.LoginActivityModule;
import com.xwzx.equipmanager.internal.scopers.ActivityScope;
import com.xwzx.equipmanager.mvp.views.activitys.LoginActivity;

import dagger.Component;

@ActivityScope
@Component(modules = LoginActivityModule.class,dependencies = {NetComponent.class,AppComponent.class})
public interface LoginActivityComponent {
    void inject(LoginActivity loginActivity);
}
