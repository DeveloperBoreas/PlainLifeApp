package com.xwzx.equipmanager.mvp.views.viewinterfaces;

import com.xwzx.equipmanager.base.BaseResponse;
import com.xwzx.equipmanager.mvp.ViewInterface;

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
