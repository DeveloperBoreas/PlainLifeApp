package com.boreas.plainlife.mvp.presenters.presenterimpl;

import com.orhanobut.logger.Logger;
import com.boreas.plainlife.api.ApiService;
import com.boreas.plainlife.base.BaseRequest;
import com.boreas.plainlife.mvp.models.precenter.OperatorAllRecordModel;
import com.boreas.plainlife.mvp.presenters.ipresenter.IOperatorRecordPresenter;
import com.boreas.plainlife.mvp.views.viewinterfaces.OperatorRecordActivityInterface;
import com.boreas.plainlife.utils.ListUtil;

import java.util.ArrayList;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class OperatorRecordPresenter extends BaseRequest implements IOperatorRecordPresenter {
    private ApiService apiService;
    private OperatorRecordActivityInterface viewInterface;
    private Disposable subscribe;

    @Inject
    public OperatorRecordPresenter(ApiService apiService, OperatorRecordActivityInterface viewInterface) {
        this.apiService = apiService;
        this.viewInterface = viewInterface;
    }

    @Override
    public void requestOperatorRecord() {
        if (isNetWorkEnable()) {
            viewInterface.onShowLoadingDialog();
            subscribe = apiService.findAllRecord()
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(data -> {
                        this.viewInterface.onDisLoadingDialog();
                        if (data.getStatus() == 200) {
                            if (data.getData() != null) {
                                ArrayList<OperatorAllRecordModel.DataBeanXXX.InnerBean> allRecord = new ArrayList<>();
                                allRecord.addAll(data.getData().getTake());
                                allRecord.addAll(data.getData().getBack());
                                allRecord.addAll(data.getData().getOverdue());
                                allRecord.addAll(data.getData().getReturned());
                                viewInterface.onAllRecordSuccess(allRecord);
                                if (!ListUtil.isNull(data.getData().getTake())) {
                                    viewInterface.onTakeRecordSuccess(data.getData().getTake());
                                }
                                if (!ListUtil.isNull(data.getData().getBack())) {
                                    ArrayList<OperatorAllRecordModel.DataBeanXXX.InnerBean> backList = new ArrayList<>();
                                    backList.addAll(data.getData().getBack());
                                    backList.addAll(data.getData().getOverdue());
                                    viewInterface.onBackRecordSuccess(backList);
                                }
                                if(!ListUtil.isNull(data.getData().getOverdue())){
                                    viewInterface.onBackedRecordSuccess(data.getData().getOverdue());
                                }
                                if (!ListUtil.isNull(data.getData().getReturned())) {
                                    viewInterface.onReturnedRecordSuccess(data.getData().getReturned());
                                }
                                viewInterface.onRecordSuccess();
                            } else {
                                this.viewInterface.onFailed("返回数据异常!");
                            }
                        } else {
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
