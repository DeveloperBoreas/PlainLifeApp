package com.boreas.plainlife.mvp.views.fragments.location;

import android.annotation.TargetApi;
import android.os.Build;
import android.widget.CheckBox;

import com.amap.api.maps.AMap;
import com.amap.api.maps.MapFragment;
import com.amap.api.maps.UiSettings;
import com.amap.api.maps.model.MyLocationStyle;
import com.boreas.plainlife.R;
import com.boreas.plainlife.base.BaseFragment;

public class LocationMapFragment extends BaseFragment {
    private AMap mMap;
    private UiSettings uiSettings;

    @Override
    public void lazyFetchData() {

    }

    @Override
    public int setContent() {
        return R.layout.fragment_location_layout;
    }

    @Override
    public void initView() {
        setUpMapIfNeeded();
    }

    @Override
    public void onResume() {
        super.onResume();
        setUpMapIfNeeded();
    }

    @Override
    public void initListener() {

    }

    @Override
    public void initComponent() {

    }

    @Override
    public boolean onBackPressed() {
        return false;
    }

    /**
     * 获取Amap 对象
     */
    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    private void setUpMapIfNeeded() {
        if (this.mMap == null) {
            this.mMap = ((MapFragment) getActivity().getFragmentManager().findFragmentById(R.id.map)).getMap();
            this.uiSettings = this.mMap.getUiSettings();
            this.uiSettings.setCompassEnabled(true);//指南针是否显示
            this.uiSettings.setScaleControlsEnabled(true);//比例尺
        }
    }

    //切换地图样式   标准和夜晚
    private void changMapStyle() {
        if (this.mMap != null) {
            this.mMap.setMapType(AMap.MAP_TYPE_NIGHT); //夜晚
            this.mMap.setMapType(AMap.MAP_TYPE_NORMAL); //普通
        }
    }
    //定位类型
    private void setType(){
        MyLocationStyle myLocationStyle = new MyLocationStyle();
        mMap.setMyLocationStyle(myLocationStyle);

        mMap.setMyLocationEnabled(true);// 设置为true表示显示定位层并可触发定位，false表示隐藏定位层并不可触发定位，默认是false
        // 只定位，不进行其他操作
        mMap.setMyLocationStyle(myLocationStyle.myLocationType(MyLocationStyle.LOCATION_TYPE_SHOW));
        mMap.setMyLocationStyle(myLocationStyle.myLocationType(MyLocationStyle.LOCATION_TYPE_LOCATE));
        // 设置定位的类型为 跟随模式
        mMap.setMyLocationStyle(myLocationStyle.myLocationType(MyLocationStyle.LOCATION_TYPE_FOLLOW));
        // 设置定位的类型为根据地图面向方向旋转
        mMap.setMyLocationStyle(myLocationStyle.myLocationType(MyLocationStyle.LOCATION_TYPE_MAP_ROTATE));
        // 定位、且将视角移动到地图中心点，定位点依照设备方向旋转，  并且会跟随设备移动。
        mMap.setMyLocationStyle(myLocationStyle.myLocationType(MyLocationStyle.LOCATION_TYPE_LOCATION_ROTATE));
        // 定位、但不会移动到地图中心点，并且会跟随设备移动。
        mMap.setMyLocationStyle(myLocationStyle.myLocationType(MyLocationStyle.LOCATION_TYPE_FOLLOW_NO_CENTER));
        // 定位、但不会移动到地图中心点，地图依照设备方向旋转，并且会跟随设备移动。
        mMap.setMyLocationStyle(myLocationStyle.myLocationType(MyLocationStyle.LOCATION_TYPE_MAP_ROTATE_NO_CENTER));
        // 定位、但不会移动到地图中心点，定位点依照设备方向旋转，并且会跟随设备移动。
        mMap.setMyLocationStyle(myLocationStyle.myLocationType(MyLocationStyle.LOCATION_TYPE_LOCATION_ROTATE_NO_CENTER));
    }
}
