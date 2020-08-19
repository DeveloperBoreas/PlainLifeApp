package com.boreas.plainlife.mvp.views.activitys;

import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.widget.Toast;

import com.boreas.plainlife.App;
import com.boreas.plainlife.Constant;
import com.boreas.plainlife.R;
import com.boreas.plainlife.base.BaseActivity;
import com.boreas.plainlife.databinding.ActivityLoginBinding;
import com.boreas.plainlife.framwork.ClickProxy;
import com.boreas.plainlife.internal.components.DaggerLoginActivityComponent;
import com.boreas.plainlife.internal.modules.LoginActivityModule;
import com.boreas.plainlife.mq.RabbitMQConfiguration;
import com.boreas.plainlife.mvp.models.login.AccessModel;
import com.boreas.plainlife.mvp.models.login.LoginReceModel;
import com.boreas.plainlife.mvp.presenters.presenterimpl.LoginActivityPresenter;
import com.boreas.plainlife.mvp.views.viewinterfaces.LoginActivityInterface;
import com.boreas.plainlife.utils.PreUtil;
import com.boreas.plainlife.utils.RegExpValidatorUtil;
import com.boreas.plainlife.utils.SoftKeyboardUtil;
import com.boreas.plainlife.utils.ToastUtil;

import javax.inject.Inject;


public class LoginActivity extends BaseActivity<ActivityLoginBinding> implements LoginActivityInterface {

    @Inject
    LoginActivityPresenter loginActivityPresenter;


    @Override
    public int setContentView() {
        return R.layout.activity_login;
    }


    @Override
    protected void initView() {
        super.setSwipeBackEnable(false);
    }

    @Override
    protected void initListener() {
        this.binding.login.setOnClickListener(new ClickProxy(v -> {
            if (this.verParams()) {
                String userName = this.binding.userName.getText().toString().trim();
                String password = this.binding.userPsd.getText().toString().trim();
                loginActivityPresenter.requestData(userName, password);
            }
        }));
        this.binding.logo.setOnClickListener(v -> {
            this.continuousClick();
        });
        this.binding.saveIp.setOnClickListener(new ClickProxy(v -> {
            if(TextUtils.isEmpty(this.binding.inputIp.getText().toString())){
                ToastUtil.show(this,"输入的IP不能未空");
                return;
            }
            if (!RegExpValidatorUtil.regExpIp(this.binding.inputIp.getText().toString())) {
                Toast.makeText(this, "请输入正确格式的IP地址", Toast.LENGTH_LONG).show();
                return;
            }
            SoftKeyboardUtil.hideSoftKeyboard(this);
            this.binding.ipContent.setVisibility(View.GONE);
            PreUtil.put("IP",this.binding.inputIp.getText().toString());
        }));
    }

    final static int COUNTS = 7;// 点击次数
    final static long DURATION = 1000;// 规定有效时间
    long[] mHits = new long[COUNTS];

    private void continuousClick() {
        //每次点击时，数组向前移动一位
        System.arraycopy(mHits, 1, mHits, 0, mHits.length - 1);
        //为数组最后一位赋值
        mHits[mHits.length - 1] = System.currentTimeMillis();
//        Logger.e("mHits[0] :" + mHits[0] + "\t\t" + (System.currentTimeMillis() - DURATION));
        if (mHits[0] >= (System.currentTimeMillis() - DURATION)) {
            mHits = new long[COUNTS];//重新初始化数组
//            Toast.makeText(this, "连续点击了5次", Toast.LENGTH_LONG).show();
            this.binding.ipContent.setVisibility(View.VISIBLE);
        }
    }

    private boolean verParams() {
        if (TextUtils.isEmpty(this.binding.userName.getText().toString().trim())) {
            ToastUtil.show(this, "请输入用户名");
            return false;
        }
        if (TextUtils.isEmpty(this.binding.userPsd.getText().toString().trim())) {
            ToastUtil.show(this, "请输入密码");
            return false;
        }
        return true;
    }

    @Override
    protected void initComponent() {
        DaggerLoginActivityComponent.builder()
                .appComponent(App.getInstance().getmAppComponent())
                .netComponent(App.getInstance().getmNetComponent())
                .loginActivityModule(new LoginActivityModule(this))
                .build().inject(this);
    }

    @Override
    protected void initData() {
        RabbitMQConfiguration rabbitMQConfiguration = RabbitMQConfiguration.getInstance();
        rabbitMQConfiguration.init(getApplicationContext());
    }

    @Override
    public void onSuccess(LoginReceModel s) {

    }

    /**
     * 查询权限成功回调
     *
     * @param accessModel
     */
    @Override
    public void onAccessSuccess(AccessModel accessModel) {
        if(true){
            Intent mainIntent = new Intent(this, MainActivity.class);
            mainIntent.putExtra(Constant.ROLE, Constant.OPERATOR_ROLE);
            startActivity(mainIntent);
            App.getInstance().role = Constant.OPERATOR_ROLE;
            finish();
            return;
        }
        if (accessModel.getData().getLevel1() != null && accessModel.getData().getLevel2() != null) {
            AccessModel.DataBean.Level1Bean level1 = accessModel.getData().getLevel1();
            AccessModel.DataBean.Level2Bean level2 = accessModel.getData().getLevel2();
            Intent mainIntent = new Intent(this, MainActivity.class);
            mainIntent.putExtra(Constant.ROLE, Constant.OPERATOR_ROLE);
            startActivity(mainIntent);
            App.getInstance().role = Constant.OPERATOR_ROLE;
            finish();
            return;
//            if (level1.is操作终端()) {
//                Intent mainIntent = new Intent(this, MainActivity.class);
//                if (level2.is操作人员() && level2.is库管人员()) {
//                    Intent intent = new Intent(this, SwitchRoleActivity.class);
//                    startActivity(intent);
//                    finish();
//                } else if (level2.is库管人员()) {
//                    mainIntent.putExtra(Constant.ROLE, Constant.KEEPER_ROLE);
//                    startActivity(mainIntent);
//                    App.getInstance().role = Constant.KEEPER_ROLE;
//                    finish();
//                } else if (level2.is操作人员()) {
//                    mainIntent.putExtra(Constant.ROLE, Constant.OPERATOR_ROLE);
//                    startActivity(mainIntent);
//                    App.getInstance().role = Constant.OPERATOR_ROLE;
//                    finish();
//                }
//            }
        }
    }


    /**
     * 数据请求失败回调
     *
     * @param message
     */
    @Override
    public void onFailed(String message) {
        ToastUtil.show(this, message);
    }

    @Override
    public void noNetWork() {
        ToastUtil.show(this, "当前无网络");
    }

    @Override
    public void noData() {

    }

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
    protected void onDestroy() {
        super.onDestroy();
        if (loginActivityPresenter != null) {
            loginActivityPresenter.onDestory();
        }
    }
}
