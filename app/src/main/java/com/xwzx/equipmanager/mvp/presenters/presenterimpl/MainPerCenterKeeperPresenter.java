package com.xwzx.equipmanager.mvp.presenters.presenterimpl;

import com.xwzx.equipmanager.api.ApiService;
import com.xwzx.equipmanager.base.BaseRequest;
import com.xwzx.equipmanager.mvp.presenters.ipresenter.IMainPerCenterKeeperPresenter;
import com.xwzx.equipmanager.mvp.views.viewinterfaces.MainPerCenterKeeperFragmentInterface;

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
