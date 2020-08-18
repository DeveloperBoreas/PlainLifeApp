package com.xwzx.equipmanager.internal.modules;

import com.xwzx.equipmanager.internal.scopers.ActivityScope;
import com.xwzx.equipmanager.mvp.views.viewinterfaces.LoginActivityInterface;

import dagger.Module;
import dagger.Provides;

@ActivityScope
@Module
public class LoginActivityModule {
    private LoginActivityInterface loginActivityInterface;

    public LoginActivityModule(LoginActivityInterface loginActivityInterface) {
        this.loginActivityInterface = loginActivityInterface;
    }

    @Provides
    public LoginActivityInterface provideLoginActivityInterface(){
        return this.loginActivityInterface;
    }
}
