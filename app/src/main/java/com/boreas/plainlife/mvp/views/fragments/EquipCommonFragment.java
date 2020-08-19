package com.boreas.plainlife.mvp.views.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.boreas.plainlife.App;
import com.boreas.plainlife.R;
import com.boreas.plainlife.adapter.CommonEquioDevicesAdapter;
import com.boreas.plainlife.base.BaseFragment;
import com.boreas.plainlife.base.adapter.BaseRecyclerAdapter;
import com.boreas.plainlife.databinding.FragmentEquipCommonBinding;

import com.boreas.plainlife.internal.components.DaggerEquipCommonFragmentComponect;
import com.boreas.plainlife.internal.modules.EquipCommonFragmentModule;
import com.boreas.plainlife.mvp.models.equipLib.EquipLibraryReceModel;
import com.boreas.plainlife.mvp.presenters.presenterimpl.EquipCommonFragmentPresenter;
import com.boreas.plainlife.mvp.views.activitys.DeviceDescActivity;
import com.boreas.plainlife.mvp.views.viewinterfaces.EquipCommonFragmentInterface;
import com.boreas.plainlife.utils.ToastUtil;

import java.util.ArrayList;

import javax.inject.Inject;


public class EquipCommonFragment extends BaseFragment<FragmentEquipCommonBinding> implements BaseRecyclerAdapter.OnItemClickListener, EquipCommonFragmentInterface, CommonEquioDevicesAdapter.OnAddClickListener {
    @Inject
    EquipCommonFragmentPresenter equipCommonFragmentPresenter;

    private ArrayList<EquipLibraryReceModel.EquipDataListModel> deviceModels;
    private ArrayList<EquipLibraryReceModel.EquipDataListModel> equipDataListModels;



    public EquipCommonFragment(ArrayList<EquipLibraryReceModel.EquipDataListModel> equipDataListModels,String name) {
        super(name);
        this.equipDataListModels = equipDataListModels;
    }

    @Override
    public void lazyFetchData() {

    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public int setContent() {
        return R.layout.fragment_equip_common;
    }

    @Override
    public void initView() {
        this.deviceModels = new ArrayList<>();
        this.testData();
        this.binding.contentList.setHasFixedSize(false);
        this.binding.contentList.setLayoutManager(new GridLayoutManager(getActivity(), 2));
        CommonEquioDevicesAdapter commonEquioDevicesAdapter = (CommonEquioDevicesAdapter) new CommonEquioDevicesAdapter(
                getActivity(), this.deviceModels,
                R.layout.common_equipdevice_item)
                .setIsShowFooter((int) getResources().getDimension(R.dimen.main_bottomNavigtion_height_50));
        commonEquioDevicesAdapter.setOnItemClickListener(this);
        commonEquioDevicesAdapter.setOnAddClickListener(this);
        this.binding.contentList.setAdapter(commonEquioDevicesAdapter);
    }

    private void testData() {
        if(equipDataListModels!=null&&equipDataListModels.size()>0){
            deviceModels.addAll(equipDataListModels);
        }
    }

    @Override
    public void initListener() {

    }

    @Override
    public void initComponent() {
        DaggerEquipCommonFragmentComponect.builder()
                .netComponent(App.getInstance().getmNetComponent())
                .equipCommonFragmentModule(new EquipCommonFragmentModule(this))
                .build().inject(this);
    }

    @Override
    public boolean onBackPressed() {
        return false;
    }

    //跳转到详情页
    @Override
    public void onItemClick(RecyclerView parent, View view, int position) {
        Intent intent = new Intent(getActivity(), DeviceDescActivity.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable("data",deviceModels.get(position));
        intent.putExtra("bundle", bundle);
        startActivity(intent);
    }
    //添加到购物车点击事件
    @Override
    public void onAddClick(EquipLibraryReceModel.EquipDataListModel equipDataListModel) {
//        if(equipDataListModel.getStatus()==1){
//            equipCommonFragmentPresenter.requestAddShoppingCart(equipDataListModel);
//        }else{
//            ToastUtil.show(getActivity(),"当前装备已借出");
//        }
        equipCommonFragmentPresenter.requestAddShoppingCart(equipDataListModel);
    }

    @Override
    public void onSuccess(String msg) {
        ToastUtil.show(getActivity(),msg);
    }

    @Override
    public void onFailed(String msg) {
        ToastUtil.show(getActivity(),msg);
    }

    @Override
    public void noNetWork() {
        ToastUtil.show(getActivity(),"当前无网络");
    }

    @Override
    public void noData() {

    }

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
