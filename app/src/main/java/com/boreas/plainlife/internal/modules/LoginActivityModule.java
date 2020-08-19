package com.boreas.plainlife.internal.modules;

import com.boreas.plainlife.internal.scopers.ActivityScope;
import com.boreas.plainlife.mvp.views.viewinterfaces.LoginActivityInterface;

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
