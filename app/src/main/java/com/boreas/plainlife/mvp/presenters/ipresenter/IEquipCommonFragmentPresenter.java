package com.boreas.plainlife.mvp.presenters.ipresenter;

import com.boreas.plainlife.mvp.Presenter;
import com.boreas.plainlife.mvp.models.equipLib.EquipLibraryReceModel;

public interface IEquipCommonFragmentPresenter extends Presenter {
    void requestAddShoppingCart(EquipLibraryReceModel.EquipDataListModel equipDataListModel);
}
