package com.boreas.di.componects;

import android.content.Context;

import com.boreas.di.modules.AppModule;

import javax.inject.Singleton;

import dagger.Component;

/**
 * 作者 boreas
 * 日期 18-2-24
 * 邮箱 13051089921@163.com
 * @author boreas
 */
@Component(modules = AppModule.class)
@Singleton
public interface AppComponent {
//    Context getContext();
}
