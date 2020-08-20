package com.boreas.plainlife.mvp.views.activitys;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.text.TextUtils;
import android.util.Base64;
import android.view.View;
import android.widget.Toast;

import com.boreas.plainlife.App;
import com.boreas.plainlife.R;
import com.boreas.plainlife.base.BaseActivity;
import com.boreas.plainlife.databinding.ActivityLoginBinding;
import com.boreas.plainlife.framwork.ClickProxy;
import com.boreas.plainlife.internal.components.DaggerLoginActivityComponent;
import com.boreas.plainlife.internal.modules.LoginActivityModule;
import com.boreas.plainlife.mq.RabbitMQConfiguration;
import com.boreas.plainlife.mvp.models.login.CaptchatModel;
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
    private CaptchatModel captchatModel;

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
                String verCode  = this.binding.verCode.getText().toString().trim();
                loginActivityPresenter.requestLogin(userName, password,verCode,captchatModel.getUuid());
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
        this.binding.verCodeImg.setOnClickListener(new ClickProxy(v -> {
            loginActivityPresenter.requestCaptchatImg();
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
        if (TextUtils.isEmpty(this.binding.verCode.getText().toString().trim())) {
            ToastUtil.show(this, "请输入验证码");
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
        loginActivityPresenter.requestCaptchatImg();
    }

    @Override
    public void onSuccess(String s) {

    }

    @Override
    public void onCaptchatSuccess(CaptchatModel captchatModel) {
        this.captchatModel = captchatModel;
        byte[] decodedString = Base64.decode(captchatModel.getImg(), Base64.DEFAULT);
        Bitmap decodedByte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
        this.binding.verCodeImg.setImageBitmap(decodedByte);
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
