package com.boreas.di.componects;

import com.boreas.di.modules.LoginModule;
import com.boreas.ui.activity.LoginActivity;

import dagger.Component;

/**
 * 作者 boreas
 * 日期 18-3-7
 * 邮箱 13051089921@163.com
 * @author boreas
 */
@Component(modules = {LoginModule.class})
public interface LoginComponent {
    void inject(LoginActivity loginActivity);
}
