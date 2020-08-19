package com.boreas.plainlife.mvp.presenters.ipresenter;

import com.boreas.plainlife.mvp.Presenter;
import com.boreas.plainlife.mvp.models.shopping.AddShoppingCartModel;

import java.util.List;

public interface IOperatorHistoryTaskPresenter extends Presenter {
    /**
     * 请求History任务信息
     */
    void requestHistoryTask(int status);

    /**
     * 添加装备至购物车
     * @param addShoppingCartModels
     */
    void requestAddShoppingCart(List<AddShoppingCartModel> addShoppingCartModels);
}
