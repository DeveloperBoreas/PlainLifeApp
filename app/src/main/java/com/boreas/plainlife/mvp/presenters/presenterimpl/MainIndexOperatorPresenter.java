package com.boreas.plainlife.mvp.presenters.presenterimpl;

import com.boreas.plainlife.api.ApiService;
import com.boreas.plainlife.base.BaseRequest;
import com.boreas.plainlife.mvp.presenters.ipresenter.IMainIndexOperatorPresenter;
import com.boreas.plainlife.mvp.views.viewinterfaces.MainIndexOperatorFragmentInterface;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class MainIndexOperatorPresenter extends BaseRequest implements IMainIndexOperatorPresenter {
    private ApiService apiService;
    private MainIndexOperatorFragmentInterface viewInterface;
    private Disposable personalDisposable;

    @Inject
    public MainIndexOperatorPresenter(ApiService apiService, MainIndexOperatorFragmentInterface viewInterface) {
        this.apiService = apiService;
        this.viewInterface = viewInterface;
    }

    /**
     * 请求个人基本信息
     */
    @Override
    public void requestPersonalBase() {
        if (isNetWorkEnable()) {
            viewInterface.onShowLoadingDialog();
            personalDisposable = apiService.personalBase()
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(personalBaseMsgModel -> {
                        if (personalBaseMsgModel.getStatus() == 200) {
                            viewInterface.onPersonalBaseMsgSuccess(personalBaseMsgModel);
                        } else {
                            viewInterface.onFailed(personalBaseMsgModel.getMsg());
                        }
                        this.viewInterface.onDisLoadingDialog();
                    }, throwable -> {
                        viewInterface.onFailed(throwable.getMessage());
                        this.viewInterface.onDisLoadingDialog();
                    });
        } else {
            this.viewInterface.noNetWork();
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
        if (personalDisposable != null && !personalDisposable.isDisposed()) {
            personalDisposable.dispose();
        }
    }
}
