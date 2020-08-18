package com.xwzx.equipmanager.mvp.views.fragments;

import android.content.Intent;

import androidx.recyclerview.widget.LinearLayoutManager;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CircleCrop;
import com.bumptech.glide.request.RequestOptions;
import com.xwzx.equipmanager.App;
import com.xwzx.equipmanager.R;
import com.xwzx.equipmanager.adapter.MainPerCenterReturnAdapter;
import com.xwzx.equipmanager.base.BaseFragment;
import com.xwzx.equipmanager.databinding.FragmentPerCenterCustodianLayoutBinding;
import com.xwzx.equipmanager.framwork.ClickProxy;
import com.xwzx.equipmanager.internal.components.DaggerMainPerCenterKeeperFragmentComponect;
import com.xwzx.equipmanager.internal.components.MainPerCenterKeeperFragmentComponect;
import com.xwzx.equipmanager.internal.modules.MainPerCenterKeeperFragmentModule;
import com.xwzx.equipmanager.mvp.Presenter;
import com.xwzx.equipmanager.mvp.presenters.presenterimpl.MainPerCenterKeeperPresenter;
import com.xwzx.equipmanager.mvp.views.activitys.KeeperApplingActivity;
import com.xwzx.equipmanager.mvp.views.activitys.KeeperMyOperationActivity;
import com.xwzx.equipmanager.mvp.views.activitys.KeeperUpComingActivity;
import com.xwzx.equipmanager.mvp.views.activitys.MessageActivity;
import com.xwzx.equipmanager.mvp.views.activitys.SettingsActivity;
import com.xwzx.equipmanager.mvp.views.viewinterfaces.MainPerCenterKeeperFragmentInterface;


import java.util.ArrayList;

import javax.inject.Inject;

public class MainPerCenterKeeperFragment extends BaseFragment<FragmentPerCenterCustodianLayoutBinding> implements MainPerCenterKeeperFragmentInterface {

    @Inject
    MainPerCenterKeeperPresenter presenter;

    @Override
    public void lazyFetchData() {
        presenter.requestAll();
    }

    @Override
    public int setContent() {
        return R.layout.fragment_per_center_custodian_layout;
    }

    @Override
    public void initView() {
        ArrayList<String> dataList = new ArrayList<>();
        dataList.add("1");
        dataList.add("1");
        dataList.add("1");
        dataList.add("1");
        this.binding.rcvReturnEquip.setHasFixedSize(true);
        MainPerCenterReturnAdapter adapter = new MainPerCenterReturnAdapter(getActivity(), dataList, R.layout.recycle_return_item_layout);
        this.binding.rcvReturnEquip.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false));
        this.binding.rcvReturnEquip.setAdapter(adapter);

        Glide.with(this)
                .load("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1588847280973&di=58c4db7897ffcc9576fe66e252529a40&imgtype=0&src=http%3A%2F%2Fpics1.baidu.com%2Ffeed%2Fb90e7bec54e736d12fa67dcb0d3268c4d4626920.jpeg%3Ftoken%3Dd92b368c528c0d0591e59251102b1c73%26s%3Df22fb044c4d80bc43db2fd1b03008099")
                .apply(RequestOptions.bitmapTransform(new CircleCrop()))
                .into(this.binding.ivUserHeadImg);

    }

    @Override
    public void initListener() {
        this.binding.tvOperationMore.setOnClickListener(new ClickProxy(view -> {
            Intent intent = new Intent(getActivity(), KeeperMyOperationActivity.class);
            intent.putExtra("UPTAB",0);
            startActivity(intent);
        }));
        //我的待办
        this.binding.llMyUpcoming.setOnClickListener(new ClickProxy(v -> {
            Intent intent = new Intent(getActivity(), KeeperUpComingActivity.class);
            startActivity(intent);
        }));
        //我的申请
        this.binding.llMyApply.setOnClickListener(new ClickProxy(v -> {
            startActivity(new Intent(getActivity(), KeeperApplingActivity.class));
        }));
        //我的消息
        this.binding.llMyMessage.setOnClickListener(new ClickProxy(v -> {
            startActivity(new Intent(getActivity(), MessageActivity.class));
        }));
        //出库
        this.binding.llOutStock.setOnClickListener(new ClickProxy(view -> {
            Intent intent = new Intent(getActivity(), KeeperMyOperationActivity.class);
            intent.putExtra("UPTAB",1);
            startActivity(intent);
        }));
        //盘库
        this.binding.llInventory.setOnClickListener(new ClickProxy(view -> {
            Intent intent = new Intent(getActivity(), KeeperMyOperationActivity.class);
            intent.putExtra("UPTAB",2);
            startActivity(intent);
        }));
        //维修
        this.binding.llService.setOnClickListener(new ClickProxy(view -> {
            Intent intent = new Intent(getActivity(), KeeperMyOperationActivity.class);
            intent.putExtra("UPTAB",3);
            startActivity(intent);
        }));
        //检测
        this.binding.llTesting.setOnClickListener(new ClickProxy(view -> {
            Intent intent = new Intent(getActivity(), KeeperMyOperationActivity.class);
            intent.putExtra("UPTAB",4);
            startActivity(intent);
        }));
        //报废
        this.binding.llScrap.setOnClickListener(new ClickProxy(view -> {
            Intent intent = new Intent(getActivity(), KeeperMyOperationActivity.class);
            intent.putExtra("UPTAB",5);
            startActivity(intent);
        }));

        this.binding.ivSettings.setOnClickListener(new ClickProxy(view -> {
            startActivity(new Intent(getActivity(), SettingsActivity.class));
        }));
    }

    @Override
    public void initComponent() {
        DaggerMainPerCenterKeeperFragmentComponect.builder()
                .netComponent(App.getInstance().getmNetComponent())
                .mainPerCenterKeeperFragmentModule(new MainPerCenterKeeperFragmentModule(this))
                .build()
                .inject(this);
    }

    @Override
    public boolean onBackPressed() {
        return false;
    }

    /**
     * 请求数据成功回调
     */
    @Override
    public void onSuccess() {

    }

    /**
     * 请求数据失败回调
     */
    @Override
    public void onFailed() {

    }

    /**
     * 没有网络状态回调
     */
    @Override
    public void noNetWork() {

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

    }

    /**
     * 显示LoadingDialog
     */
    @Override
    public void onShowLoadingDialog() {

    }
}
