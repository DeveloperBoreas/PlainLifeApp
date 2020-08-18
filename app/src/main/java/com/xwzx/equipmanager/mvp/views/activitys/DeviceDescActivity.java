package com.xwzx.equipmanager.mvp.views.activitys;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.xwzx.equipmanager.App;
import com.xwzx.equipmanager.Constant;
import com.xwzx.equipmanager.R;
import com.xwzx.equipmanager.base.BaseActivity;
import com.xwzx.equipmanager.databinding.ActivityDeviceDescBinding;
import com.xwzx.equipmanager.framwork.ClickProxy;
import com.xwzx.equipmanager.internal.components.DaggerDeviceDescActivityComponect;
import com.xwzx.equipmanager.internal.modules.DeviceDescActivityModule;
import com.xwzx.equipmanager.mvp.models.ShoppingModel;
import com.xwzx.equipmanager.mvp.models.equipLib.EquipLibraryReceModel;
import com.xwzx.equipmanager.mvp.presenters.presenterimpl.DeviceDescActivityPresneter;
import com.xwzx.equipmanager.mvp.views.viewinterfaces.DeviceDescActivityInterface;
import com.xwzx.equipmanager.utils.TimeUtils;
import com.xwzx.equipmanager.utils.ToastUtil;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.inject.Inject;


public class DeviceDescActivity extends BaseActivity<ActivityDeviceDescBinding> implements DeviceDescActivityInterface {


    private EquipLibraryReceModel.EquipDataListModel equipDeviceModel;
    @Inject
    public DeviceDescActivityPresneter descActivityPresneter;

    @Override
    public int setContentView() {
        return R.layout.activity_device_desc;
    }

    @Override
    public void handlerJumpData(Intent intent) {
        super.handlerJumpData(intent);
        Bundle bundle = intent.getBundleExtra("bundle");
        if (bundle != null) {
            equipDeviceModel = (EquipLibraryReceModel.EquipDataListModel) bundle.getSerializable("data");
        }
    }

    @Override
    protected void initView() {
        this.binding.headLayout.rightTitle.setVisibility(View.GONE);
        this.binding.headLayout.rightBtn.setVisibility(View.GONE);
        this.binding.headLayout.headTitle.setText(getResources().getString(R.string.devicedesc));
        this.binding.jindu.setVisibility(Constant.KEEPER_ROLE == App.getInstance().role ? View.VISIBLE : View.GONE);
        String[] arr = {"提交申请 ", "提交申请 ", "提交申请 ", "提交申请 ", "提交申请 ", "完成"};
        String[] arr1 = {"04.01 10:30:21", "04.01 10:30:21", "04.01 10:30:21", "04.01 10:30:21", "04.01 10:30:21", ""};
        this.binding.indicatorText.setMax(6 * 10);
        this.binding.indicatorText.setProgress(6 * 10 / 3);
        this.binding.indicatorText.customTickTexts(arr);
        this.binding.indicatorText.customTickTimeTexts(arr1);
        this.initViewData();
    }

    private void initViewData() {
        if (equipDeviceModel != null) {
            this.binding.deviceTitle.setText(equipDeviceModel.getName());
            this.binding.evaluate.setRating(equipDeviceModel.getScore());
            this.binding.evaluateText.setText(equipDeviceModel.getScore()+".0");
            this.binding.tvEquipNum.setText(equipDeviceModel.getNum());
            this.binding.tvPosition.setText(equipDeviceModel.getPosition());
            this.binding.tvEquipType.setText(equipDeviceModel.getEquipmentType());
            this.binding.tvEquipStatus.setText(equipDeviceModel.getStatus());
            String time = "";
            try {
                time = TimeUtils.StringToDate(equipDeviceModel.getPurchaseTime());
            } catch (ParseException e) {
                e.printStackTrace();
            }
            this.binding.tvPurchaseTime.setText("采购日期："+time);
            this.binding.tvManufact.setText("生产厂商："+equipDeviceModel.getManufacturer());
            this.binding.tvDescription.setText("功能特点："+equipDeviceModel.getPresentation());
            StringBuffer stringBuffer = new StringBuffer();
            List<EquipLibraryReceModel.EquipDataListModel.ParameterBean> parameter = equipDeviceModel.getParameter();
            for (EquipLibraryReceModel.EquipDataListModel.ParameterBean parameterBean :parameter) {
                stringBuffer.append(parameterBean.getName()+":"+parameterBean.getValue()+"\r\n");
            }
            this.binding.tvParamter.setText(stringBuffer.toString());

        }
    }


    @Override
    protected void initListener() {
        this.binding.headLayout.back.setOnClickListener(new ClickProxy(v -> finish()));
        this.binding.deviceImg.setOnClickListener(new ClickProxy(v -> {
            startActivity(new Intent(this, Show3DActivity.class));
        }));

        this.binding.trunDown.setOnClickListener(new ClickProxy(view -> {
            descActivityPresneter.requestAddShoppingCart(equipDeviceModel);
        }));

        this.binding.agree.setOnClickListener(new ClickProxy(view -> {
            ArrayList<ShoppingModel> shoppingModels = handleData();
            if (App.getInstance().role == Constant.KEEPER_ROLE) {
                Intent intent = new Intent(this, SubmitApplyActivity.class);
                intent.putExtra("applyList", shoppingModels);
                startActivity(intent);
            } else if (App.getInstance().role == Constant.OPERATOR_ROLE) {
                Intent intent = new Intent(this, SubmitApplyOperationActivity.class);
                intent.putExtra("applyList", shoppingModels);
                startActivity(intent);
            }
        }));
    }

    private ArrayList<ShoppingModel> handleData() {
        ArrayList<ShoppingModel> shoppingModels = new ArrayList<>();
        ShoppingModel shoppingModel = new ShoppingModel();
        shoppingModel.setChecked(false);
        shoppingModel.setTitle(equipDeviceModel.getEquipmentType());
        ArrayList<ShoppingModel.ShoppingChildModel> childModels = new ArrayList<>();
        ShoppingModel.ShoppingChildModel childModel = new ShoppingModel.ShoppingChildModel();
        childModel.setChecked(false);
        childModel.setGoodsLocation(equipDeviceModel.getPosition());
        childModel.setId(equipDeviceModel.getId());
        childModel.setImgUrl(equipDeviceModel.getImageUrls().size()>0?equipDeviceModel.getImageUrls().get(0):"");
        childModel.setTitle(equipDeviceModel.getName());
        childModel.setType(equipDeviceModel.getEquipmentType());
        childModel.setType_en(equipDeviceModel.getEquipmentType_en());
        childModels.add(childModel);
        shoppingModel.setChildModels(childModels);
        shoppingModels.add(shoppingModel);
        return shoppingModels;
    }

    @Override
    protected void initComponent() {
        DaggerDeviceDescActivityComponect.builder()
                .netComponent(App.getInstance().getmNetComponent())
                .deviceDescActivityModule(new DeviceDescActivityModule(this))
                .build()
                .inject(this);
    }


    @Override
    protected void initData() {

    }

    @Override
    public void addShoppingCartSuccess(String msg) {
        ToastUtil.show(this,msg);
    }

    @Override
    public void addShoppingCartFaild(String msg) {
        ToastUtil.show(this,msg);
    }

    @Override
    public void noNetWork() {

    }

    @Override
    public void noData() {

    }

    @Override
    public void onDisLoadingDialog() {

    }

    @Override
    public void onShowLoadingDialog() {

    }
}
