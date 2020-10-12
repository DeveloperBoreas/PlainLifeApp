package com.boreas.plainlife.mvp.presenters.presenterimpl;


import android.content.Context;

import com.boreas.plainlife.App;
import com.boreas.plainlife.api.ApiService;
import com.boreas.plainlife.base.BaseRequest;
import com.boreas.plainlife.mvp.models.login.LoginParams;
import com.boreas.plainlife.mvp.models.login.UserRegisterParams;
import com.boreas.plainlife.mvp.presenters.ipresenter.ILoginActivityPresenter;
import com.boreas.plainlife.mvp.views.viewinterfaces.LoginActivityInterface;
import com.orhanobut.logger.Logger;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class LoginActivityPresenter extends BaseRequest implements ILoginActivityPresenter {

    private ApiService apiService;
    private LoginActivityInterface loginActivityInterface;
    private Context context;
    private App app;
    private Disposable loginSubscribe;

    @Inject
    public LoginActivityPresenter(ApiService apiService, LoginActivityInterface loginActivityInterface, Context context, App app) {
        this.apiService = apiService;
        this.loginActivityInterface = loginActivityInterface;
        this.context = context;
        this.app = app;
    }

    @Override
    public void requestLogin(String userName, String password, String verCode, String uuid) {
        if (isNetWorkEnable()) {
            loginActivityInterface.onShowLoadingDialog();
            loginSubscribe = apiService.login(ApiService.User_Agent, new LoginParams(userName, password, verCode, uuid))
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(looginReceModel -> {
                        loginActivityInterface.onDisLoadingDialog();
                        if (looginReceModel.getCode() == 200) {
                            loginActivityInterface.onSuccess(looginReceModel);
                        } else {
                            loginActivityInterface.onFailed(looginReceModel.getMsg());
                        }
                    }, throwable -> {
                        Logger.e(throwable.getMessage());
                        loginActivityInterface.onFailed(throwable.getMessage());
                        loginActivityInterface.onDisLoadingDialog();
                    });
            return;
        }
        loginActivityInterface.noNetWork();
    }

    @Override
    public void requesrRegister(UserRegisterParams userRegisterParams) {
        if (isNetWorkEnable()) {
            loginActivityInterface.onShowLoadingDialog();
            loginSubscribe = apiService.register(userRegisterParams)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(baseResponse -> {
                        loginActivityInterface.onDisLoadingDialog();
                        if (baseResponse.getCode() == 200) {
                            loginActivityInterface.onRegisterSuccess(userRegisterParams);
                            return;
                        }
                        loginActivityInterface.onFailed(baseResponse.getMsg());
                    }, throwable -> {
                        Logger.e(throwable.getMessage());
                        loginActivityInterface.onFailed(throwable.getMessage());
                        loginActivityInterface.onDisLoadingDialog();
                    });
            return;
        }
        loginActivityInterface.noNetWork();
    }

    @Override
    public void sendSms(String phone) {
        if (isNetWorkEnable()) {
            loginActivityInterface.onShowLoadingDialog();
            loginSubscribe = apiService.sendSms(phone)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(baseResponse -> {
                        loginActivityInterface.onDisLoadingDialog();
                        if (baseResponse.getCode() == 200) {
                            loginActivityInterface.onSendSMSSuccess();
                            return;
                        }
                        loginActivityInterface.onFailed(baseResponse.getMsg());
                    }, throwable -> {
                        Logger.e(throwable.getMessage());
                        loginActivityInterface.onFailed(throwable.getMessage());
                        loginActivityInterface.onDisLoadingDialog();
                    });
            return;
        }
        loginActivityInterface.noNetWork();
    }

    @Override
    public void requestCaptchatImg() {
        if (isNetWorkEnable()) {
            loginActivityInterface.onShowLoadingDialog();
            loginSubscribe = apiService.getCaptchaImage()
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(captchatModel -> {
                        if (captchatModel.getCode() == 200) {
                            loginActivityInterface.onCaptchatSuccess(captchatModel);
                        } else {
                            loginActivityInterface.onFailed(captchatModel.getMsg());
                        }
                        loginActivityInterface.onDisLoadingDialog();
                    }, throwable -> {
                        Logger.e(throwable.getMessage());
                        loginActivityInterface.onFailed(throwable.getMessage());
                        loginActivityInterface.onDisLoadingDialog();
                    });
            return;
        }
        loginActivityInterface.noNetWork();
    }

    @Override
    public void onInit() {

    }

    @Override
    public void onResume() {

    }

    @Override
    public void onPause() {

    }

    @Override
    public void onDestory() {
        if (loginSubscribe != null && !loginSubscribe.isDisposed()) {
            loginSubscribe.dispose();
        }
    }

    public ApiService getApiService() {
        return this.apiService;
    }
}
