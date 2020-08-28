package com.boreas.plainlife.mvp.views.activitys;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.CountDownTimer;
import android.text.TextUtils;
import android.util.Base64;
import android.view.View;
import android.widget.Toast;

import com.boreas.plainlife.App;
import com.boreas.plainlife.Location.LocationService;
import com.boreas.plainlife.R;
import com.boreas.plainlife.base.BaseActivity;
import com.boreas.plainlife.databinding.ActivityLoginBinding;
import com.boreas.plainlife.framwork.ClickProxy;
import com.boreas.plainlife.internal.components.DaggerLoginActivityComponent;
import com.boreas.plainlife.internal.modules.LoginActivityModule;
import com.boreas.plainlife.mq.RabbitMQConfiguration;
import com.boreas.plainlife.mvp.models.login.CaptchatModel;
import com.boreas.plainlife.mvp.models.login.LoginReceModel;
import com.boreas.plainlife.mvp.models.login.UserRegisterParams;
import com.boreas.plainlife.mvp.presenters.presenterimpl.LoginActivityPresenter;
import com.boreas.plainlife.mvp.views.viewinterfaces.LoginActivityInterface;
import com.boreas.plainlife.utils.FrontAndBackView;
import com.boreas.plainlife.utils.PreUtil;
import com.boreas.plainlife.utils.RegExpValidatorUtil;
import com.boreas.plainlife.utils.RxTimer;
import com.boreas.plainlife.utils.SoftKeyboardUtil;
import com.boreas.plainlife.utils.ToastUtil;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;


public class LoginActivity extends BaseActivity<ActivityLoginBinding> implements LoginActivityInterface {

    private FrontAndBackView faceAndBackView;

    @Inject
    LoginActivityPresenter loginActivityPresenter;
    private CaptchatModel captchatModel;
    private CountDownTimer timer;

    @Override
    public int setContentView() {
        return R.layout.activity_login;
    }


    @Override
    protected void initView() {
        super.setSwipeBackEnable(false);
        this.startService(new Intent(this, LocationService.class));
        faceAndBackView = new FrontAndBackView(getApplicationContext(), this.binding.loginParent, this.binding.registerParent);
    }

    @Override
    protected void initListener() {
        //登陆界面
        this.binding.loginBtn.setOnClickListener(new ClickProxy(v -> {
            if (this.verLoginParams()) {
                String userName = this.binding.userName.getText().toString().trim();
                String password = this.binding.userPsd.getText().toString().trim();
                String verCode = this.binding.verCode.getText().toString().trim();
                loginActivityPresenter.requestLogin(userName, password, verCode, captchatModel.getUuid());
            }
        }));
//        this.binding.logo.setOnClickListener(v -> {
//            faceAndBackView.toggle();
////            this.continuousClick();
//        });
//        this.binding.saveIp.setOnClickListener(new ClickProxy(v -> {
//            if (TextUtils.isEmpty(this.binding.inputIp.getText().toString())) {
//                ToastUtil.show(this, "输入的IP不能未空");
//                return;
//            }
//            if (!RegExpValidatorUtil.regExpIp(this.binding.inputIp.getText().toString())) {
//                Toast.makeText(this, "请输入正确格式的IP地址", Toast.LENGTH_LONG).show();
//                return;
//            }
//            SoftKeyboardUtil.hideSoftKeyboard(this);
//            this.binding.ipContent.setVisibility(View.GONE);
//            PreUtil.put("IP", this.binding.inputIp.getText().toString());
//        }));
        this.binding.verCodeImg.setOnClickListener(new ClickProxy(v -> {
            loginActivityPresenter.requestCaptchatImg();
        }));

        this.binding.regist.setOnClickListener(new ClickProxy(v -> {
            faceAndBackView.toggle();
        }));
        this.binding.guset.setOnClickListener(new ClickProxy(v -> {

        }));
        //注册界面
        this.binding.verCodeRegister.setOnClickListener(new ClickProxy(v -> {
            if (TextUtils.isEmpty(this.binding.verPhoneRegister.getText().toString().trim())) {
                ToastUtil.show(this, "请输入手机号");
                return;
            }
            if (timer == null) {
                this.timer = new CountDownTimer(1000 * 60, 1000) {
                    @SuppressLint("DefaultLocale")
                    @Override
                    public void onTick(long millisUntilFinished) {
                        binding.verCodeRegister.setEnabled(false);
                        binding.verCodeRegister.setText(String.format("已发送(%d)", millisUntilFinished / 1000));
                    }

                    @Override
                    public void onFinish() {
                        binding.verCodeRegister.setEnabled(true);
                        binding.verCodeRegister.setText("重新获取");
                    }
                };
            }
            loginActivityPresenter.getApiService().sendSms(binding.verPhoneRegister.getText().toString().trim())
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(baseResponse -> {
                        this.onFailed(baseResponse.getMsg());
                        if(baseResponse.getCode() == 200){
                            ToastUtil.show(this,baseResponse.getMsg());
                            timer.start();
                        }
                    }, throwable -> {
                        ToastUtil.show(this,throwable.getMessage());
                    });
        }));
        this.binding.back.setOnClickListener(new ClickProxy(v -> faceAndBackView.toggle()));
        this.binding.login.setOnClickListener(new ClickProxy(v -> {
            if (this.verRegisterParams()) {
                loginActivityPresenter.requesrRegister(new UserRegisterParams(
                        this.binding.userNameRegister.getText().toString().trim(),
                        this.binding.userPsdRegister.getText().toString().trim(),
                        this.binding.verPhoneRegister.getText().toString().trim(),
                        this.binding.userCodeRegister.getText().toString().trim()

                ));
            }
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
//            this.binding.ipContent.setVisibility(View.VISIBLE);
        }
    }

    private boolean verLoginParams() {
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

    private boolean verRegisterParams() {
        if (TextUtils.isEmpty(this.binding.userNameRegister.getText().toString().trim())) {
            ToastUtil.show(this, "请输入用户名");
            return false;
        }
        if (TextUtils.isEmpty(this.binding.userPsdRegister.getText().toString().trim())) {
            ToastUtil.show(this, "请输入密码");
            return false;
        }
        if (TextUtils.isEmpty(this.binding.verPhoneRegister.getText().toString().trim())) {
            ToastUtil.show(this, "请输入手机号");
            return false;
        }
        if (TextUtils.isEmpty(this.binding.userCodeRegister.getText().toString().trim())) {
            ToastUtil.show(this, "请输入手机验证码");
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
    public void onSuccess(LoginReceModel looginReceModel) {
        Intent mainIntent = new Intent(LoginActivity.this, MainActivity.class);
        this.startActivity(mainIntent);
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
    public void onRegisterSuccess(UserRegisterParams userRegisterParams) {
        ToastUtil.show(this, "注册成功，请登陆");
        RxTimer rxTimer = new RxTimer();
        this.binding.userName.setText(userRegisterParams.getUserName());
        this.binding.userPsd.setText(userRegisterParams.getUserName());
        rxTimer.timer(1000,number -> {
            faceAndBackView.toggle();
        });
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
