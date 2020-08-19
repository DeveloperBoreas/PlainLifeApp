package com.boreas.plainlife.internal.components;

import android.content.Context;

import com.boreas.plainlife.App;
import com.boreas.plainlife.internal.modules.AppModule;

import dagger.Component;

@Component(modules = {AppModule.class})
public interface AppComponent {
    Context getContext();
    App getApp();
}
