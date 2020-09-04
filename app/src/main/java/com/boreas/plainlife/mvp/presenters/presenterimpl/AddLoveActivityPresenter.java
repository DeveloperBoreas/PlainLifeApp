package com.boreas.plainlife.mvp.presenters.presenterimpl;

import android.content.Context;

import com.boreas.plainlife.App;
import com.boreas.plainlife.api.ApiService;
import com.boreas.plainlife.base.BaseRequest;
import com.boreas.plainlife.mvp.presenters.ipresenter.IAddLoveActivityPresenter;
import com.boreas.plainlife.mvp.presenters.ipresenter.IMainActivityPresenter;
import com.boreas.plainlife.mvp.views.viewinterfaces.AddLoveActivityInterface;
import com.boreas.plainlife.mvp.views.viewinterfaces.MainActivityInterface;
import com.orhanobut.logger.Logger;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class AddLoveActivityPresenter extends BaseRequest implements IAddLoveActivityPresenter {
    private ApiService apiService;
    private AddLoveActivityInterface addLoveActivityInterface;
    private Context context;
    private App app;
    private Disposable testSubscribe;

    @Inject
    public AddLoveActivityPresenter(ApiService apiService, AddLoveActivityInterface addLoveActivityInterface, Context context, App app) {
        this.apiService = apiService;
        this.addLoveActivityInterface = addLoveActivityInterface;
        this.context = context;
        this.app = app;
    }

    @Override
    public void queryUserByPhone(String phone) {
        if (isNetWorkEnable()) {
            addLoveActivityInterface.onShowLoadingDialog();
            apiService.queryUserByPhone(phone)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(locationUserModel->{
                        addLoveActivityInterface.onDisLoadingDialog();
                        Logger.e(locationUserModel.toString());
                        if(locationUserModel.getCode() == 200){
                            addLoveActivityInterface.onSuccess(locationUserModel.getData());
                            return;
                        }
                        addLoveActivityInterface.onFailed(locationUserModel.getMsg());
                    },throwable -> {
                        addLoveActivityInterface.onDisLoadingDialog();
                        Logger.e(throwable.getMessage());
                        addLoveActivityInterface.onFailed("数据异常");
                    });
        }else {
            addLoveActivityInterface.noNetWork();
        }
    }

    @Override
    public void bindUserByPhone(String phone) {
        if (isNetWorkEnable()) {
            addLoveActivityInterface.onShowLoadingDialog();
            apiService.bindUserForPhone(phone)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(baseResponse->{
                        addLoveActivityInterface.onDisLoadingDialog();
                        if(baseResponse.getCode() == 200){
                            addLoveActivityInterface.onBindSuccess(baseResponse.getMsg());
                            return;
                        }
                        addLoveActivityInterface.onFailed(baseResponse.getMsg());
                    },throwable -> {
                        Logger.e(throwable.getMessage());
                        addLoveActivityInterface.onDisLoadingDialog();
                        addLoveActivityInterface.onFailed("数据异常");
                    });
        }else {
            addLoveActivityInterface.noNetWork();
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
