package com.boreas.plainlife.mvp.presenters.presenterimpl;

import android.content.Context;

import com.orhanobut.logger.Logger;
import com.boreas.plainlife.App;
import com.boreas.plainlife.Constant;
import com.boreas.plainlife.api.ApiService;
import com.boreas.plainlife.base.BaseRequest;
import com.boreas.plainlife.mvp.models.login.AccessModel;
import com.boreas.plainlife.mvp.models.login.LoginModel;
import com.boreas.plainlife.mvp.models.login.LoginReceModel;
import com.boreas.plainlife.mvp.presenters.ipresenter.ILoginActivityPresenter;
import com.boreas.plainlife.mvp.views.viewinterfaces.LoginActivityInterface;
import com.boreas.plainlife.utils.PreUtil;

import javax.inject.Inject;

import io.reactivex.ObservableSource;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Function;
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
    public void requestData(String userName, String password) {
        if(true){
            loginActivityInterface.onAccessSuccess(null);
            return;
        }
        if (isNetWorkEnable()) {
            loginActivityInterface.onShowLoadingDialog();
            loginSubscribe = apiService.login(new LoginModel(userName,password))
                    .subscribeOn(Schedulers.io())
                    .map(loginReceModel -> {
                        PreUtil.put(Constant.TOKEN_KEY,loginReceModel.getToken_type() + " " + loginReceModel.getAccess_token());
                        loginActivityInterface.onSuccess(loginReceModel);
                        return loginReceModel;
                    })
                    .flatMap((Function<LoginReceModel, ObservableSource<AccessModel>>) loginReceModel -> apiService.findMyAccess())
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(accessModel -> {
                        if(accessModel.getStatus() == 200){
                            loginActivityInterface.onAccessSuccess(accessModel);
                        }else{
                            loginActivityInterface.onFailed(accessModel.getMsg());
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
