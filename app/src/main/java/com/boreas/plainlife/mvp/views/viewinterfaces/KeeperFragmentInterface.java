package com.boreas.plainlife.mvp.views.viewinterfaces;

import com.boreas.plainlife.mvp.ViewInterface;

public interface KeeperFragmentInterface extends ViewInterface {
    /**
     * 请求数据成功回调
     */
    void onSuccess();

    /**
     * 请求数据失败回调
     */
    void onFailed();
}
