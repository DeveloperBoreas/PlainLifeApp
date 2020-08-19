package com.boreas.plainlife.mvp.views.viewinterfaces;

import com.boreas.plainlife.mvp.ViewInterface;
import com.boreas.plainlife.mvp.models.indexoperator.TaskModel;

import java.util.ArrayList;

public interface OperatorProcessingTaskFragmentInterface extends ViewInterface {
    /**
     * 请求历史任务信息
     */
    void onProcessingTaskMessageSuccess(ArrayList<TaskModel.DataBeanX.DataBean.TasksBean> tasksBeans);

    /**
     * 请求数据失败回调
     */
    void onFailed(String message);
}
