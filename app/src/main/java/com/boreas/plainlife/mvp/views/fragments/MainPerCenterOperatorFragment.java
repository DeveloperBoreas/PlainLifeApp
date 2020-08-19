package com.boreas.plainlife.mvp.views.fragments;

import android.content.Intent;

import androidx.recyclerview.widget.LinearLayoutManager;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CircleCrop;
import com.bumptech.glide.request.RequestOptions;
import com.boreas.plainlife.App;
import com.boreas.plainlife.R;
import com.boreas.plainlife.adapter.MainPerCenterUseEquipAdapter;
import com.boreas.plainlife.base.BaseFragment;
import com.boreas.plainlife.databinding.FragmentPerCenterOperatorLayoutBinding;
import com.boreas.plainlife.framwork.ClickProxy;
import com.boreas.plainlife.internal.components.DaggerMainPerCenterOperatorFragmentComponect;
import com.boreas.plainlife.internal.modules.MainPerCenterOperatorFragmentModule;
import com.boreas.plainlife.mvp.models.precenter.KeeperUsedDevicesModel;
import com.boreas.plainlife.mvp.models.precenter.PersonalBaseMsgModel;
import com.boreas.plainlife.mvp.presenters.presenterimpl.MainPerCenterOperatorPresenter;
import com.boreas.plainlife.mvp.views.activitys.MessageActivity;
import com.boreas.plainlife.mvp.views.activitys.OperatorApplyActivity;
import com.boreas.plainlife.mvp.views.activitys.OperatorMyRecordActivity;
import com.boreas.plainlife.mvp.views.activitys.OperatorTaskActivity;
import com.boreas.plainlife.mvp.views.activitys.SettingsActivity;
import com.boreas.plainlife.mvp.views.viewinterfaces.MainPerCenterOperatorFragmentInterface;
import com.boreas.plainlife.utils.ToastUtil;

import java.util.ArrayList;

import javax.inject.Inject;

public class MainPerCenterOperatorFragment extends BaseFragment<FragmentPerCenterOperatorLayoutBinding> implements MainPerCenterOperatorFragmentInterface {

    @Inject
    MainPerCenterOperatorPresenter presenter;
    private MainPerCenterUseEquipAdapter usedDevicesAdapter;

    /**
     * 延迟获取数据
     */
    @Override
    public void lazyFetchData() {
        presenter.requestPersonalBase();
        presenter.requestPersonalDevices();
    }

    @Override
    public int setContent() {
        return R.layout.fragment_per_center_operator_layout;
    }

