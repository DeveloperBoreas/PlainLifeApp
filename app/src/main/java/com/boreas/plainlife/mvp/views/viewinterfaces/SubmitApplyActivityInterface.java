package com.boreas.plainlife.mvp.views.viewinterfaces;

import com.boreas.plainlife.base.BaseResponse;
import com.boreas.plainlife.mvp.ViewInterface;

public interface SubmitApplyActivityInterface extends ViewInterface {
    /**
     * 数据请求成功回调
     * @param s
     */
    void onSuccess(BaseResponse<String> s);
    /**
     * 数据请求失败回调
     */
    void onFailed();
}