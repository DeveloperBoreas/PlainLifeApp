package com.xwzx.equipmanager.mvp.presenters.ipresenter;

import com.xwzx.equipmanager.mvp.Presenter;
import com.xwzx.equipmanager.mvp.models.shopping.DeleteShoppingCartModel;

public interface IShoppingCartPresenter extends Presenter {
    void requestData();

    void DeleShoppingCart(DeleteShoppingCartModel model);
}
