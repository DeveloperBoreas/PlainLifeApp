package com.xwzx.equipmanager.mvp.presenters.presenterimpl;

import com.orhanobut.logger.Logger;
import com.xwzx.equipmanager.api.ApiService;
import com.xwzx.equipmanager.base.BaseRequest;
import com.xwzx.equipmanager.mvp.models.equipLib.EquipLibraryReceModel;
import com.xwzx.equipmanager.mvp.models.shopping.AddShoppingCartModel;
import com.xwzx.equipmanager.mvp.presenters.ipresenter.IDeviceDescActivityPresenter;
import com.xwzx.equipmanager.mvp.views.viewinterfaces.DeviceDescActivityInterface;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class DeviceDescActivityPresneter  extends BaseRequest implements IDeviceDescActivityPresenter {
    private ApiService apiService;
    private DeviceDescActivityInterface descActivityInterface;
    private Disposable subscribe;

    @Inject
    public DeviceDescActivityPresneter(ApiService apiService, DeviceDescActivityInterface descActivityInterface) {
        this.apiService = apiService;
        this.descActivityInterface = descActivityInterface;
    }

    @Override
    public void requestAddShoppingCart(EquipLibraryReceModel.EquipDataListModel equipDataListModel) {
        if(isNetWorkEnable()){
            descActivityInterface.onShowLoadingDialog();
            AddShoppingCartModel addShoppingCartModel = new AddShoppingCartModel(equipDataListModel.getId(),
                    equipDataListModel.getName(),equipDataListModel.getEquipmentType_en()
                    ,equipDataListModel.getImageUrls().size()>0?equipDataListModel.getImageUrls().get(0):"");
            subscribe = apiService.insert(addShoppingCartModel)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(baseResponse -> {
                        if (baseResponse.getStatus() == 200) {
                            descActivityInterface.addShoppingCartSuccess(baseResponse.getMsg());
                        } else {
                            descActivityInterface.addShoppingCartFaild(baseResponse.getMsg());
                        }
                        descActivityInterface.onDisLoadingDialog();
                    }, throwable -> {
                        Logger.e(throwable.getMessage());
                        descActivityInterface.addShoppingCartFaild(throwable.getMessage());
                        descActivityInterface.onDisLoadingDialog();
                    });

            return;

        }

        descActivityInterface.noNetWork();
    }

    @Override
    public void onInit() {

    }

    @Override
    public void onResume() {

    }

    @Override
    public void onPause() {

    }

    @Override
    public void onDestory() {
        if (subscribe != null && !subscribe.isDisposed()) {
            subscribe.dispose();
        }
    }
}
