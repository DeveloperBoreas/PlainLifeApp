package com.xwzx.equipmanager.framwork;

import android.text.TextUtils;

import com.xwzx.equipmanager.Constant;
import com.xwzx.equipmanager.utils.PreUtil;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

public class TokenInterceptor implements Interceptor {
    @Override
    public Response intercept(Chain chain) throws IOException {
        /*获取token */
        String token = (String) PreUtil.get(Constant.TOKEN_KEY, "");
        if (TextUtils.isEmpty(token)) {
            Response response = chain.proceed(chain.request());
            return response;
        }
        Request authorization = chain.request().newBuilder()
                .addHeader("Authorization", token)
                .build();
//        HttpUrl url = chain.request().url();
//        url.pathSegments();
        Response response = chain.proceed(authorization);
        return response;
    }
}
