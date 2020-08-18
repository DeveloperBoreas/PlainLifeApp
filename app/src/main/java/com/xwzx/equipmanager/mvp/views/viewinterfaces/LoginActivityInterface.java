package com.xwzx.equipmanager.mvp.views.viewinterfaces;

import com.xwzx.equipmanager.base.BaseResponse;
import com.xwzx.equipmanager.mvp.ViewInterface;
import com.xwzx.equipmanager.mvp.models.login.AccessModel;
import com.xwzx.equipmanager.mvp.models.login.LoginReceModel;

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
