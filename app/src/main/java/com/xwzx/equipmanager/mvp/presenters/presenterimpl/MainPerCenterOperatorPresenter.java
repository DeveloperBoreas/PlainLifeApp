package com.xwzx.equipmanager.mvp.presenters.presenterimpl;

import com.orhanobut.logger.Logger;
import com.xwzx.equipmanager.api.ApiService;
import com.xwzx.equipmanager.base.BaseRequest;
import com.xwzx.equipmanager.mvp.presenters.ipresenter.IMainPerCenterKeeperPresenter;
import com.xwzx.equipmanager.mvp.views.fragments.MainPerCenterOperatorFragment;
import com.xwzx.equipmanager.mvp.views.viewinterfaces.MainPerCenterKeeperFragmentInterface;
import com.xwzx.equipmanager.mvp.views.viewinterfaces.MainPerCenterOperatorFragmentInterface;

import java.util.concurrent.TimeoutException;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class MainPerCenterOperatorPresenter extends BaseRequest implements IMainPerCenterKeeperPresenter {
    private ApiService apiService;
    private MainPerCenterOperatorFragmentInterface mainPerCenterOperatorFragmentInterface;
    private Disposable personalDisposable;
    private Disposable personalDevicesDisposable;

    @Inject
    public MainPerCenterOperatorPresenter(ApiService apiService, MainPerCenterOperatorFragmentInterface mainPerCenterOperatorFragmentInterface) {
        this.apiService = apiService;
        this.mainPerCenterOperatorFragmentInterface = mainPerCenterOperatorFragmentInterface;
    }

    /**
     * 请求个人基本信息
     */
    @Override
    public void requestPersonalBase() {
        if (isNetWorkEnable()) {
            mainPerCenterOperatorFragmentInterface.onShowLoadingDialog();
            personalDisposable = apiService.personalBase()
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(personalBaseMsgModel -> {
                        if (personalBaseMsgModel.getStatus() == 200) {
                            mainPerCenterOperatorFragmentInterface.onPersonalBaseMsgSuccess(personalBaseMsgModel);
                        } else {
                            mainPerCenterOperatorFragmentInterface.onFailed(personalBaseMsgModel.getMsg());
                        }
                        this.mainPerCenterOperatorFragmentInterface.onDisLoadingDialog();
                    }, throwable -> {
                        this.mainPerCenterOperatorFragmentInterface.onDisLoadingDialog();
                        if (throwable instanceof TimeoutException) {
                            this.mainPerCenterOperatorFragmentInterface.onFailed("请求超时");
                            return;
                        }
                        this.mainPerCenterOperatorFragmentInterface.onFailed(throwable.getMessage());
                    });
        } else {
            this.mainPerCenterOperatorFragmentInterface.noNetWork();
        }
    }

    /**
     * 请求常用设备信息
     */
    @Override
    public void requestPersonalDevices() {
        if (isNetWorkEnable()) {
            mainPerCenterOperatorFragmentInterface.onShowLoadingDialog();
            personalDevicesDisposable = apiService.personalDevices()
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(keeperUsedDevicesModel -> {
                        if (keeperUsedDevicesModel.getStatus() == 200) {
                            mainPerCenterOperatorFragmentInterface.onPersonalDevices(keeperUsedDevicesModel);
                        } else {
                            mainPerCenterOperatorFragmentInterface.onFailed(keeperUsedDevicesModel.getMsg());
                        }
                        this.mainPerCenterOperatorFragmentInterface.onDisLoadingDialog();
                    }, throwable -> {
                        Logger.e(throwable.getMessage());
                        this.mainPerCenterOperatorFragmentInterface.onDisLoadingDialog();
                        if (throwable instanceof TimeoutException) {
                            this.mainPerCenterOperatorFragmentInterface.onFailed("请求超时");
                            return;
                        }
                        this.mainPerCenterOperatorFragmentInterface.onFailed(throwable.getMessage());

                    });
        } else {
            this.mainPerCenterOperatorFragmentInterface.noNetWork();
        }
    }

    /**
     * 请求全部接口数据
     */
    @Override
    public void requestAll() {

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
        if (personalDisposable != null && !personalDisposable.isDisposed()) {
            personalDisposable.dispose();
        }
        if (personalDevicesDisposable != null && !personalDevicesDisposable.isDisposed()) {
            personalDevicesDisposable.dispose();
        }
    }
}