    @Override
    public void initView() {
        ArrayList<KeeperUsedDevicesModel.DataBean.RecentDevicesBean> dataList = new ArrayList<>();
        this.binding.rcvUseEquip.setHasFixedSize(true);
        this.usedDevicesAdapter = new MainPerCenterUseEquipAdapter(getActivity(), dataList, R.layout.recycle_use_equip_list_layout);
        this.binding.rcvUseEquip.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false));
        this.binding.rcvUseEquip.setAdapter(this.usedDevicesAdapter);

        Glide.with(this)
                .load("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1588847280973&di=58c4db7897ffcc9576fe66e252529a40&imgtype=0&src=http%3A%2F%2Fpics1.baidu.com%2Ffeed%2Fb90e7bec54e736d12fa67dcb0d3268c4d4626920.jpeg%3Ftoken%3Dd92b368c528c0d0591e59251102b1c73%26s%3Df22fb044c4d80bc43db2fd1b03008099")
                .apply(RequestOptions.bitmapTransform(new CircleCrop()))
                .into(this.binding.ivUserHeadImg);
    }

    @Override
    public void initListener() {
        this.binding.llMyMessage.setOnClickListener(new ClickProxy(v -> startActivity(new Intent(getActivity(), MessageActivity.class))));
        this.binding.llMyTask.setOnClickListener(new ClickProxy(v -> startActivity(new Intent(getActivity(), OperatorTaskActivity.class))));
        this.binding.llMyApply.setOnClickListener(new ClickProxy(v -> startActivity(new Intent(getActivity(), OperatorApplyActivity.class))));
        this.binding.tvRecordMore.setOnClickListener(new ClickProxy(view -> {
            Intent intent = new Intent(getActivity(), OperatorMyRecordActivity.class);
            intent.putExtra("UPTAB", 0);
            startActivity(intent);
        }));
        this.binding.llTobeCollected.setOnClickListener(new ClickProxy(view -> {
            Intent intent = new Intent(getActivity(), OperatorMyRecordActivity.class);
            intent.putExtra("UPTAB", 1);
            startActivity(intent);
        }));
        this.binding.llReceived.setOnClickListener(new ClickProxy(view -> {
            Intent intent = new Intent(getActivity(), OperatorMyRecordActivity.class);
            intent.putExtra("UPTAB", 2);
            startActivity(intent);
        }));
        this.binding.llOverdue.setOnClickListener(new ClickProxy(view -> {
            Intent intent = new Intent(getActivity(), OperatorMyRecordActivity.class);
            intent.putExtra("UPTAB", 3);
            startActivity(intent);
        }));
        this.binding.llReturn.setOnClickListener(new ClickProxy(view -> {
            Intent intent = new Intent(getActivity(), OperatorMyRecordActivity.class);
            intent.putExtra("UPTAB", 4);
            startActivity(intent);
        }));
        this.binding.ivSettings.setOnClickListener(new ClickProxy(view -> {
            startActivity(new Intent(getActivity(), SettingsActivity.class));
        }));
    }

    @Override
    public void initComponent() {
        DaggerMainPerCenterOperatorFragmentComponect.builder()
                .netComponent(App.getInstance().getmNetComponent())
                .mainPerCenterOperatorFragmentModule(new MainPerCenterOperatorFragmentModule(this))
                .build()
                .inject(this);
    }

    @Override
    public boolean onBackPressed() {
        return false;
    }


    /**
     * 没有网络状态回调
     */
    @Override
    public void noNetWork() {
        ToastUtil.show(getActivity(), "当前无网络");
    }

    /**
     * 无数据状态回调
     */
    @Override
    public void noData() {

    }

    /**
     * 消除LoadingDialog
     */
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

    /**
     * 请求个人基本信息成功回调
     *
     * @param personalBaseMsgModel
     */
    @Override
    public void onPersonalBaseMsgSuccess(PersonalBaseMsgModel personalBaseMsgModel) {
        if (personalBaseMsgModel.getData().isSuccess() && personalBaseMsgModel.getData().getResult() != null) {
            PersonalBaseMsgModel.DataBean.ResultBean result = personalBaseMsgModel.getData().getResult();
            this.binding.tvUserName.setText(result.getUserName() + "");
            this.binding.tvJob.setText((result.getRole() != null && !result.getRole().isEmpty()) ? result.getRole().get(0) + "" : "操作员");
            this.binding.tvPerEquipCount.setText(result.getCurrDeviceNum() + "");
            this.binding.tvPerAttendance.setText(result.getHistoryNum() + "");
            this.binding.mTsk.setText(result.getToDoTaskNum() + "");
            this.binding.mApplication.setText(result.getApplicationNum() + "");
            this.binding.mAlermmsg.setText(result.getAlarmNum() + "");
        }
    }

    /**
     * 请求常用装备信息成功回调
     *
     * @param keeperUsedDevicesModel
     */
    @Override
    public void onPersonalDevices(KeeperUsedDevicesModel keeperUsedDevicesModel) {
        if (keeperUsedDevicesModel.getData() != null && keeperUsedDevicesModel.getData().getRecentDevices() != null && !keeperUsedDevicesModel.getData().getRecentDevices().isEmpty()) {
            this.usedDevicesAdapter.reset(keeperUsedDevicesModel.getData().getRecentDevices());
        }
    }


    /**
     * 请求数据失败回调
     *
     * @param message
     */
    @Override
    public void onFailed(String message) {
        ToastUtil.show(getActivity(), message);
    }

    @Override
    public void onDestroyView() {
        if (presenter != null) {
            presenter.onDestory();
        }
        super.onDestroyView();
    }
}
