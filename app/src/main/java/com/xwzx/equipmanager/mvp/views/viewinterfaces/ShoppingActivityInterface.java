package com.xwzx.equipmanager.mvp.views.viewinterfaces;

import com.xwzx.equipmanager.base.BaseResponse;
import com.xwzx.equipmanager.mvp.ViewInterface;
import com.xwzx.equipmanager.mvp.models.shopping.ShoppingCartModel;

import java.util.ArrayList;
import java.util.Map;

public interface ShoppingActivityInterface extends ViewInterface {
    /**
     * 数据请求成功回调
     * @param s
     */
    void onSuccess(Map<String, ArrayList<ShoppingCartModel.ShoppingData>> s);
    /**
     * 数据请求失败回调
     */
    void onFailed();

    void onDataIsEmpty();

    void onDeleteSuccess(Map<String, ArrayList<ShoppingCartModel.ShoppingData>> s);
}
