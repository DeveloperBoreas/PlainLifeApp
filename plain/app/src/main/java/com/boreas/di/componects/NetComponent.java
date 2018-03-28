package com.boreas.di.componects;

import com.boreas.api.ApiService;
import com.boreas.di.modules.NetModule;

import javax.inject.Singleton;

import dagger.Component;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;

/**
 *
 * @author admin
 * @date 2018/2/23
 */
@Component(modules = {NetModule.class})
public interface NetComponent {
//    ApiService getApiService();
//    OkHttpClient getOkHttp();
//    Retrofit getRetrofit();
}
