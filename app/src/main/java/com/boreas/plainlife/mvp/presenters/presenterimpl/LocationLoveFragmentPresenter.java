package com.boreas.plainlife.mvp.presenters.presenterimpl;


import com.boreas.plainlife.api.ApiService;
import com.boreas.plainlife.base.BaseRequest;
import com.boreas.plainlife.mvp.presenters.ipresenter.ILocationLoveFragmentPresenter;
import com.boreas.plainlife.mvp.views.viewinterfaces.LocationLoveFragmentInterface;
import com.orhanobut.logger.Logger;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class LocationLoveFragmentPresenter extends BaseRequest implements ILocationLoveFragmentPresenter {
    private ApiService apiService;
    private LocationLoveFragmentInterface locationLoveFragmentInterface;
    private Disposable subscribe;

    @Inject
    public LocationLoveFragmentPresenter(ApiService apiService, LocationLoveFragmentInterface locationLoveFragmentInterface) {
        this.apiService = apiService;
        this.locationLoveFragmentInterface = locationLoveFragmentInterface;
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
                            locationLoveFragmentInterface.onQueryBindUserSuccess(locationUserListModel.getData());
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
