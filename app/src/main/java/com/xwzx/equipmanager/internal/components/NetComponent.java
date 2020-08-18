package com.xwzx.equipmanager.internal.components;

import com.xwzx.equipmanager.api.ApiService;
import com.xwzx.equipmanager.internal.modules.NetModule;

import javax.inject.Singleton;

import dagger.Component;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;


@Component(modules = NetModule.class,dependencies = AppComponent.class)
@Singleton
public interface NetComponent {
    ApiService getService();
    OkHttpClient getOkHttp();
    Retrofit getRetrofit();
}
