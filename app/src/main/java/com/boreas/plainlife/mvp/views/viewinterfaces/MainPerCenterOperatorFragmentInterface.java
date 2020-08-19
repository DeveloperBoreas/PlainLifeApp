package com.boreas.plainlife.mvp.views.viewinterfaces;

import com.boreas.plainlife.mvp.ViewInterface;
import com.boreas.plainlife.mvp.models.precenter.KeeperUsedDevicesModel;
import com.boreas.plainlife.mvp.models.precenter.PersonalBaseMsgModel;

public interface MainPerCenterOperatorFragmentInterface extends ViewInterface {
    /**
     * 请求个人基本信息成功回调
     */
    void onPersonalBaseMsgSuccess(PersonalBaseMsgModel personalBaseMsgModel);

    /**
     * 请求常用装备信息成功回调
     */
    void onPersonalDevices(KeeperUsedDevicesModel keeperUsedDevicesModel);

    /**
     * 请求数据失败回调
     */
    void onFailed(String message);
}
