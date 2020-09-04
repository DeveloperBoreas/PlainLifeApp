package com.boreas.plainlife.mvp.views.viewinterfaces;

import com.boreas.plainlife.mvp.ViewInterface;
import com.boreas.plainlife.mvp.models.location.LocationUserModel;
import com.boreas.plainlife.mvp.models.login.CaptchatModel;
import com.boreas.plainlife.mvp.models.login.LoginReceModel;
import com.boreas.plainlife.mvp.models.login.UserRegisterParams;

public interface AddLoveActivityInterface extends ViewInterface {
    /**
     * 数据请求成功回调,根据手机号查询用户返回
     * @param locationUserModel
     */
    void onSuccess(LocationUserModel.Data locationUserModel);

    /**
     * 绑定成功
     */
    void onBindSuccess(String msg);

    /**
     * 数据请求失败回调
     */
    void onFailed(String message);
}
