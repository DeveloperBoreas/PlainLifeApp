package com.boreas.plainlife.mvp.views.activitys;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Point;
import android.os.Bundle;
import android.util.Pair;
import android.view.Display;

import com.amap.api.maps.AMap;
import com.amap.api.maps.CameraUpdateFactory;
import com.amap.api.maps.model.BitmapDescriptor;
import com.amap.api.maps.model.BitmapDescriptorFactory;
import com.amap.api.maps.model.CameraPosition;
import com.amap.api.maps.model.LatLng;
import com.amap.api.maps.model.LatLngBounds;
import com.amap.api.maps.model.Marker;
import com.amap.api.maps.model.MarkerOptions;
import com.amap.api.maps.model.Polyline;
import com.amap.api.maps.model.PolylineOptions;
import com.amap.api.maps.utils.SpatialRelationUtil;
import com.amap.api.maps.utils.overlay.MovingPointOverlay;
import com.boreas.plainlife.App;
import com.boreas.plainlife.Constant;
import com.boreas.plainlife.ObjectPool;
import com.boreas.plainlife.R;
import com.boreas.plainlife.base.BaseActivity;
import com.boreas.plainlife.databinding.ActivityLocationMapBinding;
import com.boreas.plainlife.internal.components.DaggerLocationMapActivityComponent;
import com.boreas.plainlife.internal.modules.LocationMapActivityModule;
import com.boreas.plainlife.mvp.presenters.presenterimpl.LocationMapActivityPresenter;
import com.boreas.plainlife.mvp.views.viewinterfaces.LocationMapActivityInterface;
import com.boreas.plainlife.utils.RxTimer;
import com.boreas.plainlife.utils.ToastUtil;
import com.orhanobut.logger.Logger;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.inject.Inject;

public class LocationMapActivity extends BaseActivity<ActivityLocationMapBinding> implements LocationMapActivityInterface {

    private AMap aMap;
    private Long uid = -1L;
    private int[] colors = new int[]{Color.argb(255, 0, 255, 0), Color.argb(255, 255, 255, 0), Color.argb(255, 255, 0, 0)};
    private List<Integer> colorList = new ArrayList<Integer>();
    //用一个数组来存放纹理
    private List<BitmapDescriptor> textureList = new ArrayList<>();
    private List<BitmapDescriptor> bitmapDescriptors = new ArrayList<>();
    private Polyline mPolyline;
    private MovingPointOverlay smoothMarker;
    private Marker marker;
    private static final int TotalDuration1 = 180;
    private static final int TotalDuration2 = 280;
    private static final int TotalDuration3 = 380;
    private static final int TotalDuration4 = 480;
    private static final int TotalDuration5 = 580;
    private static final int TotalDuration6 = 680;
    private static final int TotalDuration7 = 780;
    private static final int TotalDuration8 = 880;
    private int cameraTime = 0;
    @Inject
    LocationMapActivityPresenter presenter;
    @Inject
    ObjectPool objectPool;

    @Override
    public void handlerJumpBundle(Bundle bundle) {
        this.binding.map.onCreate(bundle);// 此方法必须重写
    }

    @Override
    public void handlerJumpData(Intent intent) {
        super.handlerJumpData(intent);
        this.uid = intent.getLongExtra(Constant.USER_UID, this.objectPool.getUserInfo().getUser().getUserId());
    }

    @Override
    public int setContentView() {
        return R.layout.activity_location_map;
    }

    @Override
    protected void initView() {
        textureList.add(BitmapDescriptorFactory.fromResource(R.mipmap.guidline));
        this.aMap = this.binding.map.getMap();
    }

    @Override
    protected void initListener() {

    }

    @Override
    protected void initComponent() {
        DaggerLocationMapActivityComponent.builder()
                .appComponent(App.getInstance().getmAppComponent())
                .netComponent(App.getInstance().getmNetComponent())
                .beansComponent(App.getInstance().getmBeansComponent())
                .locationMapActivityModule(new LocationMapActivityModule(this))
                .build().inject(this);
    }

    @Override
    protected void initData() {
        this.presenter.queryUserLocation(this.uid);
    }

    @Override
    protected void onResume() {
        super.onResume();
        this.binding.map.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        this.binding.map.onPause();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        this.binding.map.onSaveInstanceState(outState);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        this.binding.map.onDestroy();
    }

