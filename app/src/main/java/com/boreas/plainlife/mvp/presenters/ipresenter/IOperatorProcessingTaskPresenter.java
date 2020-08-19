package com.boreas.plainlife.mvp.presenters.ipresenter;

import com.boreas.plainlife.mvp.Presenter;

public interface IOperatorProcessingTaskPresenter extends Presenter {
    /**
     * 请求在办任务信息
     */
    void requestHistoryTask(int status);

}
