package com.boreas.plainlife.mvp.views.viewinterfaces;

import com.boreas.plainlife.mvp.ViewInterface;
import com.boreas.plainlife.mvp.models.indexoperator.TaskModel;

import java.util.List;

public interface OperatorTaskActivityInterface extends ViewInterface {
    /**
     * 在办任务成功回调
     */
    void onProcessingTaskSuccess(List<TaskModel.DataBeanX.DataBean.TasksBean> processingList);

    /**
     * 历史任务成功回调
     */
    void onHistoryTaskSuccess(List<TaskModel.DataBeanX.DataBean.TasksBean> historyTaskList);

    /**
     * 请求数据失败回调
     */
    void onFailed(String message);
}
