package com.boreas.plainlife.mvp.presenters.presenterimpl;

import android.content.Context;

import com.amap.api.maps.model.LatLng;
import com.boreas.plainlife.App;
import com.boreas.plainlife.api.ApiService;
import com.boreas.plainlife.base.BaseRequest;
import com.boreas.plainlife.mvp.presenters.ipresenter.IAddLoveActivityPresenter;
import com.boreas.plainlife.mvp.presenters.ipresenter.ILocationMapActivityPresenter;
import com.boreas.plainlife.mvp.views.viewinterfaces.AddLoveActivityInterface;
import com.boreas.plainlife.mvp.views.viewinterfaces.LocationMapActivityInterface;
import com.orhanobut.logger.Logger;

import java.util.ArrayList;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class LocationMapActivityPresenter extends BaseRequest implements ILocationMapActivityPresenter {
    private ApiService apiService;
    private LocationMapActivityInterface locationMapActivityInterface;
    private Context context;
    private App app;
    private Disposable subscribe;

    @Inject
    public LocationMapActivityPresenter(ApiService apiService, LocationMapActivityInterface locationMapActivityInterface, Context context, App app) {
        this.apiService = apiService;
        this.locationMapActivityInterface = locationMapActivityInterface;
        this.context = context;
        this.app = app;
    }

    @Override
    public void queryUserLocation(Long uid) {
        if (isNetWorkEnable()) {
            locationMapActivityInterface.onShowLoadingDialog();
            apiService.querySameDayLatlngByUid(uid)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(locationTrackModel -> {
                        locationMapActivityInterface.onDisLoadingDialog();
                        if (locationTrackModel.getCode() == 200) {
                            locationMapActivityInterface.onSuccess(this.handlerTracks(locationTrackModel.getData()));
                            return;
                        }
                        locationMapActivityInterface.onFailed(locationTrackModel.getMsg());
                    }, throwable -> {
                        locationMapActivityInterface.onDisLoadingDialog();
                        Logger.e(throwable.getMessage());
                        locationMapActivityInterface.onFailed("数据异常");
                    });
        } else {
            locationMapActivityInterface.noNetWork();
        }
    }

    private ArrayList<LatLng> handlerTracks(String data) {
        ArrayList<LatLng> tracks = new ArrayList<>();
        String[] split = data.split(";");
        for (String temp : split) {
            try {
                String[] latlng =  temp.split(",");
                tracks.add(new LatLng(Double.parseDouble(latlng[0]),Double.parseDouble(latlng[1])));
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }
        }
        return tracks;
    }

    @Override
    public void onInit() {

    }

    @Override
    public void onResume() {

    }

    @Override
    public void onPause() {

    }

    @Override
    public void onDestory() {
        if (subscribe != null && !subscribe.isDisposed()) {
            subscribe.dispose();
        }
    }
}
