package com.boreas.plainlife.mvp.presenters.ipresenter;

import com.boreas.plainlife.mvp.Presenter;

public interface IOperatorTaskPresenter extends Presenter {
    /**
     * 请求任务信息
     */
    void requestOperatorTask(int processingStatus,int historyStatus);

}
