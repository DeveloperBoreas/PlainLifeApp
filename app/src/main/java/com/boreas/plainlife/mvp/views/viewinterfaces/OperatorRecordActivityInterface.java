package com.boreas.plainlife.mvp.views.viewinterfaces;

import com.boreas.plainlife.mvp.ViewInterface;
import com.boreas.plainlife.mvp.models.precenter.OperatorAllRecordModel;

import java.util.List;

public interface OperatorRecordActivityInterface extends ViewInterface {
    /**
     * 全部记录成功回调
     */
    void onAllRecordSuccess(List<OperatorAllRecordModel.DataBeanXXX.InnerBean> allRecord);

    /**
     * 待领取记录成功回调
     */
    void onTakeRecordSuccess(List<OperatorAllRecordModel.DataBeanXXX.InnerBean> takeRecord);

    /**
     * 已领取记录成功回调
     */
    void onBackRecordSuccess(List<OperatorAllRecordModel.DataBeanXXX.InnerBean> backRecord);

    /**
     * 已逾期记录成功回调
     */
    void onBackedRecordSuccess(List<OperatorAllRecordModel.DataBeanXXX.InnerBean> backedRecord);

    /**
     * 已归还记录成功回调
     */
    void onReturnedRecordSuccess(List<OperatorAllRecordModel.DataBeanXXX.InnerBean> returnedRecord);

    /**
     * 统一成功回调
     */
    void onRecordSuccess();

    /**
     * 请求数据失败回调
     */
    void onFailed(String message);
}
