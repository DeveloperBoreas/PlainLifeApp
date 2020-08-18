package com.xwzx.equipmanager.framwork;

import android.text.TextUtils;

import com.xwzx.equipmanager.Constant;
import com.xwzx.equipmanager.utils.PreUtil;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

public class BaseUrlInterceptor implements Interceptor {
    private static BaseUrlInterceptor baseUrlInterceptor = new BaseUrlInterceptor();

    private BaseUrlInterceptor() {
    }

    public static BaseUrlInterceptor getInstance() {
        return baseUrlInterceptor;
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();
        String oldUrl = request.url().toString();
        String newUrl = (String) PreUtil.get("IP", "");
        if (!TextUtils.isEmpty(newUrl)) {
            String BASE_URL = "http://" + newUrl + ":9060";
            newUrl = BASE_URL + oldUrl.substring(Constant.BASE_URL.length(), oldUrl.length());
            Request newRequest = request
                    .newBuilder()
                    .url(newUrl)
                    .build();
            return chain.proceed(newRequest);
        }
        return chain.proceed(request);
    }

}