    @Override
    public void onSuccess(ArrayList<LatLng> tracks) {
        Logger.e("长度：" + tracks.size());
        Random random = new Random();
        for (int i = 0; i < tracks.size(); i++) {
            colorList.add(colors[random.nextInt(3)]);
            bitmapDescriptors.add(textureList.get(0));

        }
        this.setGuidLinePolyLine(tracks);
        RxTimer rxTimer = new RxTimer();
        rxTimer.timer(1000, number -> this.startPolyLineAnim(tracks));
    }

    private void setGuidLinePolyLine(ArrayList<LatLng> tracks) {
        mPolyline = this.aMap.addPolyline(
                new PolylineOptions().setCustomTexture(BitmapDescriptorFactory.fromResource(R.mipmap.guidline))
                        //setCustomTextureList(bitmapDescriptors)
//				        .setCustomTextureIndex(texIndexList)
                        .addAll(tracks)
                        .setUseTexture(true)
                        .useGradient(true)
                        .width(20)
                        .zIndex(100));
//        LatLngBounds.Builder builder = new LatLngBounds.Builder();
//        builder.include(tracks.get(0));
////        builder.include(tracks.get(tracks.size() - 2));
//        this.aMap.animateCamera(CameraUpdateFactory.newLatLngBounds(builder.build(), 100));
        aMap.moveCamera(CameraUpdateFactory.newCameraPosition(new CameraPosition(tracks.get(0), 17, 30, 0)));
//        aMap.addMarker(new MarkerOptions().position(tracks.get(0)).icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED)));
    }

    private void startPolyLineAnim(ArrayList<LatLng> tracks) {
        // 实例 MovingPointOverlay 对象
        if (smoothMarker == null) {
            // 设置 平滑移动的 图标
            marker = this.aMap.addMarker(new MarkerOptions().icon(BitmapDescriptorFactory.fromResource(R.mipmap.icon_car)).anchor(0.5f, 0.5f));
            smoothMarker = new MovingPointOverlay(this.aMap, marker);
        }
        LatLng drivePoint = tracks.get(0);
        Pair<Integer, LatLng> pair = SpatialRelationUtil.calShortestDistancePoint(tracks, drivePoint);
        tracks.set(pair.first, drivePoint);
        List<LatLng> subList = tracks.subList(pair.first, tracks.size());
        // 设置轨迹点
        smoothMarker.setPoints(subList);
        // 设置平滑移动的总时间  单位  秒
        smoothMarker.setTotalDuration(this.handlerTotalDuration(tracks.size()));
        // 设置移动的监听事件  返回 距终点的距离  单位 米
//        smoothMarker.setMoveListener(distance -> {
//            try {
//                runOnUiThread(() -> {
//                    aMap.moveCamera(CameraUpdateFactory.newCameraPosition(new CameraPosition(smoothMarker.getPosition(), 17, 30, 0)));
//                });
//            } catch (Throwable e) {
//                e.printStackTrace();
//            }
//        });
        // 开始移动
        smoothMarker.startSmoothMove();
    }

    private int handlerTotalDuration(int size) {
        if (size > 0 && size < 200) {
            return TotalDuration1;
        } else if (size > 200 && size < 400) {
            return TotalDuration2;
        } else if (size > 400 && size < 600) {
            return TotalDuration3;
        } else if (size > 600 && size < 800) {
            return TotalDuration4;
        } else if (size > 800 && size < 1000) {
            return TotalDuration5;
        } else if (size > 1000 && size < 1200) {
            return TotalDuration6;
        } else if (size > 1200 && size < 1400) {
            return TotalDuration7;
        } else {
            return TotalDuration8;
        }
    }

    @Override
    public void onFailed(String message) {
        ToastUtil.show(this, message);
    }

    @Override
    public void noNetWork() {
        ToastUtil.show(this, "暂无网络！");
    }

    @Override
    public void noData() {
        ToastUtil.show(this, "暂无数据！");
    }

    @Override
    public void onDisLoadingDialog() {
        this.dismissLoadingDialog();
    }

    @Override
    public void onShowLoadingDialog() {
        this.showLoadingDialog();
    }

    @Override
    public void onReStart() {

    }
}
