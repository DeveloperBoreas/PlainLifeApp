package com.boreas.plainlife.mvp.views.fragments.location;

import androidx.recyclerview.widget.LinearLayoutManager;

import com.boreas.plainlife.App;
import com.boreas.plainlife.R;
import com.boreas.plainlife.base.BaseFragment;
import com.boreas.plainlife.databinding.FragmentLocationLovepeopleBinding;
import com.boreas.plainlife.framwork.ClickProxy;
import com.boreas.plainlife.internal.components.DaggerLocationLoveFragmentComponent;
import com.boreas.plainlife.internal.modules.LocationLoveFragmentModule;
import com.boreas.plainlife.mvp.models.location.LocationUserListModel;
import com.boreas.plainlife.mvp.presenters.presenterimpl.LocationLoveFragmentPresenter;
import com.boreas.plainlife.mvp.views.viewinterfaces.LocationLoveFragmentInterface;
import com.boreas.plainlife.utils.ToastUtil;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

public class LocationLoveFragment extends BaseFragment<FragmentLocationLovepeopleBinding> implements LocationLoveFragmentInterface {
    private List<LocationUserListModel.Data> userList = new ArrayList<>();
    @Inject
    LocationLoveFragmentPresenter presenter;
    private LovePeopleAdapter adapter;

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
        this.binding.headLayout.headTitle.setText(getString(R.string.ilove_people));
        this.binding.list.setHasFixedSize(false);
        this.binding.list.setLayoutManager(new LinearLayoutManager(getContext()));
        this.adapter = (LovePeopleAdapter) new LovePeopleAdapter(getContext(),userList,R.layout.location_lovepeople_item).setIsShowFooter((int) getResources().getDimension(R.dimen.main_bottomNavigtion_height_50));
        this.binding.list.setAdapter( this.adapter);
    }

    @Override
    public void initListener() {
        this.binding.addLove.setOnClickListener(new ClickProxy(v -> {
            presenter.queryBindUsers();
//            Intent intent = new Intent(getActivity(), AddLoveActivity.class);
//            startActivity(intent);
        }));

    }

    @Override
    public void initComponent() {
        DaggerLocationLoveFragmentComponent.builder()
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
        ToastUtil.show(getContext(),message);
    }

    @Override
    public void noNetWork() {
        ToastUtil.show(getContext(),"暂无网络");
    }

    @Override
    public void noData() {
        ToastUtil.show(getContext(),"暂无数据");
    }

    @Override
    public void onDisLoadingDialog() {
        super.dismissLoadingDialog();
    }

    @Override
    public void onShowLoadingDialog() {
        super.showLoadingDialog();
    }
}
