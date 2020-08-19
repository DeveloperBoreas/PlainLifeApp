package com.boreas.plainlife.mvp.views.viewinterfaces;

import com.boreas.plainlife.mvp.ViewInterface;
import com.boreas.plainlife.mvp.models.precenter.OperatorApplicationModel;

import java.util.List;

public interface OperatorApplyActivityInterface extends ViewInterface {
    /**
     * 申请中成功回调
     */
    void onAgreeSuccess(List<OperatorApplicationModel.DataBean.AgreeBean> agreeBeans);

    /**
     * 已完成成功回调
     */
    void onApplySuccess(List<OperatorApplicationModel.DataBean.ApplyBean> applyBeans);

    /**
     * 已完成成功回调
     */
    void onRehecrtSuccess(List<OperatorApplicationModel.DataBean.RehecrtBean> rehecrtBeans);

    /**
     * 请求数据失败回调
     */
    void onFailed(String message);
}
