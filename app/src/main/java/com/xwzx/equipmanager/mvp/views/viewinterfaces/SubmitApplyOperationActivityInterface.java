package com.xwzx.equipmanager.mvp.views.viewinterfaces;

import com.xwzx.equipmanager.mvp.ViewInterface;
import com.xwzx.equipmanager.mvp.models.submit.FindCaseByUserModel;

import java.util.List;

public interface SubmitApplyOperationActivityInterface extends ViewInterface {
    /**
     * 数据请求成功回调
     * @param
     */
    void onSuccess();

    void onGetCaseAndTask(List<FindCaseByUserModel.CaseItemModel> data);

    void onSubmitApply(String msg);
    /**
     * 数据请求失败回调
     */
    void onFailed(String msg);
}
