package com.boreas.plainlife.mvp.presenters.presenterimpl;

import com.boreas.plainlife.api.ApiService;
import com.boreas.plainlife.base.BaseRequest;
import com.boreas.plainlife.mvp.models.indexoperator.TaskModel;
import com.boreas.plainlife.mvp.models.shopping.AddShoppingCartModel;
import com.boreas.plainlife.mvp.presenters.ipresenter.IOperatorHistoryTaskPresenter;
import com.boreas.plainlife.mvp.views.viewinterfaces.OperatorHistoryTaskFragmentInterface;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class OperatorHistoryTaskPresenter extends BaseRequest implements IOperatorHistoryTaskPresenter {
    private ApiService apiService;
    private OperatorHistoryTaskFragmentInterface viewInterface;
    private Disposable taskDisposable;

    @Inject
    public OperatorHistoryTaskPresenter(ApiService apiService, OperatorHistoryTaskFragmentInterface viewInterface) {
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
            this.taskDisposable = apiService.findCaseTaskByStatus(status)
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
                                viewInterface.onHistoryTaskMessageSuccess(tasksBeans);
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
    public void requestAddShoppingCart(List<AddShoppingCartModel> addShoppingCartModels) {
        if (isNetWorkEnable()) {
            viewInterface.onShowLoadingDialog();
            this.taskDisposable = apiService.inserts(addShoppingCartModels)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(taskModel -> {
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
        if (taskDisposable != null && !taskDisposable.isDisposed()) {
            taskDisposable.dispose();
        }
    }
}
