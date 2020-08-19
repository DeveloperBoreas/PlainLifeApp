package com.boreas.plainlife.mvp.views.activitys;

import android.content.Intent;
import android.text.Html;
import android.view.View;
import android.widget.CompoundButton;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.boreas.plainlife.App;
import com.boreas.plainlife.Constant;
import com.boreas.plainlife.R;
import com.boreas.plainlife.adapter.ShoppingParentAdapter;
import com.boreas.plainlife.base.BaseActivity;
import com.boreas.plainlife.base.adapter.BaseRecyclerAdapter;
import com.boreas.plainlife.databinding.ActivityShoppingBinding;
import com.boreas.plainlife.events.ShopingBottomNumEvent;
import com.boreas.plainlife.framwork.ClickProxy;
import com.boreas.plainlife.internal.components.DaggerShoppingActivityComponent;
import com.boreas.plainlife.internal.modules.ShoppingActivityModule;
import com.boreas.plainlife.mvp.models.ShoppingModel;
import com.boreas.plainlife.mvp.models.shopping.DeleteShoppingCartModel;
import com.boreas.plainlife.mvp.models.shopping.ShoppingCartModel;
import com.boreas.plainlife.mvp.presenters.presenterimpl.ShoppingCartPresenter;
import com.boreas.plainlife.mvp.views.viewinterfaces.ShoppingActivityInterface;
import com.boreas.plainlife.utils.ToastUtil;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.Map;

import javax.inject.Inject;

public class ShoppingActivity extends BaseActivity<ActivityShoppingBinding> implements BaseRecyclerAdapter.OnItemClickListener, ShoppingActivityInterface {

    @Inject
    ShoppingCartPresenter shoppingCartPresenter;
    private static final int CODE_REQUEST_SUBMIT = 1001;
    private static final int CODE_RESULT_SUBMIT = 1002;

    @Override
    public int setContentView() {
        return R.layout.activity_shopping;
    }

    private ArrayList<ShoppingModel> shoppingModels;
    private ShoppingParentAdapter shoppingParentAdapter;

    @Override
    protected void initView() {
        this.binding.headLayout.rightTitle.setTag("管理");
        this.binding.totalText.setText(Html.fromHtml("合计 :<font color='#F8D42E'> 0 </font>个"));
        EventBus.getDefault().register(this);
        this.shoppingModels = new ArrayList<>();
        shoppingCartPresenter.requestData();
        this.binding.shoppingList.setHasFixedSize(false);
        this.binding.shoppingList.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        this.shoppingParentAdapter = (ShoppingParentAdapter) new ShoppingParentAdapter(this, this.shoppingModels, R.layout.activity_shopping_item_parent)
                 .setIsShowFooter((int) getResources().getDimension(R.dimen.main_bottomNavigtion_height_20))
                .setOnItemClickListener(this);
        this.binding.shoppingList.setAdapter(this.shoppingParentAdapter);
    }

