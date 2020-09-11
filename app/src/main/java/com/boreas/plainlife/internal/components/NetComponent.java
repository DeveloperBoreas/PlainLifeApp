package com.boreas.plainlife.internal.components;

import com.boreas.plainlife.mq.RabbitMQConfiguration;
import com.boreas.plainlife.websocket.WebSocketManger;
import com.boreas.plainlife.api.ApiService;
import com.boreas.plainlife.internal.modules.NetModule;

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
    WebSocketManger getWebSocketManager();
    RabbitMQConfiguration getRabbitMQConfiguration();
}
