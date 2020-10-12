package com.boreas.plainlife.mvp.views.fragments.location;

import android.content.Intent;

import androidx.recyclerview.widget.LinearLayoutManager;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.amap.api.location.AMapLocation;
import com.boreas.plainlife.App;
import com.boreas.plainlife.Constant;
import com.boreas.plainlife.Location.LocationListener;
import com.boreas.plainlife.Location.LocationService;
import com.boreas.plainlife.ObjectPool;
import com.boreas.plainlife.R;
import com.boreas.plainlife.base.BaseFragment;
import com.boreas.plainlife.databinding.FragmentLocationLovepeopleBinding;
import com.boreas.plainlife.framwork.ClickProxy;
import com.boreas.plainlife.internal.components.DaggerLocationLoveFragmentComponent;
import com.boreas.plainlife.internal.modules.LocationLoveFragmentModule;
import com.boreas.plainlife.mvp.models.location.LocationUserListModel;
import com.boreas.plainlife.mvp.presenters.presenterimpl.LocationLoveFragmentPresenter;
import com.boreas.plainlife.mvp.views.activitys.AddLoveActivity;
import com.boreas.plainlife.mvp.views.activitys.LocationMapActivity;
import com.boreas.plainlife.mvp.views.fragments.LocationFragment;
import com.boreas.plainlife.mvp.views.viewinterfaces.LocationLoveFragmentInterface;
import com.boreas.plainlife.utils.ToastUtil;
import com.boreas.plainlife.websocket.PlainMessage;
import com.boreas.plainlife.websocket.WebSocketListener;
import com.boreas.plainlife.websocket.WebSocketManger;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

public class LocationLoveFragment extends BaseFragment<FragmentLocationLovepeopleBinding> implements LocationLoveFragmentInterface, WebSocketListener, LocationListener {

    private List<LocationUserListModel.Data> userList = new ArrayList<>();
    private LovePeopleAdapter adapter;

    @Inject
    LocationLoveFragmentPresenter presenter;
    @Inject
    WebSocketManger webSocketManger;
    private LocationFragment locationFragment;

    @Override
    public void lazyFetchData() {
        presenter.queryBindUsers();
    }

    @Override
    public int setContent() {
        return R.layout.fragment_location_lovepeople;
    }

    @Override
    public void initView() {
        this.locationFragment = (LocationFragment) getParentFragment();
        this.webSocketManger.registerSocketListener(this);
        this.binding.headLayout.headTitle.setText(getString(R.string.ilove_people));
        this.binding.list.setHasFixedSize(false);
        this.binding.list.setLayoutManager(new LinearLayoutManager(getContext()));
        this.adapter = (LovePeopleAdapter) new LovePeopleAdapter(getContext(), userList, R.layout.location_lovepeople_item).setIsShowFooter((int) getResources().getDimension(R.dimen.main_bottomNavigtion_height_50));
        this.binding.list.setAdapter(this.adapter);
    }

    @Override
    public void initListener() {
        this.binding.addLove.setOnClickListener(new ClickProxy(v -> {
            Intent intent = new Intent(getActivity(), AddLoveActivity.class);
            startActivity(intent);
        }));
        this.adapter.setOnItemClickListener((parent, view, position) -> {
            LocationUserListModel.Data data = this.adapter.getListData().get(position);
            Intent intent = new Intent(getActivity(), LocationMapActivity.class);
            intent.putExtra(Constant.USER_UID, data.getUserId());
            startActivity(intent);
        });
        LocationService.getInstance().registerLocationListener(this);
    }

    @Override
    public void initComponent() {
        DaggerLocationLoveFragmentComponent.builder()
                .beansComponent(App.getInstance().getmBeansComponent())
                .locationLoveFragmentModule(new LocationLoveFragmentModule(this))
                .netComponent(App.getInstance().getmNetComponent())
                .build().inject(this);
    }

    @Override
    public boolean onBackPressed() {
        return false;
    }

    @Override
    public void onQueryBindUserSuccess(ArrayList<LocationUserListModel.Data> userList) {
        this.adapter.reset(userList);
    }

    @Override
    public void onFailed(String message) {
        ToastUtil.show(getContext(), message);
    }

    @Override
    public void noNetWork() {
        ToastUtil.show(getContext(), "暂无网络");
    }

    @Override
    public void noData() {
        ToastUtil.show(getContext(), "暂无数据");
    }

    @Override
    public void onDisLoadingDialog() {
        super.dismissLoadingDialog();
    }

    @Override
    public void onShowLoadingDialog() {
        super.showLoadingDialog();
    }

    @Override
    public void onReStart() {

    }

    @Override
    public void onMessage(String message) {
        if (!message.contains(PlainMessage.ONLINE_EVENT)) {
            return;
        }
        JSONObject jsonObject = JSONObject.parseObject(message);
        JSONArray dataList = (JSONArray) jsonObject.get(PlainMessage.DATA);
        List<LocationUserListModel.Data> adapterListData = this.adapter.getListData();
        for (int i = 0, size = dataList.size(); i < size; i++) {
            JSONObject object = dataList.getJSONObject(i);
            long uid = object.getLong(PlainMessage.USERID);
            boolean isOnline = object.getBoolean(PlainMessage.ISONLINE);
            for (LocationUserListModel.Data data : adapterListData) {
                if (data.getUserId() == uid) {
                    data.setOnline(isOnline);
                    break;
                }
            }
        }
        getActivity().runOnUiThread(() -> {
            this.adapter.notifyDataSetChanged();
        });
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        this.webSocketManger.unRegisterSocketListener(this);
        LocationService.getInstance().unregisterLocationListener(this);
    }

    @Override
    public void onLocationSuccess(AMapLocation location) {
        List<LocationUserListModel.Data> adapterListData = this.adapter.getListData();
        for (LocationUserListModel.Data data : adapterListData) {
            if (data.getUserId() == presenter.getUserid()) {
                data.setLocation(location.getAddress());
                break;
            }
        }
        getActivity().runOnUiThread(() -> {
            this.adapter.notifyDataSetChanged();
        });
    }
}
