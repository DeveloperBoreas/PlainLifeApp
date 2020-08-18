package com.xwzx.equipmanager;

import android.app.Application;

import com.lzf.easyfloat.EasyFloat;
import com.xwzx.equipmanager.internal.components.AppComponent;
import com.xwzx.equipmanager.internal.components.DBComponent;
import com.xwzx.equipmanager.internal.components.DaggerAppComponent;
import com.xwzx.equipmanager.internal.components.DaggerDBComponent;
import com.xwzx.equipmanager.internal.components.DaggerNetComponent;
import com.xwzx.equipmanager.internal.components.NetComponent;
import com.xwzx.equipmanager.internal.modules.AppModule;
import com.xwzx.equipmanager.utils.FileUtil;


public class App extends Application {
    private static App app;
    private AppComponent mAppComponent;
    private NetComponent mNetComponent;
    private DBComponent mDBComponent;
    public int role;
    private boolean NetWorkState = true;

    @Override
    public void onCreate() {
        super.onCreate();
        app = this;
        initComponent();
        initBugly();
        initFloatBall();
        initCopyObjToDataFile();
    }

    private void initCopyObjToDataFile() {
        FileUtil.copyFilesFromAssetsToData(this,"3dmodel.obj");
    }

    private void initFloatBall() {
        //因为设置悬浮球只在应用前台显示必须初始化
        EasyFloat.init(this);
    }

    public static App getInstance() {
        return app;
    }

    private void initBugly() {

    }

    private void initComponent() {
        mAppComponent = DaggerAppComponent.builder().appModule(new AppModule(this)).build();
        mNetComponent = DaggerNetComponent.builder().appComponent(mAppComponent).build();
        mDBComponent = DaggerDBComponent.builder().build();
    }

    public AppComponent getmAppComponent() {
        return mAppComponent;
    }

    public NetComponent getmNetComponent() {
        return mNetComponent;
    }

    public DBComponent getmDBComponent() {
        return mDBComponent;
    }

    public boolean getNetWorkState() {
        return this.NetWorkState;
    }
}
