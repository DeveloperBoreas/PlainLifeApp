package com.boreas.plainlife.websocket;


import android.content.Context;

import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.JSONObject;
import com.amap.api.maps.model.LatLng;
import com.boreas.plainlife.App;
import com.boreas.plainlife.Location.LocationService;
import com.boreas.plainlife.utils.PhoneInfoUtils;
import com.boreas.plainlife.utils.TimeUtils;

import static com.boreas.plainlife.mq.CommonString.*;


/**
 *  Socket 位置消息
 */
public class PlainMessage {
    private static final String TAG = PlainMessage.class.getSimpleName();
    private Context context;
    public PlainMessage(Context context) {
        this.context = context;
    }

    public String hbService(String userId) {
        try {
            JSONObject jsonObject = baseObj();
            jsonObject.put(USERID,userId);
            return jsonObject.toString();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 添加请求公共字段
     */
    private JSONObject baseObj() throws JSONException {
        JSONObject result = new JSONObject();
        result.put(IMEI, PhoneInfoUtils.getImeiNum(this.context)); //手机IMEI
//        result.put(PHONE_NUMBER, PhoneInfoUtils.getPhoneNum(this.context)); //手机号
        result.put(PHONE_NUMBER, "13051089921"); //手机号
        result.put(NETWORK_TYPE, PhoneInfoUtils.GetNetworkType(this.context)); //网络状态
        result.put(TIME, TimeUtils.getUTCTimeDef());//时间
        result.put(MAC, PhoneInfoUtils.getMobileMAC(this.context)); //MAC地址
        result.put(POWER, App.getInstance().getBatteryInfo().getLevel());//手机电量
        result.put(GPSTIME, TimeUtils.getUTCTimeDef());
        LatLng latLng = LocationService.getInstance().getLatLng();
        result.put(GPSLNG, latLng.longitude);
        result.put(GPSLAT, latLng.latitude);
        result.put(ADDRESS,LocationService.getInstance().getCurrentAddress());
        result.put(GPSLOCTYPE, GPSTYPE_GAODE);
        return result;
    }
}