    @Override
    protected void initListener() {
        this.binding.submit.setOnClickListener(new ClickProxy(v -> {
            // 提交申请
            ArrayList<ShoppingModel> selectedShopping = getSelectedShopping();
            if (selectedShopping.size() > 0) {
                if (App.getInstance().role == Constant.KEEPER_ROLE) {
                    Intent intent = new Intent(this, SubmitApplyActivity.class);
                    intent.putExtra("applyList", selectedShopping);
                    startActivityForResult(intent, CODE_REQUEST_SUBMIT);
                } else if (App.getInstance().role == Constant.OPERATOR_ROLE) {
                    Intent intent = new Intent(this, SubmitApplyOperationActivity.class);
                    intent.putExtra("applyList", selectedShopping);
                    startActivityForResult(intent, CODE_REQUEST_SUBMIT);
                }
            } else {
                ToastUtil.show(ShoppingActivity.this, "请选择装备");
            }

        }));
        this.binding.headLayout.back.setOnClickListener(v -> finish());

        this.binding.headLayout.rightBtn.setOnClickListener(new ClickProxy(view -> {
//            if(!rightClick){
//                this.binding.headLayout.rightTitle.setText("完成");
//            }
            String tag = (String) this.binding.headLayout.rightTitle.getTag();
            if ("管理".equals(tag)) {
                this.binding.headLayout.rightTitle.setText("完成");
                this.binding.headLayout.rightTitle.setTag("完成");
                this.binding.totalText.setVisibility(View.GONE);
                this.binding.cbCheckAll.setVisibility(View.VISIBLE);
                this.binding.submit.setVisibility(View.GONE);
                this.binding.delete.setVisibility(View.VISIBLE);
            } else {
                this.binding.headLayout.rightTitle.setText("管理");
                this.binding.headLayout.rightTitle.setTag("管理");
                this.binding.totalText.setVisibility(View.VISIBLE);
                this.binding.cbCheckAll.setVisibility(View.GONE);
                this.binding.submit.setVisibility(View.VISIBLE);
                this.binding.delete.setVisibility(View.GONE);
            }
        }));

        this.binding.delete.setOnClickListener(new ClickProxy(view -> {
            ArrayList<ShoppingModel> selectedShopping = getSelectedShopping();
            if (selectedShopping.size() > 0) {
                ArrayList<String> ids = new ArrayList<>();
                for (ShoppingModel shoppingModel : selectedShopping) {
                    if (shoppingModel.isChecked()) {
                        for (ShoppingModel.ShoppingChildModel shoppingChildModel : shoppingModel.getChildModels()) {
                            if (shoppingChildModel.isChecked()) {
                                String id = shoppingChildModel.getId();
                                ids.add(id);

                            }

                        }
                    }
                }
                DeleteShoppingCartModel model = new DeleteShoppingCartModel();
                model.setIds(ids);
                shoppingCartPresenter.DeleShoppingCart(model);
            } else {
                ToastUtil.show(ShoppingActivity.this, "请选择装备");
            }
        }));


        this.binding.cbCheckAll.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(compoundButton.isPressed()){
                    setAllCheck(b);
                }
            }
        });

    }

    private void setAllCheck(boolean isChecked) {
        for (ShoppingModel shoppingModel : this.shoppingModels) {
            shoppingModel.setChecked(isChecked);
            for (ShoppingModel.ShoppingChildModel shoppingChildModel : shoppingModel.getChildModels()) {
                shoppingChildModel.setChecked(isChecked);
            }
        }
        this.shoppingParentAdapter.notifyDataSetChanged();
        EventBus.getDefault().post(new ShopingBottomNumEvent());
    }

    private ArrayList<ShoppingModel> getSelectedShopping() {
        ArrayList<ShoppingModel> tempList = new ArrayList<>();
        for (ShoppingModel shoppingModel : this.shoppingModels) {
            if (shoppingModel.isChecked()) {
                ShoppingModel shoppingModel1 = new ShoppingModel().setChecked(true).setId(shoppingModel.getId()).setTitle(shoppingModel.getTitle());
                ArrayList<ShoppingModel.ShoppingChildModel> childModels = new ArrayList<>();
                for (ShoppingModel.ShoppingChildModel shoppingChildModel : shoppingModel.getChildModels()) {
                    if (shoppingChildModel.isChecked()) {
                        ShoppingModel.ShoppingChildModel childModel = new ShoppingModel.ShoppingChildModel()
                                .setChecked(true)
                                .setGoodsLocation(shoppingChildModel.getGoodsLocation())
                                .setId(shoppingChildModel.getId())
                                .setImgUrl(shoppingChildModel.getImgUrl())
                                .setType(shoppingChildModel.getType())
                                .setType_en(shoppingChildModel.getType_en())
                                .setTitle(shoppingChildModel.getTitle());
                        childModels.add(childModel);
                    }
                }
                shoppingModel1.setChildModels(childModels);
                tempList.add(shoppingModel1);
            }
        }
        return tempList;
    }

    @Override
    protected void initComponent() {
        DaggerShoppingActivityComponent.builder()
                .appComponent(App.getInstance().getmAppComponent())
                .netComponent(App.getInstance().getmNetComponent())
                .shoppingActivityModule(new ShoppingActivityModule(this))
                .build().inject(this);
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        shoppingCartPresenter.onDestory();
        EventBus.getDefault().unregister(this);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void updateText(ShopingBottomNumEvent shopingBottomNumEvent) {
        int total = 0;
        for (int i = 0, size = this.shoppingModels.size(); i < size; i++) {
            ShoppingModel shoppingModel = shoppingModels.get(i);
            for (int j = 0, jsize = shoppingModel.getChildModels().size(); j < jsize; j++) {
                ShoppingModel.ShoppingChildModel childModel = shoppingModel.getChildModels().get(j);
                if (childModel.isChecked()) {
                    total++;
                }
            }
        }
        this.binding.totalText.setText(Html.fromHtml("合计 :<font color='#F8D42E'> " + total + " </font>个"));
        this.binding.cbCheckAll.setChecked(isAllChecked());
    }

    //判断所有数据是否全选
    public boolean isAllChecked() {
        for (ShoppingModel shoppingModel : shoppingModels) {
            if (!shoppingModel.isChecked()) {
                return false;
            } else {
                for (ShoppingModel.ShoppingChildModel childModel : shoppingModel.getChildModels()) {
                    if (!childModel.isChecked()) {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    @Override
    public void onItemClick(RecyclerView parent, View view, int position) {
//        Logger.e("onItemClick :" + position);
        ShoppingModel shoppingModel = shoppingModels.get(position);
        if (shoppingModel == null) {
            return;
        }
        shoppingModel.setChecked(!shoppingModel.isChecked());
        shoppingParentAdapter.updateCheckedState(shoppingModel);

    }

    @Override
    public void onSuccess(Map<String, ArrayList<ShoppingCartModel.ShoppingData>> shoppingMap) {
        shoppingModels.clear();
        handleData(shoppingMap);
    }

    /**
     * 拆分数据，不同类型设备分别封装
     *
     * @param
     */
    private void handleData(Map<String, ArrayList<ShoppingCartModel.ShoppingData>> shoppingMap) {
        for (Map.Entry<String, ArrayList<ShoppingCartModel.ShoppingData>> entry : shoppingMap.entrySet()) {
            ShoppingModel shoppingModel = new ShoppingModel();
            shoppingModel.setChecked(false);
//            shoppingModel.setId(i);
            shoppingModel.setTitle(entry.getKey());
            ArrayList<ShoppingModel.ShoppingChildModel> childModels = new ArrayList<>();
            for (ShoppingCartModel.ShoppingData shoppingData : entry.getValue()) {
                ShoppingModel.ShoppingChildModel childModel = new ShoppingModel.ShoppingChildModel();
                childModel.setChecked(false);
                childModel.setGoodsLocation(shoppingData.getLocation());
                childModel.setId(shoppingData.getId());
                childModel.setImgUrl(shoppingData.getImg());
                childModel.setTitle(shoppingData.getName());
                childModel.setType(shoppingData.getType());
                childModel.setType_en(shoppingData.getType_en());
                childModels.add(childModel);
            }
            shoppingModel.setChildModels(childModels);
            shoppingModels.add(shoppingModel);
        }

        this.shoppingParentAdapter.notifyDataSetChanged();
        EventBus.getDefault().post(new ShopingBottomNumEvent());

    }

    @Override
    public void onFailed() {
        ToastUtil.show(ShoppingActivity.this, "请求失败");
    }

    @Override
    public void onDataIsEmpty() {

    }

    @Override
    public void onDeleteSuccess(Map<String, ArrayList<ShoppingCartModel.ShoppingData>> shoppingMap) {
        ToastUtil.show(ShoppingActivity.this, "删除成功");
        shoppingModels.clear();
        handleData(shoppingMap);
    }

    @Override
    public void noNetWork() {
        ToastUtil.show(ShoppingActivity.this, "当前无网络");
    }

    @Override
    public void noData() {

    }

    @Override
    public void onDisLoadingDialog() {
        this.dismissLoadingDialog();
    }

    /**
     * 显示LoadingDialog
     */
    @Override
    public void onShowLoadingDialog() {
        this.showLoadingDialog();
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == CODE_REQUEST_SUBMIT) {
            if (resultCode == CODE_RESULT_SUBMIT) {
                shoppingCartPresenter.requestData();
            }
        }
    }
}
