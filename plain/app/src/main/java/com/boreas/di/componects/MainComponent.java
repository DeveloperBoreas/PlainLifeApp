package com.boreas.di.componects;

import com.boreas.di.PerActivity;
import com.boreas.di.modules.MainModule;
import com.boreas.ui.activity.MainActivity;

import dagger.Component;

/**
 * 作者 boreas
 * 日期 18-3-12
 * 邮箱 13051089921@163.com
 */
@PerActivity
@Component(modules = MainModule.class)
public interface MainComponent {
    void inject(MainActivity activity);
}
