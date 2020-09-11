package com.boreas.plainlife.mvp.views.fragments;

import com.amap.api.location.AMapLocation;
import com.amap.api.maps.AMap;
import com.amap.api.maps.MapView;
import com.boreas.plainlife.Location.LocationListener;
import com.boreas.plainlife.Location.LocationService;
import com.boreas.plainlife.R;
import com.boreas.plainlife.base.BaseFragment;
import com.boreas.plainlife.databinding.FragmentLocationParentlayoutBinding;
import com.boreas.plainlife.framwork.ClickProxy;
import com.boreas.plainlife.mq.RabbitMQConfiguration;
import com.boreas.plainlife.mq.ResqonCallBack;
import com.boreas.plainlife.mvp.views.fragments.location.LocationLoveFragment;
import com.boreas.plainlife.mvp.views.fragments.location.LocationMapFragment;
import com.boreas.plainlife.mvp.views.fragments.location.LocationSettingFragment;
import com.boreas.plainlife.mvp.views.fragments.location.ViewPagerAdapter;
import com.boreas.plainlife.utils.RxTimer;

import java.util.ArrayList;

public class LocationFragment extends BaseFragment<FragmentLocationParentlayoutBinding> implements LocationListener {

    private ArrayList<BaseFragment> fragments;

    @Override
    public void lazyFetchData() {

    }

    @Override
    public int setContent() {
        return R.layout.fragment_location_parentlayout;
    }

    @Override
    public void initView() {
        LocationService.getInstance().registerLocationListener(this);
        this.binding.viewPager.setOffscreenPageLimit(3);
        this.binding.viewPager.setAdapter(new ViewPagerAdapter(getActivity().getSupportFragmentManager(), this.addFragments()));

    }

    private ArrayList<BaseFragment> addFragments() {
        this.fragments = new ArrayList<>();
        this.fragments.add(new LocationLoveFragment());
        this.fragments.add(new LocationMapFragment());
        this.fragments.add(new LocationSettingFragment());
        return this.fragments;
    }

    @Override
    public void initListener() {
        this.binding.love.setOnClickListener(new ClickProxy(v -> {
            this.binding.viewPager.setCurrentItem(0);
        }, 100));
        this.binding.location.setOnClickListener(new ClickProxy(v -> {
            this.binding.viewPager.setCurrentItem(1);
        }, 100));
        this.binding.setting.setOnClickListener(new ClickProxy(v -> {
            this.binding.viewPager.setCurrentItem(2);
        }, 100));
    }

    @Override
    public void initComponent() {

    }

    @Override
    public boolean onBackPressed() {
        return false;
    }

    @Override
    public void onLocationSuccess(AMapLocation location) {

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (LocationService.getInstance() != null) {
            LocationService.getInstance().unregisterLocationListener(this);
        }
    }
}
