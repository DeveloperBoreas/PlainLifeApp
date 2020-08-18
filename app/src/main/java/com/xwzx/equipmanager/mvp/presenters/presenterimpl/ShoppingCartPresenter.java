package com.xwzx.equipmanager.mvp.presenters.presenterimpl;

import android.content.Context;

import com.orhanobut.logger.Logger;
import com.xwzx.equipmanager.App;
import com.xwzx.equipmanager.api.ApiService;
import com.xwzx.equipmanager.base.BaseRequest;
import com.xwzx.equipmanager.mvp.models.login.LoginModel;
import com.xwzx.equipmanager.mvp.models.shopping.DeleteShoppingCartModel;
import com.xwzx.equipmanager.mvp.models.shopping.ShoppingCartModel;
import com.xwzx.equipmanager.mvp.presenters.ipresenter.IShoppingCartPresenter;
import com.xwzx.equipmanager.mvp.views.viewinterfaces.LoginActivityInterface;
import com.xwzx.equipmanager.mvp.views.viewinterfaces.ShoppingActivityInterface;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

public class ShoppingCartPresenter extends BaseRequest implements IShoppingCartPresenter {

    private ApiService apiService;
    private ShoppingActivityInterface shoppingActivityInterface;
    private Context context;
    private App app;

    private Disposable getCartSubscribe;
    private Disposable deleteDisposable;

    @Inject
    public ShoppingCartPresenter(ApiService apiService, ShoppingActivityInterface shoppingActivityInterface, Context context, App app) {
        this.apiService = apiService;
        this.shoppingActivityInterface = shoppingActivityInterface;
        this.context = context;
        this.app = app;
    }

    @Override
    public void requestData() {
        if (isNetWorkEnable()) {
            shoppingActivityInterface.onShowLoadingDialog();
            getCartSubscribe = apiService.getCart()
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .map(new Function<ShoppingCartModel, Map<String, ArrayList<ShoppingCartModel.ShoppingData>>>() {
                        @Override
                        public Map<String, ArrayList<ShoppingCartModel.ShoppingData>> apply(ShoppingCartModel shoppingCartModel) throws Exception {
                            HashMap<String, ArrayList<ShoppingCartModel.ShoppingData>> shoppingMap = new HashMap<>();
                            if (shoppingCartModel.getStatus() == 200) {
                                ArrayList<ShoppingCartModel.ShoppingData> data = shoppingCartModel.getData();
                                for (ShoppingCartModel.ShoppingData shoppingData : data) {
                                    if (!shoppingMap.containsKey(shoppingData.getType())) {
                                        ArrayList<ShoppingCartModel.ShoppingData> shoppingDataList = new ArrayList<>();
                                        shoppingDataList.add(shoppingData);
                                        shoppingMap.put(shoppingData.getType(), shoppingDataList);
                                    } else {
                                        ArrayList<ShoppingCartModel.ShoppingData> shoppingDataList = shoppingMap.get(shoppingData.getType());
                                        shoppingDataList.add(shoppingData);
                                    }
                                }
                            }
                            return shoppingMap;
                        }
                    })
                    .subscribe(s -> {
                        if (!s.isEmpty()) {
                            shoppingActivityInterface.onSuccess(s);
                            shoppingActivityInterface.onDisLoadingDialog();
                        } else {
                            shoppingActivityInterface.onDataIsEmpty();
                            shoppingActivityInterface.onDisLoadingDialog();
                        }

                    }, throwable -> {
                        Logger.e(throwable.getMessage());
                        shoppingActivityInterface.onFailed();
                        shoppingActivityInterface.onDisLoadingDialog();
                    });

            return;
        }
        shoppingActivityInterface.noNetWork();
    }

    @Override
    public void DeleShoppingCart(DeleteShoppingCartModel model) {
        if (isNetWorkEnable()) {
            shoppingActivityInterface.onShowLoadingDialog();
            deleteDisposable = apiService.delBatch(model)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .map(new Function<ShoppingCartModel, Map<String, ArrayList<ShoppingCartModel.ShoppingData>>>() {
                        @Override
                        public Map<String, ArrayList<ShoppingCartModel.ShoppingData>> apply(ShoppingCartModel shoppingCartModel) throws Exception {
                            HashMap<String, ArrayList<ShoppingCartModel.ShoppingData>> shoppingMap = new HashMap<>();
                            if (shoppingCartModel.getStatus() == 200) {
                                ArrayList<ShoppingCartModel.ShoppingData> data = shoppingCartModel.getData();
                                for (ShoppingCartModel.ShoppingData shoppingData : data) {
                                    if (!shoppingMap.containsKey(shoppingData.getType())) {
                                        ArrayList<ShoppingCartModel.ShoppingData> shoppingDataList = new ArrayList<>();
                                        shoppingDataList.add(shoppingData);
                                        shoppingMap.put(shoppingData.getType(), shoppingDataList);
                                    } else {
                                        ArrayList<ShoppingCartModel.ShoppingData> shoppingDataList = shoppingMap.get(shoppingData.getType());
                                        shoppingDataList.add(shoppingData);
                                    }
                                }
                            }
                            return shoppingMap;
                        }
                    })
                    .subscribe(s -> {
                        if (!s.isEmpty()) {
                            shoppingActivityInterface.onDeleteSuccess(s);
                            shoppingActivityInterface.onDisLoadingDialog();
                        } else {
                            shoppingActivityInterface.onFailed();
                            shoppingActivityInterface.onDisLoadingDialog();
                        }

                    }, throwable -> {
                        Logger.e(throwable.getMessage());
                        shoppingActivityInterface.onFailed();
                        shoppingActivityInterface.onDisLoadingDialog();
                    });
            return;

        }
        shoppingActivityInterface.noNetWork();
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
        if (getCartSubscribe != null && !getCartSubscribe.isDisposed()) {
            getCartSubscribe.dispose();
        }
        if (deleteDisposable != null && !deleteDisposable.isDisposed()) {
            deleteDisposable.dispose();
        }
    }
}
