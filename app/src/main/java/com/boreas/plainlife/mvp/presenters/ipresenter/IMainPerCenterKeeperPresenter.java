package com.boreas.plainlife.mvp.presenters.ipresenter;

import com.boreas.plainlife.mvp.Presenter;

public interface IMainPerCenterKeeperPresenter extends Presenter {
    /**
     * 请求个人基本信息
     */
    void requestPersonalBase();
    /**
     * 请求常用设备信息
     */
    void requestPersonalDevices();

    /**
     * 请求全部接口数据
     */
    void requestAll();
}
