package com.boreas.di.componects;

import com.boreas.di.PerFragment;
import com.boreas.di.modules.MusicFragmentModule;
import com.boreas.ui.fragment.MusicFragment;

import dagger.Component;

/**
 * 作者 boreas
 * 日期 18-3-15
 * 邮箱 13051089921@163.com
 */
@PerFragment
@Component(modules = MusicFragmentModule.class)
public interface MusicFragmentComponent {
    void inject(MusicFragment musicFragment);
}
