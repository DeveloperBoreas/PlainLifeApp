package com.xwzx.equipmanager.mvp.views.viewinterfaces;

import com.xwzx.equipmanager.mvp.ViewInterface;
import com.xwzx.equipmanager.mvp.models.indexoperator.TaskModel;
import com.xwzx.equipmanager.mvp.models.precenter.PersonalBaseMsgModel;

import java.util.ArrayList;

public interface OperatorHistoryTaskFragmentInterface extends ViewInterface {
    /**
     * 请求历史任务信息
     */
    void onHistoryTaskMessageSuccess(ArrayList<TaskModel.DataBeanX.DataBean.TasksBean> tasksBeans);

    /**
     * 请求数据失败回调
     */
    void onFailed(String message);
}
