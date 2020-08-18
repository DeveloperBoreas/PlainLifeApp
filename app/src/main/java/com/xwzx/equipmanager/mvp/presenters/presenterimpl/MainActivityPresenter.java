package com.xwzx.equipmanager.mvp.presenters.presenterimpl;

import android.content.Context;

import com.orhanobut.logger.Logger;
import com.xwzx.equipmanager.App;
import com.xwzx.equipmanager.api.ApiService;
import com.xwzx.equipmanager.base.BaseRequest;
import com.xwzx.equipmanager.mvp.presenters.ipresenter.IMainActivityPresenter;
import com.xwzx.equipmanager.mvp.views.viewinterfaces.MainActivityInterface;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class MainActivityPresenter extends BaseRequest implements IMainActivityPresenter {
    private ApiService apiService;
    private MainActivityInterface mainActivityInterface;
    private Context context;
    private App app;
    private Disposable testSubscribe;

    @Inject
    public MainActivityPresenter(ApiService apiService, MainActivityInterface mainActivityInterface, Context context, App app) {
        this.apiService = apiService;
        this.mainActivityInterface = mainActivityInterface;
        this.context = context;
        this.app = app;
    }

    @Override
    public void requestData() {
        if (isNetWorkEnable()) {

        }else {
            mainActivityInterface.noNetWork();
        }
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
        if (testSubscribe != null && !testSubscribe.isDisposed()) {
            testSubscribe.dispose();
        }
    }
}
