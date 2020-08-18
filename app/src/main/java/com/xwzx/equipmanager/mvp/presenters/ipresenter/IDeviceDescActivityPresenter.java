package com.xwzx.equipmanager.mvp.presenters.ipresenter;

import com.xwzx.equipmanager.mvp.Presenter;
import com.xwzx.equipmanager.mvp.models.equipLib.EquipLibraryReceModel;

public interface IDeviceDescActivityPresenter extends Presenter {
    void requestAddShoppingCart(EquipLibraryReceModel.EquipDataListModel equipDataListModel);
}
