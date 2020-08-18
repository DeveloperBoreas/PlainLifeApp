package com.xwzx.equipmanager.mvp.presenters.ipresenter;

import com.xwzx.equipmanager.mvp.Presenter;

public interface IOperatorProcessingTaskPresenter extends Presenter {
    /**
     * 请求在办任务信息
     */
    void requestHistoryTask(int status);

}
