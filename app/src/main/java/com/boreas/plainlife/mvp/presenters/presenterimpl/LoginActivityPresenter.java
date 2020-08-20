package com.boreas.plainlife.mvp.presenters.presenterimpl;

import android.content.Context;
import android.util.Log;

import com.boreas.plainlife.mvp.models.login.LoginParams;
import com.orhanobut.logger.Logger;
import com.boreas.plainlife.App;
import com.boreas.plainlife.Constant;
import com.boreas.plainlife.api.ApiService;
import com.boreas.plainlife.base.BaseRequest;
import com.boreas.plainlife.mvp.models.login.LoginModel;
import com.boreas.plainlife.mvp.presenters.ipresenter.ILoginActivityPresenter;
import com.boreas.plainlife.mvp.views.viewinterfaces.LoginActivityInterface;
import com.boreas.plainlife.utils.PreUtil;

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
    public void requestLogin(String userName, String password,String verCode,String uuid) {
        if (isNetWorkEnable()) {
            loginActivityInterface.onShowLoadingDialog();
            loginSubscribe = apiService.login(ApiService.User_Agent,new LoginParams(userName,password,verCode,uuid))
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(s -> {
                        Logger.e(s);
//                        if(accessModel.get() == 200){
////                            loginActivityInterface.onAccessSuccess(accessModel);
//                        }else{
//                            loginActivityInterface.onFailed(accessModel.getMsg());
//                        }
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
    public void requestCaptchatImg() {
        if (isNetWorkEnable()) {
            loginActivityInterface.onShowLoadingDialog();
            loginSubscribe = apiService.getCaptchaImage()
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(captchatModel -> {
                        Logger.e(captchatModel.toString());
                        if(captchatModel.getCode() == 200){
                            loginActivityInterface.onCaptchatSuccess(captchatModel);
                        }else{
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


}
