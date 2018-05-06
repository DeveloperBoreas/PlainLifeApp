package com.boreas.di.componects;

import com.boreas.di.PerActivity;
import com.boreas.di.modules.ChooseImgsModule;
import com.boreas.ui.activity.ChoosePicsActivity;

import dagger.Component;

/**
 * 作者 boreas
 * 日期 18-3-15
 * 邮箱 13051089921@163.com
 */
@PerActivity
@Component(modules = ChooseImgsModule.class)
public interface ChooseImgsComponent {
    void inject(ChoosePicsActivity activity);
}
