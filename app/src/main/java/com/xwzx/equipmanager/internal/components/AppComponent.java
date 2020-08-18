package com.xwzx.equipmanager.internal.components;

import android.content.Context;

import com.xwzx.equipmanager.App;
import com.xwzx.equipmanager.internal.modules.AppModule;

import dagger.Component;

@Component(modules = {AppModule.class})
public interface AppComponent {
    Context getContext();
    App getApp();
}
