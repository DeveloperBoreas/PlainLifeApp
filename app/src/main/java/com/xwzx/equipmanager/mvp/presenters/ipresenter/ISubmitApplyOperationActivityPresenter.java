package com.xwzx.equipmanager.mvp.presenters.ipresenter;

import com.xwzx.equipmanager.mvp.Presenter;
import com.xwzx.equipmanager.mvp.models.submit.SubmitApprovalModel;

public interface ISubmitApplyOperationActivityPresenter extends Presenter {
    void findCaseAndTaskByUser();
    void submitApply(SubmitApprovalModel submitApproval);
}
