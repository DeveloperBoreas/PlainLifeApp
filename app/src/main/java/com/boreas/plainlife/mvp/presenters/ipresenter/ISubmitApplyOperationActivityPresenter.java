package com.boreas.plainlife.mvp.presenters.ipresenter;

import com.boreas.plainlife.mvp.Presenter;
import com.boreas.plainlife.mvp.models.submit.SubmitApprovalModel;

public interface ISubmitApplyOperationActivityPresenter extends Presenter {
    void findCaseAndTaskByUser();
    void submitApply(SubmitApprovalModel submitApproval);
}
