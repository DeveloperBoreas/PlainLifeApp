package com.boreas.plainlife.Location;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.text.TextUtils;
import android.util.Log;

import androidx.annotation.Nullable;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationListener;
import com.amap.api.maps.model.LatLng;
import com.amap.api.maps.model.Poi;
import com.boreas.plainlife.App;
import com.boreas.plainlife.Constant;
import com.boreas.plainlife.utils.RxTimer;
import com.orhanobut.logger.Logger;

import java.util.ArrayList;
import java.util.List;

public class LocationService extends Service {
    private static final String TAG = LocationService.class.getSimpleName();
    private GaoDeLocationClient mGaoDeLocationClient;
    private MBDAbstractLocationListener locationListener;
    private static LocationService locationService;
    private AMapLocation location;
    private List<LocationListener> locationListeners = new ArrayList<>();

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        locationService = this;
        this.initLocationService();
    }

    public static LocationService getInstance() {
        return locationService;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        initLocationService();
        return super.onStartCommand(intent, flags, startId);
    }


    @Override
    public void onDestroy() {
        destoryLocationService();
        super.onDestroy();
    }

    private void initLocationService() {
        if (null == mGaoDeLocationClient) {
            mGaoDeLocationClient = App.getInstance().getLocationClient();
            locationListener = new MBDAbstractLocationListener();
            mGaoDeLocationClient.registerListener(locationListener);
            mGaoDeLocationClient.start();
        }
    }

    private void destoryLocationService() {
        if (mGaoDeLocationClient != null) {
            mGaoDeLocationClient.unregisterListener(locationListener);
            mGaoDeLocationClient.stop();
            locationListener = null;
        }
        this.stopSelf();
    }

    public AMapLocation getBdLocation() {
        return this.location;
    }

    private LatLng latLng;
    public LatLng getLatLng() {
        if (this.location == null) {
            return new LatLng(39.907090, 116.391310);
        }
        return latLng;
    }
    private String currentAddress = "";
    public String getCurrentAddress(){
        return this.currentAddress;
    }

    public synchronized void registerLocationListener(LocationListener locationListener) {
        if (locationListener != null) {
            if (locationListeners.contains(locationListener)) {
                return;
            }
            this.locationListeners.add(locationListener);
        }
    }

    public synchronized void unregisterLocationListener(LocationListener locationListener) {
        if (locationListener != null) {
            if (locationListeners.contains(locationListener)) {
                locationListeners.remove(locationListener);
            }
        }
    }

    public void handlerLocationSuccessListener(AMapLocation location) {
        if (location != null) {
            if (this.locationListeners.size() > 0) {
                for (LocationListener locationListener : locationListeners) {
                    locationListener.onLocationSuccess(location);
                }
            }
        }
    }

    int count = 0;
    private boolean isLocationSuccess = false;
    private class MBDAbstractLocationListener implements AMapLocationListener {
        @Override
        public void onLocationChanged(AMapLocation aMapLocation) {
            if (null != aMapLocation && aMapLocation.getErrorCode() == 0) {
                isLocationSuccess = true;
                if (!Constant.DEBUG) {
                    StringBuffer sb = new StringBuffer(256);
                    sb.append("time : ");
                    sb.append(aMapLocation.getTime());
                    sb.append("\n定位类型( 1 GPS定位结果 2 前次定位结果 4 缓存定位结果 5 Wifi定位结果 6 基站定位结果 8 离线定位结果 9 最后位置缓存 ) getLocationType : ");// 定位类型
                    sb.append(aMapLocation.getLocationType());//0  定位失败  1 GPS定位结果 2 前次定位结果 4 缓存定位结果 5 Wifi定位结果 6 基站定位结果 8 离线定位结果 9 最后位置缓存
                    sb.append("\n定位类型说明 getLocationDetail : ");// *****对应的定位类型说明*****
                    sb.append(aMapLocation.getLocationDetail());
                    sb.append("\n纬度 latitude : ");// 纬度
                    sb.append(aMapLocation.getLatitude());
                    sb.append("\n经度 lontitude : ");// 经度
                    sb.append(aMapLocation.getLongitude());
                    sb.append("\n精度 getAccuracy : ");// 精度
                    sb.append(aMapLocation.getAccuracy());
                    sb.append("\n海拔 getAltitude : ");// 海拔
                    sb.append(aMapLocation.getAltitude());
                    sb.append("\n速度 getSpeed : ");// 速度
                    sb.append(aMapLocation.getSpeed());
                    sb.append("\n方向角 getBearing : ");// 方向角
                    sb.append(aMapLocation.getBearing());
                    sb.append("\n地址描述 getAddress : ");// 地址描述
                    sb.append(aMapLocation.getAddress());
                    sb.append("\n设备当前GPS状态 getGpsAccuracyStatus() : ");// 设备当前 GPS 状态
                    sb.append(aMapLocation.getGpsAccuracyStatus());
                    sb.append("" + count++);
//                    Log.e("LocationService ----------------", sb.toString());
                }
                if (isLocationSuccess) {
                    new RxTimer().timer(2 * 1000, number -> {
                        handlerLocationSuccessListener(aMapLocation);
                    });
                    location = aMapLocation;
                    latLng = new LatLng(aMapLocation.getLatitude(), aMapLocation.getLongitude());
                    currentAddress = aMapLocation.getAddress();
                }
            } else {
                isLocationSuccess = false;
                Logger.e(location.toString());
            }
        }
    }
}
