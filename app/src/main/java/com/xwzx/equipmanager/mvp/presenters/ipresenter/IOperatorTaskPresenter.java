package com.xwzx.equipmanager.mvp.presenters.ipresenter;

import com.xwzx.equipmanager.mvp.Presenter;

public interface IOperatorTaskPresenter extends Presenter {
    /**
     * 请求任务信息
     */
    void requestOperatorTask(int processingStatus,int historyStatus);

}
