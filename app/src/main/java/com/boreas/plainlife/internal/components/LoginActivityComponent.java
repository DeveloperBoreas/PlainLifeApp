package com.boreas.plainlife.internal.components;

import com.boreas.plainlife.internal.modules.LoginActivityModule;
import com.boreas.plainlife.internal.scopers.ActivityScope;
import com.boreas.plainlife.mvp.views.activitys.LoginActivity;

import dagger.Component;

@ActivityScope
@Component(modules = LoginActivityModule.class,dependencies = {NetComponent.class,AppComponent.class})
public interface LoginActivityComponent {
    void inject(LoginActivity loginActivity);
}
