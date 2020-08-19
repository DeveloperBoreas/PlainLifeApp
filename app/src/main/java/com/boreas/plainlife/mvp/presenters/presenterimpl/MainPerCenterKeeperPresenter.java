package com.boreas.plainlife.mvp.presenters.presenterimpl;

import com.boreas.plainlife.api.ApiService;
import com.boreas.plainlife.base.BaseRequest;
import com.boreas.plainlife.mvp.presenters.ipresenter.IMainPerCenterKeeperPresenter;
import com.boreas.plainlife.mvp.views.viewinterfaces.MainPerCenterKeeperFragmentInterface;

import javax.inject.Inject;

public class MainPerCenterKeeperPresenter extends BaseRequest implements IMainPerCenterKeeperPresenter {
    private ApiService apiService;
    private MainPerCenterKeeperFragmentInterface mainPerCenterKeeperFragmentInterface;

    @Inject
    public MainPerCenterKeeperPresenter(ApiService apiService, MainPerCenterKeeperFragmentInterface mainPerCenterKeeperFragmentInterface) {
        this.apiService = apiService;
        this.mainPerCenterKeeperFragmentInterface = mainPerCenterKeeperFragmentInterface;
    }

    /**
     * 请求个人基本信息
     */
    @Override
    public void requestPersonalBase() {

    }

    /**
     * 请求常用设备信息
     */
    @Override
    public void requestPersonalDevices() {

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

    }
}
