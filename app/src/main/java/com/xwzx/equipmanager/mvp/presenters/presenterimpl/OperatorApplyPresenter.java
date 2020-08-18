package com.xwzx.equipmanager.mvp.presenters.presenterimpl;

import com.orhanobut.logger.Logger;
import com.xwzx.equipmanager.api.ApiService;
import com.xwzx.equipmanager.base.BaseRequest;
import com.xwzx.equipmanager.mvp.models.ApplyRequestParam;
import com.xwzx.equipmanager.mvp.models.indexoperator.TaskModel;
import com.xwzx.equipmanager.mvp.presenters.ipresenter.IOperatorApplyPresenter;
import com.xwzx.equipmanager.mvp.presenters.ipresenter.IOperatorTaskPresenter;
import com.xwzx.equipmanager.mvp.views.viewinterfaces.OperatorApplyActivityInterface;
import com.xwzx.equipmanager.mvp.views.viewinterfaces.OperatorTaskActivityInterface;

import java.util.ArrayList;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class OperatorApplyPresenter extends BaseRequest implements IOperatorApplyPresenter {
    private ApiService apiService;
    private OperatorApplyActivityInterface viewInterface;
    private Disposable subscribe;

    @Inject
    public OperatorApplyPresenter(ApiService apiService, OperatorApplyActivityInterface viewInterface) {
        this.apiService = apiService;
        this.viewInterface = viewInterface;
    }

    /**
     * 请求个人基本信息
     */
    @Override
    public void requestOperatorApply() {
        if (isNetWorkEnable()) {
            viewInterface.onShowLoadingDialog();
            subscribe = apiService.findMyApplication()
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(data -> {
                        this.viewInterface.onDisLoadingDialog();
                        if (data.getStatus() == 200) {
                            if(data.getData() != null){
                                //已完成
                                if(data.getData().getAgree() != null && !data.getData().getAgree().isEmpty()){
                                    this.viewInterface.onAgreeSuccess(data.getData().getAgree());
                                }
                                //申请中
                                if(data.getData().getApply() != null && !data.getData().getApply().isEmpty()){
                                    this.viewInterface.onApplySuccess(data.getData().getApply());
                                }
                                //已驳回
                                if(data.getData().getRehecrt() != null && !data.getData().getRehecrt().isEmpty()){
                                    this.viewInterface.onRehecrtSuccess(data.getData().getRehecrt());
                                }
                            }else{
                                this.viewInterface.onFailed("返回数据异常!");
                            }
                        }else{
                            this.viewInterface.onFailed(data.getMsg());
                        }
                    }, throwable -> {
                        Logger.e(throwable.getMessage());
                        viewInterface.onFailed(throwable.getMessage());
                        viewInterface.onDisLoadingDialog();
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
        if (subscribe != null && !subscribe.isDisposed()) {
            subscribe.dispose();
        }
    }
}
