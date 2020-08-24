package com.boreas.plainlife.Location;


import com.amap.api.location.AMapLocation;

public interface LocationListener {
    /**
     * 定位成功
     *
     * @param location 百度定位回调的参数
     */
    void onLocationSuccess(AMapLocation location);
}
