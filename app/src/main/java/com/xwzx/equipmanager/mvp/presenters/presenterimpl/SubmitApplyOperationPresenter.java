package com.xwzx.equipmanager.mvp.presenters.presenterimpl;

import com.orhanobut.logger.Logger;
import com.xwzx.equipmanager.api.ApiService;
import com.xwzx.equipmanager.base.BaseRequest;
import com.xwzx.equipmanager.base.BaseResponse;
import com.xwzx.equipmanager.mvp.models.submit.FindCaseByUserModel;
import com.xwzx.equipmanager.mvp.models.submit.SubmitApprovalModel;
import com.xwzx.equipmanager.mvp.presenters.ipresenter.ISubmitApplyOperationActivityPresenter;
import com.xwzx.equipmanager.mvp.views.viewinterfaces.SubmitApplyOperationActivityInterface;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

public class SubmitApplyOperationPresenter extends BaseRequest implements ISubmitApplyOperationActivityPresenter {
    private ApiService apiService;
    private SubmitApplyOperationActivityInterface submitApplyOperationActivityInterface;
    private Disposable findDisposable;
    private Disposable submitDisposable;

    @Inject
    public SubmitApplyOperationPresenter(ApiService apiService, SubmitApplyOperationActivityInterface submitApplyOperationActivityInterface) {
        this.apiService = apiService;
        this.submitApplyOperationActivityInterface = submitApplyOperationActivityInterface;
    }

    @Override
    public void findCaseAndTaskByUser() {
        if (isNetWorkEnable()) {
            submitApplyOperationActivityInterface.onShowLoadingDialog();
            findDisposable = apiService.findCaseTaskByUser()
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(findCaseByUserModel -> {
                        if (200 == findCaseByUserModel.getStatus()) {
                            List<FindCaseByUserModel.CaseItemModel> data = findCaseByUserModel.getData();
                            if (null != data && data.size() > 0) {
                                submitApplyOperationActivityInterface.onGetCaseAndTask(data);
                                submitApplyOperationActivityInterface.onDisLoadingDialog();
                            } else {
                                submitApplyOperationActivityInterface.onFailed("当前没有数据");
                                submitApplyOperationActivityInterface.onDisLoadingDialog();
                            }

                        } else {
                            submitApplyOperationActivityInterface.onFailed(findCaseByUserModel.getMsg());
                            submitApplyOperationActivityInterface.onDisLoadingDialog();
                        }

                    }, throwable -> {
                        Logger.e(throwable.getMessage());
                        submitApplyOperationActivityInterface.onFailed("请求失败");
                        submitApplyOperationActivityInterface.onDisLoadingDialog();
                    });
            return;
        }
        submitApplyOperationActivityInterface.noNetWork();
    }

    @Override
    public void submitApply(SubmitApprovalModel submitApprovalModel) {
        if (isNetWorkEnable()) {
            submitApplyOperationActivityInterface.onShowLoadingDialog();
            submitDisposable = apiService.submitApproval(submitApprovalModel)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(s -> {
                        if (s.getStatus() == 200) {
                            submitApplyOperationActivityInterface.onSubmitApply(s.getMsg());
                            submitApplyOperationActivityInterface.onDisLoadingDialog();
                        } else {
                            submitApplyOperationActivityInterface.onFailed(s.getMsg());
                            submitApplyOperationActivityInterface.onDisLoadingDialog();
                        }
                    }, throwable -> {
                        Logger.e(throwable.getMessage());
                        submitApplyOperationActivityInterface.onFailed("请求失败");
                        submitApplyOperationActivityInterface.onDisLoadingDialog();
                    });
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
        if (findDisposable != null && !findDisposable.isDisposed()) {
            findDisposable.dispose();
        }
        if (submitDisposable != null && !submitDisposable.isDisposed()) {
            submitDisposable.dispose();
        }
    }
}
