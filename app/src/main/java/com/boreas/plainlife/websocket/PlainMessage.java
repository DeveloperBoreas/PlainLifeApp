package com.boreas.plainlife.websocket;

import android.content.Context;
import android.util.Log;

import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.JSONObject;
import com.amap.api.maps.model.LatLng;
import com.boreas.plainlife.App;
import com.boreas.plainlife.Constant;
import com.boreas.plainlife.Location.LocationService;
import com.boreas.plainlife.mq.ConnectionUtil;
import com.boreas.plainlife.mq.MessageCallBack;
import com.boreas.plainlife.mq.ResqonCallBack;
import com.boreas.plainlife.utils.PhoneInfoUtils;
import com.boreas.plainlife.utils.RxTimer;
import com.boreas.plainlife.utils.TimeUtils;
import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.Consumer;
import com.rabbitmq.client.DefaultConsumer;
import com.rabbitmq.client.Envelope;

import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.concurrent.atomic.AtomicLong;

import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;

import static com.boreas.plainlife.mq.CommonString.ADDRESS;
import static com.boreas.plainlife.mq.CommonString.GPS;
import static com.boreas.plainlife.mq.CommonString.GPSLAT;
import static com.boreas.plainlife.mq.CommonString.GPSLNG;
import static com.boreas.plainlife.mq.CommonString.GPSLOCTYPE;
import static com.boreas.plainlife.mq.CommonString.GPSTIME;
import static com.boreas.plainlife.mq.CommonString.GPSTYPE_GAODE;
import static com.boreas.plainlife.mq.CommonString.IMEI;
import static com.boreas.plainlife.mq.CommonString.MAC;
import static com.boreas.plainlife.mq.CommonString.NETWORK_TYPE;
import static com.boreas.plainlife.mq.CommonString.PHONE_NUMBER;
import static com.boreas.plainlife.mq.CommonString.POWER;
import static com.boreas.plainlife.mq.CommonString.TIME;


/**
 *
 */
public class PlainMessage {
    private static final String TAG = PlainMessage.class.getSimpleName();
    private Context context;
    public PlainMessage(Context context) {
        this.context = context;
    }

    public String hbService() {
        try {
            JSONObject jsonObject = baseObj();
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
        result.put(POWER,App.getInstance().getBatteryInfo().getLevel());//手机电量

        result.put(GPSTIME, TimeUtils.getUTCTimeDef());
        LatLng latLng = LocationService.getInstance().getLatLng();
        result.put(GPSLNG, latLng.longitude);
        result.put(GPSLAT, latLng.latitude);
        result.put(ADDRESS,LocationService.getInstance().getCurrentAddress());
        result.put(GPSLOCTYPE, GPSTYPE_GAODE);
        return result;
    }
}
