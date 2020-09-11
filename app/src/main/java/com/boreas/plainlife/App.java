package com.boreas.plainlife;

import android.app.Application;
import android.app.Service;
import android.os.Vibrator;
import android.view.LayoutInflater;

import com.boreas.commonlib.xskinloader.ExtraAttrRegister;
import com.boreas.commonlib.xskinloader.SkinInflaterFactory;
import com.boreas.commonlib.xskinloader.util.AssetFileUtils;
import com.boreas.plainlife.Location.GaoDeLocationClient;
import com.boreas.plainlife.receiver.BatteryInfo;
import com.lzf.easyfloat.EasyFloat;
import com.boreas.plainlife.internal.components.AppComponent;
import com.boreas.plainlife.internal.components.DBComponent;
import com.boreas.plainlife.internal.components.DaggerAppComponent;
import com.boreas.plainlife.internal.components.DaggerDBComponent;
import com.boreas.plainlife.internal.components.DaggerNetComponent;
import com.boreas.plainlife.internal.components.NetComponent;
import com.boreas.plainlife.internal.modules.AppModule;
import com.boreas.plainlife.utils.FileUtil;

import java.io.File;


public class App extends Application {
    private static App app;
    private AppComponent mAppComponent;
    private NetComponent mNetComponent;
    private DBComponent mDBComponent;
    private boolean NetWorkState = true;
    private GaoDeLocationClient locationClient;
    private BatteryInfo batteryInfo;
    public String skinPath;

    @Override
    public void onCreate() {
        super.onCreate();
        this.app = this;
        this.initComponent();
        this.initBugly();
        this.initFloatBall();
        this.initCopyObjToDataFile();
        this.initSkin();
        this.initGaoDeLocation();
    }

    private void initSkin() {
        // 处理自定义的换肤属性
        ExtraAttrRegister.init();
        // 使用Application的LayoutInflater加载的view也能换肤
        SkinInflaterFactory.setFactory(LayoutInflater.from(this));
    }

    private void initCopyObjToDataFile() {
        FileUtil.copyFilesFromAssetsToData(this, "3dmodel.obj");
        skinPath = getFilesDir().getAbsolutePath() + File.separator + "mipmap.apk";
        FileUtil.copyFilesFromAssetsToData(this, "mipmap.apk");
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

    private void initGaoDeLocation() {
        locationClient = new GaoDeLocationClient(getApplicationContext());
    }

    public GaoDeLocationClient getLocationClient() {
        return this.locationClient;
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

    public BatteryInfo getBatteryInfo() {
        if (this.batteryInfo == null) {
            this.batteryInfo = new BatteryInfo();
        }
        return this.batteryInfo;
    }

    public void setBatteryInfo(BatteryInfo batteryInfo) {
        this.batteryInfo = batteryInfo;
    }
}
