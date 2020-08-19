package com.boreas.plainlife.mvp.presenters.presenterimpl;

import com.orhanobut.logger.Logger;
import com.boreas.plainlife.api.ApiService;
import com.boreas.plainlife.base.BaseRequest;
import com.boreas.plainlife.mvp.models.indexoperator.TaskModel;
import com.boreas.plainlife.mvp.presenters.ipresenter.IOperatorTaskPresenter;
import com.boreas.plainlife.mvp.views.viewinterfaces.OperatorTaskActivityInterface;

import java.util.ArrayList;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class OperatorTaskPresenter extends BaseRequest implements IOperatorTaskPresenter {
    private ApiService apiService;
    private OperatorTaskActivityInterface viewInterface;
    private Disposable subscribe;

    @Inject
    public OperatorTaskPresenter(ApiService apiService, OperatorTaskActivityInterface viewInterface) {
        this.apiService = apiService;
        this.viewInterface = viewInterface;
    }

    /**
     * 请求个人基本信息
     */
    @Override
    public void requestOperatorTask(int processingStatus, int historyStatus) {
        if (isNetWorkEnable()) {
            viewInterface.onShowLoadingDialog();
            subscribe = Observable.merge(apiService.findCaseTaskByStatus(processingStatus), apiService.findCaseTaskByStatus(historyStatus))
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(taskModel -> {
                        if (taskModel.getStatus() == 200) {
                            if (taskModel.getData().getData() != null && !taskModel.getData().getData().isEmpty()) {
                                if (taskModel.getData().getStatus() == processingStatus) {
                                    ArrayList<TaskModel.DataBeanX.DataBean.TasksBean> tasksBeans = new ArrayList<>();
                                    for (TaskModel.DataBeanX.DataBean dataBean : taskModel.getData().getData()) {
                                        for (TaskModel.DataBeanX.DataBean.TasksBean taskBean : dataBean.getTasks()) {
                                            tasksBeans.add(taskBean);
                                        }
                                    }
                                    viewInterface.onProcessingTaskSuccess(tasksBeans);
                                } else if (taskModel.getData().getStatus() == historyStatus) {
                                    ArrayList<TaskModel.DataBeanX.DataBean.TasksBean> tasksBeans = new ArrayList<>();
                                    for (TaskModel.DataBeanX.DataBean dataBean : taskModel.getData().getData()) {
                                        for (TaskModel.DataBeanX.DataBean.TasksBean taskBean : dataBean.getTasks()) {
                                            tasksBeans.add(taskBean);
                                        }
                                    }
                                    viewInterface.onHistoryTaskSuccess(tasksBeans);
                                }
                                viewInterface.onDisLoadingDialog();
                            } else {
                                if (taskModel.getData().getStatus() == processingStatus) {
                                    viewInterface.onFailed("在办任务数据为空");
                                }
                                if (taskModel.getData().getStatus() == historyStatus) {
                                    viewInterface.onFailed("历史任务数据为空");
                                }

                                viewInterface.onDisLoadingDialog();
                            }
                        } else {
                            viewInterface.onFailed(taskModel.getMsg());
                            viewInterface.onDisLoadingDialog();
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
