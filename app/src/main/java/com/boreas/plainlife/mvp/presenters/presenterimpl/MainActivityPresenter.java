package com.boreas.plainlife.mvp.presenters.presenterimpl;

import android.content.Context;

import com.boreas.plainlife.App;
import com.boreas.plainlife.api.ApiService;
import com.boreas.plainlife.base.BaseRequest;
import com.boreas.plainlife.mvp.presenters.ipresenter.IMainActivityPresenter;
import com.boreas.plainlife.mvp.views.viewinterfaces.MainActivityInterface;

import javax.inject.Inject;

import io.reactivex.disposables.Disposable;

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
