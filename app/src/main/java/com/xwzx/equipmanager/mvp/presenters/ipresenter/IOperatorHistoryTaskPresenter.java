package com.xwzx.equipmanager.mvp.presenters.ipresenter;

import com.xwzx.equipmanager.mvp.Presenter;
import com.xwzx.equipmanager.mvp.models.equipLib.EquipLibraryReceModel;
import com.xwzx.equipmanager.mvp.models.shopping.AddShoppingCartModel;

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
