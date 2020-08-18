package com.xwzx.equipmanager.mvp.views.viewinterfaces;

import com.xwzx.equipmanager.mvp.ViewInterface;
import com.xwzx.equipmanager.mvp.models.indexoperator.TaskModel;
import com.xwzx.equipmanager.mvp.models.precenter.PersonalBaseMsgModel;

import java.util.ArrayList;
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
