package com.boreas.plainlife.internal.modules;

import com.boreas.plainlife.framwork.SSLSocketFactory;
import com.boreas.plainlife.websocket.WebSocketManger;
import com.boreas.plainlife.mq.RabbitMQConfiguration;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import com.boreas.plainlife.Constant;
import com.boreas.plainlife.api.ApiService;
import com.boreas.plainlife.framwork.BaseUrlInterceptor;
import com.boreas.plainlife.framwork.DownloadProgressInterceptor;
import com.boreas.plainlife.framwork.NetworkCacheInterceptor;
import com.boreas.plainlife.framwork.RetryIntercepter;
import com.boreas.plainlife.framwork.TokenInterceptor;

import java.util.concurrent.TimeUnit;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.fastjson.FastJsonConverterFactory;

@Singleton
@Module
public class NetModule {

    @Provides
    @Singleton
    public OkHttpClient provideOkHttp() {
        OkHttpClient okhttpClient = new OkHttpClient.Builder()
                .connectTimeout(Constant.CONNECT_TIME, TimeUnit.SECONDS)
                .readTimeout(Constant.READ_TIME, TimeUnit.SECONDS)
                .writeTimeout(Constant.WRITE_TIME, TimeUnit.SECONDS)
                .retryOnConnectionFailure(true)
                .pingInterval(Constant.PING_TIME,TimeUnit.SECONDS)
                .addNetworkInterceptor(new NetworkCacheInterceptor())
//                .addInterceptor(BaseUrlInterceptor.getInstance())
                .addInterceptor(new TokenInterceptor())
                .addInterceptor(new RetryIntercepter(Constant.RETRY_COUNT))
                .addInterceptor(new HttpLoggingInterceptor().setLevel(Constant.DEBUG ? HttpLoggingInterceptor.Level.BODY : HttpLoggingInterceptor.Level.NONE))
                .addInterceptor(new DownloadProgressInterceptor())
                .cache(NetworkCacheInterceptor.getCache())
                .sslSocketFactory(new SSLSocketFactory().CreateSSLSocketFactory())
                .hostnameVerifier((hostname, session) -> true)
                .build();
        return okhttpClient;
    }

    @Provides
    @Singleton
    public Retrofit provideRetrofit(OkHttpClient client) {
        Retrofit retrofit = new Retrofit.Builder()
                .client(client)
                .baseUrl(Constant.BASE_URL)
                .addConverterFactory(FastJsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
        return retrofit;
    }

    @Provides
    @Singleton
    public ApiService provideApiService(Retrofit retrofit) {
        return retrofit.create(ApiService.class);
    }

    @Provides
    @Singleton
    public WebSocketManger provideWebSocketManager(){
        return WebSocketManger.getInstance();
    }

    @Provides
    @Singleton
    public RabbitMQConfiguration provideRabbitMQConfiguration(){
        return RabbitMQConfiguration.getInstance();
    }


}
