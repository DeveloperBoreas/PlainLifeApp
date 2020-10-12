package com.boreas.plainlife.mvp.presenters.presenterimpl;


import com.boreas.plainlife.ObjectPool;
import com.boreas.plainlife.api.ApiService;
import com.boreas.plainlife.base.BaseRequest;
import com.boreas.plainlife.mvp.models.location.LocationUserListModel;
import com.boreas.plainlife.mvp.presenters.ipresenter.ILocationLoveFragmentPresenter;
import com.boreas.plainlife.mvp.views.viewinterfaces.LocationLoveFragmentInterface;
import com.orhanobut.logger.Logger;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class LocationLoveFragmentPresenter extends BaseRequest implements ILocationLoveFragmentPresenter {
    private ApiService apiService;
    private LocationLoveFragmentInterface locationLoveFragmentInterface;
    private Disposable subscribe;
    private ObjectPool objectPool;
    @Inject
    public LocationLoveFragmentPresenter(ApiService apiService, LocationLoveFragmentInterface locationLoveFragmentInterface, ObjectPool objectPool) {
        this.apiService = apiService;
        this.locationLoveFragmentInterface = locationLoveFragmentInterface;
        this.objectPool = objectPool;
    }

    @Override
    public void queryBindUsers() {
        if (isNetWorkEnable()) {
            locationLoveFragmentInterface.onShowLoadingDialog();
            subscribe = apiService.queryBindUsers()
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(locationUserListModel -> {
                        locationLoveFragmentInterface.onDisLoadingDialog();
                        if(locationUserListModel.getCode() == 200){
                            locationLoveFragmentInterface.onQueryBindUserSuccess(this.handlerListData(locationUserListModel.getData()));
                            return;
                        }
                        locationLoveFragmentInterface.onFailed(locationUserListModel.getMsg());
                    }, throwable -> {
                        locationLoveFragmentInterface.onDisLoadingDialog();
                        Logger.e(throwable.getMessage());
                        locationLoveFragmentInterface.onFailed("数据异常");
                    });
        }else {
            locationLoveFragmentInterface.noNetWork();
        }
    }

    private ArrayList<LocationUserListModel.Data> handlerListData(ArrayList<LocationUserListModel.Data> data){
        for (LocationUserListModel.Data tempData: data) {
            if(tempData.getUserId() == this.objectPool.getUserInfo().getUser().getUserId()){
                tempData.setNickName("自己");
                break;
            }
        }
        return data;
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

    public Long getUserid(){
        return this.objectPool.getUserInfo().getUser().getUserId();
    }
}
