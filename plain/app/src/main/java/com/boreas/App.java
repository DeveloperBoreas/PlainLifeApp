package com.boreas;

import android.app.Application;
import android.content.Context;

import com.boreas.di.componects.AppComponent;
import com.boreas.di.componects.DaggerAppComponent;
import com.boreas.di.componects.DaggerNetComponent;
import com.boreas.di.componects.NetComponent;
import com.boreas.di.modules.AppModule;
import com.boreas.di.modules.NetModule;
import com.orhanobut.logger.LogLevel;
import com.orhanobut.logger.Logger;


/**
 *
 * @author boreas
 * @date 2018/2/23
 */

public class App extends Application {

    private static Context appContext = null;
    private NetComponent netComponent = null;
    private AppComponent appComponent = null;

    @Override
    public void onCreate() {
        appContext = this;
        super.onCreate();
        initNet();
        initLogger();
    }

    private void initLogger() {
        Logger.init().logLevel(LogLevel.FULL);
    }

    private void initNet() {
        appComponent = DaggerAppComponent.builder()
                .appModule(new AppModule(this))
                .build();
        netComponent = DaggerNetComponent.builder()
                .netModule(new NetModule())
                .build();
    }

    public static Context getContext(){
        return appContext;
    }


    public NetComponent getNetComponent(){
        return netComponent;
    }

    public AppComponent getAppComponent(){
        return appComponent;
    }
}
