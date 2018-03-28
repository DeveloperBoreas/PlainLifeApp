package com.boreas.presenter;

import android.graphics.drawable.Drawable;
import android.util.Log;

import com.boreas.R;
import com.boreas.base.UseCase;
import com.boreas.di.PerActivity;
import com.boreas.interactor.Login;

import javax.inject.Inject;

import rx.Subscriber;

/**
 * 作者 boreas
 * 日期 18-3-6
 * 邮箱 13051089921@163.com
 * @author boreas
 */

public class LoginPresenter implements PresenterContract.Presenter{
    private static final String TAG = LoginPresenter.class.getSimpleName();
    private Login loginUseCase = null;
    private PresenterContract.LoginView loginView = null;

    @Inject
    public LoginPresenter(Login login){
        this.loginUseCase = login;
    }
    @Override
    public void resume() {
        Log.d(TAG,"resume");
    }

    @Override
    public void pause() {
        Log.d(TAG,"pause");
    }

    @Override
    public void destroy() {
        Log.d(TAG,"destroy");
    }

    public LoginPresenter setLoginView(PresenterContract.LoginView loginView){
        this.loginView = loginView;
        return this;
    }
    public void initContentBg(){
        this.loginView.setContentBg(R.drawable.d);
    }

    public void login(String name,String password){
        Login login = (Login) this.loginUseCase;
        login.setData(name,password)
                .execute(new LoginSubscriber());
    }

    private class LoginSubscriber extends Subscriber {

        @Override
        public void onCompleted() {

        }

        @Override
        public void onError(Throwable e) {

        }

        @Override
        public void onNext(Object o) {

        }
    }
}
