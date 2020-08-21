package com.boreas.plainlife.mvp.views.viewinterfaces;

import com.boreas.plainlife.mvp.ViewInterface;
import com.boreas.plainlife.mvp.models.login.CaptchatModel;
import com.boreas.plainlife.mvp.models.login.LoginReceModel;

public interface LoginActivityInterface extends ViewInterface {
    /**
     * 数据请求成功回调
     * @param looginReceModel
     */
    void onSuccess(LoginReceModel looginReceModel);

    /**
     * 验证码图片成功回调
     */
    void onCaptchatSuccess(CaptchatModel captchatModel);
    /**
     * 数据请求失败回调
     */
    void onFailed(String message);
}
