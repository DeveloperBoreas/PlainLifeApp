package com.boreas.plainlife.mvp.presenters.presenterimpl;

import com.orhanobut.logger.Logger;
import com.boreas.plainlife.api.ApiService;
import com.boreas.plainlife.base.BaseRequest;
import com.boreas.plainlife.mvp.models.equipLib.EquipLibraryReceModel;
import com.boreas.plainlife.mvp.models.shopping.AddShoppingCartModel;
import com.boreas.plainlife.mvp.presenters.ipresenter.IEquipCommonFragmentPresenter;
import com.boreas.plainlife.mvp.views.viewinterfaces.EquipCommonFragmentInterface;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class EquipCommonFragmentPresenter extends BaseRequest implements IEquipCommonFragmentPresenter {

    private ApiService apiService;
    private EquipCommonFragmentInterface equipCommonFragmentInterface;
    private Disposable subscribe;

    @Inject
    public EquipCommonFragmentPresenter(ApiService apiService, EquipCommonFragmentInterface equipCommonFragmentInterface) {
        this.apiService = apiService;
        this.equipCommonFragmentInterface = equipCommonFragmentInterface;
    }

    @Override
    public void requestAddShoppingCart(EquipLibraryReceModel.EquipDataListModel equipDataListModel) {
        if(isNetWorkEnable()){
            equipCommonFragmentInterface.onShowLoadingDialog();
            AddShoppingCartModel addShoppingCartModel = new AddShoppingCartModel(equipDataListModel.getId(),
                    equipDataListModel.getName(),equipDataListModel.getEquipmentType_en()
                    ,equipDataListModel.getImageUrls().size()>0?equipDataListModel.getImageUrls().get(0):"");
            subscribe = apiService.insert(addShoppingCartModel)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(baseResponse -> {
                        if (baseResponse.getStatus() == 200) {
                            equipCommonFragmentInterface.onSuccess(baseResponse.getMsg());
                        } else {
                            equipCommonFragmentInterface.onFailed(baseResponse.getMsg());
                        }
                        equipCommonFragmentInterface.onDisLoadingDialog();
                    }, throwable -> {
                        Logger.e(throwable.getMessage());
                        equipCommonFragmentInterface.onFailed(throwable.getMessage());
                        equipCommonFragmentInterface.onDisLoadingDialog();
                    });

            return;

        }

        equipCommonFragmentInterface.noNetWork();
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
