package com.boreas.plainlife.internal.modules;

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import com.boreas.plainlife.Constant;
import com.boreas.plainlife.api.ApiService;
import com.boreas.plainlife.framwork.BaseUrlInterceptor;
import com.boreas.plainlife.framwork.DownloadProgressInterceptor;
import com.boreas.plainlife.framwork.NetworkCacheInterceptor;
import com.boreas.plainlife.framwork.RetryIntercepter;
import com.boreas.plainlife.framwork.TokenInterceptor;

import java.security.SecureRandom;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.concurrent.TimeUnit;

import javax.inject.Singleton;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

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
                .addNetworkInterceptor(new NetworkCacheInterceptor())
                .addInterceptor(BaseUrlInterceptor.getInstance())
                .addInterceptor(new TokenInterceptor())
                .addInterceptor(new RetryIntercepter(Constant.RETRY_COUNT))
                .addInterceptor(new HttpLoggingInterceptor().setLevel(Constant.DEBUG ? HttpLoggingInterceptor.Level.BODY : HttpLoggingInterceptor.Level.NONE))
                .addInterceptor(new DownloadProgressInterceptor())
                .cache(NetworkCacheInterceptor.getCache())
                .sslSocketFactory(CreateSSLSocketFactory())
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

    private SSLSocketFactory CreateSSLSocketFactory() {
        SSLSocketFactory ssfFactory = null;
        try {
            SSLContext sc = SSLContext.getInstance("TLS");
            sc.init(null, new TrustManager[]{
                    new X509TrustManager() {
                        @Override
                        public void checkClientTrusted(java.security.cert.X509Certificate[] chain, String authType) throws CertificateException {
                        }

                        @Override
                        public void checkServerTrusted(java.security.cert.X509Certificate[] chain, String authType) throws CertificateException {
                        }

                        @Override
                        public java.security.cert.X509Certificate[] getAcceptedIssuers() {
                            return new java.security.cert.X509Certificate[]{};
                        }
                    }
            }, new java.security.SecureRandom());
            sc.init(null, new TrustManager[]{new X509TrustManager() {
                @Override
                public void checkClientTrusted(X509Certificate[] chain, String authType) throws CertificateException {

                }

                @Override
                public void checkServerTrusted(X509Certificate[] chain, String authType) throws CertificateException {

                }

                @Override
                public X509Certificate[] getAcceptedIssuers() {
                    return new X509Certificate[0];
                }
            }}, new SecureRandom());
            ssfFactory = sc.getSocketFactory();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ssfFactory;
    }
}
