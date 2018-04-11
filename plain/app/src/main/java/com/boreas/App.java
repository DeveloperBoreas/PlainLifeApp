package com.boreas;

import android.app.Activity;
import android.app.Application;
import android.app.Service;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;

import com.boreas.di.componects.AppComponent;
import com.boreas.di.componects.DaggerAppComponent;
import com.boreas.di.componects.DaggerNetComponent;
import com.boreas.di.componects.NetComponent;
import com.boreas.di.modules.AppModule;
import com.boreas.di.modules.NetModule;
import com.boreas.service.MusicService;
import com.boreas.ui.notification.MusicNotification;
import com.orhanobut.logger.LogLevel;
import com.orhanobut.logger.Logger;


/**
 *
 * @author boreas
 * @date 2018/2/23
 */

public class App extends Application implements Application.ActivityLifecycleCallbacks {

    private static Context appContext = null;
    private NetComponent netComponent = null;
    private AppComponent appComponent = null;
    public static App app;
    private IMusicPlayer mMusicPlayerService;
    private MusicNotification mMusicNotification;
    @Override
    public void onCreate() {
        appContext = this;
        app = this;
        bindService();
        super.onCreate();
        initNet();
        initLogger();
        this.registerActivityLifecycleCallbacks(this);
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

    /**service**/
    public IMusicPlayer getMusicPlayerService() {
        return mMusicPlayerService;
    }
    ServiceConnection mServiceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            mMusicPlayerService = IMusicPlayer.Stub.asInterface(service);
            linkSuccess = true;
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            bindService();
        }
    };
    private void bindService() {
        Intent intent = new Intent(App.app, MusicService.class);
        bindService(intent, mServiceConnection, Service.BIND_AUTO_CREATE);
    }

    public static boolean linkSuccess;


    int isCurrentRunningForeground;
    @Override
    public void onActivityStarted(Activity activity) {
        if (isCurrentRunningForeground == 0) {// front
            if (mMusicNotification != null) {
                mMusicNotification.unregisterListener();
            }
        }
        isCurrentRunningForeground++;
    }
    private void showNotification() throws RemoteException {
        if (mMusicNotification == null) {
            mMusicNotification = new MusicNotification(app, mMusicPlayerService);
        }
        mMusicNotification.registerListener();
        mMusicNotification.notifyMusic();
    }

    @Override
    public void onActivityStopped(Activity activity) {
        isCurrentRunningForeground--;
        if (isCurrentRunningForeground == 0) { // back
            try {
                showNotification();
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
    }





    @Override
    public void onActivityCreated(Activity activity, Bundle savedInstanceState) {

    }

    @Override
    public void onActivityResumed(Activity activity) {

    }

    @Override
    public void onActivityPaused(Activity activity) {

    }
    @Override
    public void onActivitySaveInstanceState(Activity activity, Bundle outState) {

    }

    @Override
    public void onActivityDestroyed(Activity activity) {

    }

    /**service**/
}
