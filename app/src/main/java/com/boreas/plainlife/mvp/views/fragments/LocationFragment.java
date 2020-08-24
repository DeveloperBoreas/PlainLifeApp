package com.boreas.plainlife.mvp.views.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.amap.api.location.AMapLocation;
import com.amap.api.maps.AMap;
import com.amap.api.maps.MapView;
import com.boreas.plainlife.Location.LocationListener;
import com.boreas.plainlife.Location.LocationService;
import com.boreas.plainlife.R;
import com.boreas.plainlife.base.BaseFragment;
import com.boreas.plainlife.databinding.FragmentLocationParentlayoutBinding;
import com.boreas.plainlife.mq.RabbitMQConfiguration;
import com.boreas.plainlife.mq.ResqonCallBack;
import com.boreas.plainlife.utils.RxTimer;

import java.util.ArrayList;

public class LocationFragment extends BaseFragment<FragmentLocationParentlayoutBinding> implements LocationListener {
    private RabbitMQConfiguration rabbitMQConfiguration;
    private MapView mMapView = null;
    private AMap aMap;
    @Override
    public void lazyFetchData() {

    }

    @Override
    public int setContent() {
        return R.layout.fragment_location_parentlayout;
    }

    @Override
    public void initView() {
        LocationService.getInstance().registerLocationListener(this);
        this.mMapView = this.binding.map;
        if (aMap == null) {
            aMap = this.mMapView.getMap();
        }
    }

    @Override
    public void initListener() {

    }

    @Override
    public void initComponent() {

    }

    @Override
    public boolean onBackPressed() {
        return false;
    }

    private void handlerReceiverPositionMessage() {
        //        this.rabbitMQConfiguration = RabbitMQConfiguration.getInstance();
//        this.handlerReceiverPositionMessage();
//        RxTimer hbRxTimer = new RxTimer();
//        hbRxTimer.interval(5000, number -> {
//            this.rabbitMQConfiguration.basicPublish(() -> "测试内容");
//        });
        rabbitMQConfiguration.basicConsumer(new ResqonCallBack() {
            @Override
            public void onSuccess(String jsonString) {
                System.out.println("handlerReceiverPositionMessage : " + jsonString);
            }
        });
    }

    /**
     * 定位成功
     *
     * @param location 百度定位回调的参数
     */
    @Override
    public void onLocationSuccess(AMapLocation location) {

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        RxTimer rxTimer = new RxTimer();
        rxTimer.interval(200, number -> {
            if (mMapView != null) {
                mMapView.onCreate(savedInstanceState);
                rxTimer.cancel();
            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
        mMapView.onResume();
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        RxTimer rxTimer = new RxTimer();
        rxTimer.interval(200, number -> {
            if (mMapView != null) {
                mMapView.onSaveInstanceState(outState);
                rxTimer.cancel();
            }
        });
    }

    @Override
    public void onPause() {
        super.onPause();
        mMapView.onPause();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (LocationService.getInstance() != null) {
            LocationService.getInstance().unregisterLocationListener(this);
        }
        if (mMapView != null) {
            mMapView.onDestroy();
        }
    }
}
