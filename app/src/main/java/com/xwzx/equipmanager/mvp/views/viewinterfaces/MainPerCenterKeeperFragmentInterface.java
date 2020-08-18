package com.xwzx.equipmanager.mvp.views.viewinterfaces;

import com.xwzx.equipmanager.mvp.ViewInterface;

public interface MainPerCenterKeeperFragmentInterface extends ViewInterface {
    /**
     * 请求数据成功回调
     */
    void onSuccess();

    /**
     * 请求数据失败回调
     */
    void onFailed();
}
