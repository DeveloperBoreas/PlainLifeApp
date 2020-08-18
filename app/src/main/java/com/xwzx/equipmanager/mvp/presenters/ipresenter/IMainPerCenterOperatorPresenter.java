package com.xwzx.equipmanager.mvp.presenters.ipresenter;

import com.xwzx.equipmanager.mvp.Presenter;

public interface IMainPerCenterOperatorPresenter extends Presenter {
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
