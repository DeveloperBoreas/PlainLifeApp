package com.xwzx.equipmanager.mvp.views.fragments;

import android.os.Bundle;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.google.android.material.tabs.TabLayout;
import com.orhanobut.logger.Logger;
import com.xwzx.equipmanager.App;
import com.xwzx.equipmanager.R;
import com.xwzx.equipmanager.adapter.ViewPagerAdapter;
import com.xwzx.equipmanager.base.BaseFragment;
import com.xwzx.equipmanager.databinding.FragmentEquipLayoutBinding;
import com.xwzx.equipmanager.events.ShopingBottomNumEvent;
import com.xwzx.equipmanager.events.UpDataMainEquip;
import com.xwzx.equipmanager.internal.components.DaggerMainEquipFragmentComponect;
import com.xwzx.equipmanager.internal.modules.MainEquipFragmentModule;
import com.xwzx.equipmanager.mvp.models.EquipTabModel;
import com.xwzx.equipmanager.mvp.models.ShoppingModel;
import com.xwzx.equipmanager.mvp.models.equipLib.EquipLibraryReceModel;
import com.xwzx.equipmanager.mvp.models.shopping.ShoppingCartModel;
import com.xwzx.equipmanager.mvp.presenters.presenterimpl.EquipFragmentPresenter;
import com.xwzx.equipmanager.mvp.views.viewinterfaces.EquipFragmentInterface;
import com.xwzx.equipmanager.utils.ToastUtil;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;

public class MainEquipFragment extends BaseFragment<FragmentEquipLayoutBinding> implements EquipFragmentInterface {
    @Inject
    EquipFragmentPresenter presenter;
    private ArrayList<EquipTabModel> tabModels;
    private ArrayList<BaseFragment> tabFragments;
    private ViewPagerAdapter viewPagerAdapter;
    //  private String[] tabTitles = new String[]{"全部", "便携设备", "密拍设备",};

    /**
     * 延迟获取数据
     */
    @Override
    public void lazyFetchData() {
        presenter.requestData();
    }

    @Override
    public int setContent() {
        return R.layout.fragment_equip_layout;
    }

    @Override
    public void initView() {
        this.tabModels = new ArrayList<>();
        this.tabFragments = new ArrayList<>();
        viewPagerAdapter = new ViewPagerAdapter(getChildFragmentManager(), this.tabFragments);
//        this.binding.viewPager.setOffscreenPageLimit(this.tabFragments.size());
//        this.binding.viewPager.setOffscreenPageLimit(100);
        this.binding.viewPager.setAdapter(viewPagerAdapter);
        this.binding.tabLayout.setupWithViewPager(this.binding.viewPager);
    }

    @Override
    public void initListener() {
        EventBus.getDefault().register(this);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void update(UpDataMainEquip upDataMainEquip) {
       if(upDataMainEquip.isUpData()){
           presenter.requestData();
       }
    }

    @Override
    public void initComponent() {
        DaggerMainEquipFragmentComponect.builder()
                .netComponent(App.getInstance().getmNetComponent())
                .mainEquipFragmentModule(new MainEquipFragmentModule(this))
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
    public void onSuccess(Map<String,ArrayList<EquipLibraryReceModel.EquipDataListModel>> equipMap) {
        tabModels.clear();
        tabFragments.clear();
        //全部类型装备
        ArrayList<EquipLibraryReceModel.EquipDataListModel> dataList = new ArrayList<>();
        for (Map.Entry<String, ArrayList<EquipLibraryReceModel.EquipDataListModel>> mapData : equipMap.entrySet()) {
            dataList.addAll(mapData.getValue());
        }
        EquipTabModel tabModel1 = new EquipTabModel(0, "全部");
        tabModels.add(tabModel1);
        EquipCommonFragment equipCommonFragment = new EquipCommonFragment(dataList,tabModel1.getTitle());
        tabFragments.add(equipCommonFragment);

        //拆分不同类型装备
        for (Map.Entry<String, ArrayList<EquipLibraryReceModel.EquipDataListModel>> mapData : equipMap.entrySet()) {
            String tabTitle = mapData.getKey();
            EquipCommonFragment equipCommonFragment1 = new EquipCommonFragment(mapData.getValue(),tabTitle);
            EquipTabModel tabModel = new EquipTabModel(0, tabTitle);
            tabModels.add(tabModel);
            tabFragments.add(equipCommonFragment1);
        }
        viewPagerAdapter.notifyDataSetChanged();
        for (int i = 0; i < this.binding.tabLayout.getTabCount(); i++) {
            TabLayout.Tab tabAt = this.binding.tabLayout.getTabAt(i);
            View view = LayoutInflater.from(getContext()).inflate(R.layout.tab_item, null);
            ((TextView) view.findViewById(R.id.tab)).setText(tabModels.get(i).getTitle());
            tabAt.setCustomView(view);
        }

        if (this.binding.tabLayout.getTabCount() < 4) {
            this.binding.tabLayout.setTabMode(TabLayout.MODE_FIXED);
            this.binding.tabLayout.setTabGravity(TabLayout.GRAVITY_CENTER);
        }


    }

    /**
     * 请求数据失败回调
     */
    @Override
    public void onFailed(String msg) {
        ToastUtil.show(getActivity(),msg);
    }

    /**
     * 没有网络状态回调
     */
    @Override
    public void noNetWork() {
        ToastUtil.show(getActivity(),"当前无网络");
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

    @Override
    public void onDestroyView() {
        if(presenter != null){
            presenter.onDestory();
        }
        EventBus.getDefault().unregister(this);
        super.onDestroyView();
    }

}
