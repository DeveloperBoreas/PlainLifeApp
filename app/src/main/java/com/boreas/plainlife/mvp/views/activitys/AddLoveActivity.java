package com.boreas.plainlife.mvp.views.activitys;

import com.boreas.plainlife.App;
import com.boreas.plainlife.R;
import com.boreas.plainlife.base.BaseActivity;
import com.boreas.plainlife.databinding.ActivityAddLoveBinding;
import com.boreas.plainlife.framwork.ClickProxy;
import com.boreas.plainlife.internal.components.DaggerAddLoceActivityComponent;
import com.boreas.plainlife.internal.modules.AddLoveActivityModule;
import com.boreas.plainlife.mvp.models.location.LocationUserModel;
import com.boreas.plainlife.mvp.presenters.presenterimpl.AddLoveActivityPresenter;
import com.boreas.plainlife.mvp.views.viewinterfaces.AddLoveActivityInterface;
import com.boreas.plainlife.utils.ImageUtil;
import com.boreas.plainlife.utils.ToastUtil;

import javax.inject.Inject;

public class AddLoveActivity extends BaseActivity<ActivityAddLoveBinding> implements AddLoveActivityInterface {

    @Inject
    AddLoveActivityPresenter presenter;
    @Override
    public int setContentView() {
        return R.layout.activity_add_love;
    }

    @Override
    protected void initView() {
        this.binding.headLayout.headTitle.setText(getString(R.string.addLovePeople));
    }

    @Override
    protected void initListener() {
        this.binding.query.setOnClickListener(new ClickProxy(v -> {
            String phone = this.binding.phone.getText().toString().trim();
            presenter.queryUserByPhone(phone);
        }));
        this.binding.bind.setOnClickListener(new ClickProxy(v -> {
            String phone = this.binding.phone.getText().toString().trim();
            presenter.bindUserByPhone(phone);
        }));
    }

    @Override
    protected void initComponent() {
        DaggerAddLoceActivityComponent.builder()
                .addLoveActivityModule(new AddLoveActivityModule(this))
                .appComponent(App.getInstance().getmAppComponent())
                .netComponent(App.getInstance().getmNetComponent())
                .build().inject(this);
    }

    @Override
    protected void initData() {

    }

    @Override
    public void onSuccess(LocationUserModel.Data locationUserModel) {
        ImageUtil.loadImg(this,locationUserModel.getAvatar(),this.binding.userIcon);
        this.binding.userName.setText(locationUserModel.getNickName());
        this.binding.userSex.setText(locationUserModel.getSex());
        this.binding.userPhone.setText(locationUserModel.getPhonenumber());
        this.binding.userEmail.setText(locationUserModel.getEmail());
    }

    @Override
    public void onBindSuccess(String msg) {
        ToastUtil.show(this,msg);
    }

    @Override
    public void onFailed(String message) {
        ToastUtil.show(this,message);
    }

    @Override
    public void noNetWork() {
        ToastUtil.show(this,"当前无网络");
    }

    @Override
    public void noData() {
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
