package com.xwzx.equipmanager.mvp.presenters.presenterimpl;

import com.orhanobut.logger.Logger;
import com.xwzx.equipmanager.App;
import com.xwzx.equipmanager.Constant;
import com.xwzx.equipmanager.api.ApiService;
import com.xwzx.equipmanager.base.BaseRequest;
import com.xwzx.equipmanager.mvp.models.equipLib.EquipLibraryModel;
import com.xwzx.equipmanager.mvp.models.equipLib.EquipLibraryReceModel;
import com.xwzx.equipmanager.mvp.models.login.LoginModel;
import com.xwzx.equipmanager.mvp.presenters.ipresenter.ITestFragmentPresenter;
import com.xwzx.equipmanager.mvp.views.viewinterfaces.EquipFragmentInterface;
import com.xwzx.equipmanager.mvp.views.viewinterfaces.KeeperFragmentInterface;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

public class EquipFragmentPresenter extends BaseRequest implements ITestFragmentPresenter {
    private ApiService apiService;
    private EquipFragmentInterface equipFragmentInterface;
    private Disposable equipSubscribe;

    @Inject
    public EquipFragmentPresenter(ApiService apiService, EquipFragmentInterface equipFragmentInterface) {
        this.apiService = apiService;
        this.equipFragmentInterface = equipFragmentInterface;
    }

    @Override
    public void requestData() {
        if (isNetWorkEnable()) {
            //查询设备状态 1在库 2借出 3维修 4报废
            int status = 0;
            if(App.getInstance().role== Constant.KEEPER_ROLE){
                status = 0;
            }else if(App.getInstance().role== Constant.OPERATOR_ROLE){
                status = 1;
            }
            equipFragmentInterface.onShowLoadingDialog();
            equipSubscribe = apiService.findEquipment(new EquipLibraryModel(0, Integer.MAX_VALUE, null
                    , null, null, 0, status, 0))
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .map(new Function<EquipLibraryReceModel, Map<String, ArrayList<EquipLibraryReceModel.EquipDataListModel>>>() {
                        @Override
                        public Map<String, ArrayList<EquipLibraryReceModel.EquipDataListModel>> apply(EquipLibraryReceModel equipLibraryReceModel) throws Exception {
                            HashMap<String, ArrayList<EquipLibraryReceModel.EquipDataListModel>> equipMap = new HashMap<>();
                            if(200==equipLibraryReceModel.getStatus()){
                                EquipLibraryReceModel.EquipData data = equipLibraryReceModel.getData();
                                ArrayList<EquipLibraryReceModel.EquipDataListModel> dataList = data.getData();
                                for (EquipLibraryReceModel.EquipDataListModel model : dataList) {
                                    if (!equipMap.containsKey(model.getEquipmentType())) {
                                        ArrayList<EquipLibraryReceModel.EquipDataListModel> equipDataList = new ArrayList<>();
                                        equipDataList.add(model);
                                        equipMap.put(model.getEquipmentType(), equipDataList);
                                    } else {
                                        ArrayList<EquipLibraryReceModel.EquipDataListModel> equipDataListModels = equipMap.get(model.getEquipmentType());
                                        equipDataListModels.add(model);
                                    }
                                }
                            }
                            return equipMap;
                        }
                    })
                    .subscribe(s -> {
                        if(!s.isEmpty()){
                            equipFragmentInterface.onSuccess(s);
                            equipFragmentInterface.onDisLoadingDialog();
                        }else{
                            equipFragmentInterface.onFailed("无数据");
                            equipFragmentInterface.onDisLoadingDialog();
                        }

                    }, throwable -> {
                        Logger.e(throwable.getMessage());
                        equipFragmentInterface.onFailed("请求失败");
                        equipFragmentInterface.onDisLoadingDialog();
                    });

            return;
        }
        equipFragmentInterface.noNetWork();
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
        if (equipSubscribe != null && !equipSubscribe.isDisposed()) {
            equipSubscribe.dispose();
        }
    }
}
