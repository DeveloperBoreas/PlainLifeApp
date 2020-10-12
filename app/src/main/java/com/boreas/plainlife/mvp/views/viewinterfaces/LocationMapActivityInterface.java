package com.boreas.plainlife.mvp.views.viewinterfaces;

import com.amap.api.maps.model.LatLng;
import com.boreas.plainlife.mvp.ViewInterface;

import java.util.ArrayList;

public interface LocationMapActivityInterface extends ViewInterface {
    /**
     * 数据请求成功回调,根据用户查询历史经纬度
     * @param tracks
     */
    void onSuccess(ArrayList<LatLng> tracks);

    /**
     * 数据请求失败回调
     */
    void onFailed(String message);
}
