package com.boreas.plainlife.mvp.presenters.ipresenter;

import com.boreas.plainlife.mvp.Presenter;

public interface ILoginActivityPresenter extends Presenter {
    /**
     * 请求验证码图片
     */
    void requestCaptchatImg();
    void requestLogin(String userName, String password,String verCode,String uuid);
}
