package com.boreas.plainlife.mvp.views.viewinterfaces;

import com.boreas.plainlife.mvp.ViewInterface;
import com.boreas.plainlife.mvp.models.login.AccessModel;
import com.boreas.plainlife.mvp.models.login.LoginReceModel;

public interface LoginActivityInterface extends ViewInterface {
    /**
     * 数据请求成功回调
     * @param s
     */
    void onSuccess(LoginReceModel s);

    /**
     * 查询权限成功回调
     */
    void onAccessSuccess(AccessModel accessModel);
    /**
     * 数据请求失败回调
     */
    void onFailed(String message);
}
