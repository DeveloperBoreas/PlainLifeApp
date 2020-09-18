package com.boreas.plainlife.mvp.views.viewinterfaces;

import com.boreas.plainlife.mvp.ViewInterface;

public interface MainActivityInterface extends ViewInterface {

    /**
     * 数据请求失败回调
     */
    void onFailed(String msg);
}
