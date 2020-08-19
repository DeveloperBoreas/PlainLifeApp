package com.boreas.plainlife.mvp.presenters.ipresenter;

import com.boreas.plainlife.mvp.Presenter;
import com.boreas.plainlife.mvp.models.shopping.DeleteShoppingCartModel;

public interface IShoppingCartPresenter extends Presenter {
    void requestData();

    void DeleShoppingCart(DeleteShoppingCartModel model);
}
