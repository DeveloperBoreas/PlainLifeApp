package com.boreas.plainlife.internal.modules;

import com.boreas.plainlife.App;
import com.boreas.plainlife.ObjectPool;
import com.boreas.plainlife.websocket.PlainMessage;

import dagger.Module;
import dagger.Provides;

@Module
public class BeansModule {

    private App app;

    public BeansModule(App app) {
        this.app = app;
    }

    @Provides
    public PlainMessage providePlainMessage() {
        return new PlainMessage(app.getApplicationContext());
    }
    @Provides
    public ObjectPool provideObjectPool() {
        return new ObjectPool();
    }
}
