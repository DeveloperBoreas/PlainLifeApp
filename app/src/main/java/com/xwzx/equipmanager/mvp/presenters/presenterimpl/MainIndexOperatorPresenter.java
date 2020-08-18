package com.xwzx.equipmanager.mvp.presenters.presenterimpl;

import com.xwzx.equipmanager.api.ApiService;
import com.xwzx.equipmanager.base.BaseRequest;
import com.xwzx.equipmanager.mvp.presenters.ipresenter.IMainIndexOperatorPresenter;
import com.xwzx.equipmanager.mvp.presenters.ipresenter.IMainPerCenterKeeperPresenter;
import com.xwzx.equipmanager.mvp.views.viewinterfaces.MainIndexOperatorFragmentInterface;
import com.xwzx.equipmanager.mvp.views.viewinterfaces.MainPerCenterOperatorFragmentInterface;

import java.util.concurrent.TimeoutException;

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
