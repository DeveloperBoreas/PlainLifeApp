package com.boreas.plainlife.mvp.presenters.presenterimpl;

import com.boreas.plainlife.api.ApiService;
import com.boreas.plainlife.base.BaseRequest;
import com.boreas.plainlife.mvp.models.indexoperator.TaskModel;
import com.boreas.plainlife.mvp.presenters.ipresenter.IOperatorProcessingTaskPresenter;
import com.boreas.plainlife.mvp.views.viewinterfaces.OperatorProcessingTaskFragmentInterface;

import java.util.ArrayList;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class OperatorProcessingTaskPresenter extends BaseRequest implements IOperatorProcessingTaskPresenter {
    private ApiService apiService;
    private OperatorProcessingTaskFragmentInterface viewInterface;
    private Disposable disposable;

    @Inject
    public OperatorProcessingTaskPresenter(ApiService apiService, OperatorProcessingTaskFragmentInterface viewInterface) {
        this.apiService = apiService;
        this.viewInterface = viewInterface;
    }

    /**
     * 请求个人基本信息
     */
    @Override
    public void requestHistoryTask(int status) {
        if (isNetWorkEnable()) {
            viewInterface.onShowLoadingDialog();
            disposable = apiService.findCaseTaskByStatus(status)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(taskModel -> {
                        if (taskModel.getStatus() == 200) {
                            if (taskModel.getData().getData() != null && !taskModel.getData().getData().isEmpty()) {
                                ArrayList<TaskModel.DataBeanX.DataBean.TasksBean> tasksBeans = new ArrayList<>();
                                for (TaskModel.DataBeanX.DataBean dataBean : taskModel.getData().getData()) {
                                    for (TaskModel.DataBeanX.DataBean.TasksBean taskBean : dataBean.getTasks()) {
                                        tasksBeans.add(taskBean);
                                    }
                                }
                                viewInterface.onProcessingTaskMessageSuccess(tasksBeans);
                            }else{
                                viewInterface.onFailed("数据为空");
                            }
                        } else {
                            viewInterface.onFailed(taskModel.getMsg());
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
        if (disposable != null && !disposable.isDisposed()) {
            disposable.dispose();
        }
    }
}
