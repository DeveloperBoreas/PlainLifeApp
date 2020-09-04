package com.boreas.plainlife.framwork;

import android.text.TextUtils;

import com.boreas.plainlife.Constant;
import com.boreas.plainlife.api.ApiService;
import com.boreas.plainlife.utils.PreUtil;
import com.orhanobut.logger.Logger;

import java.io.IOException;

import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

public class TokenInterceptor implements Interceptor {
    @Override
    public Response intercept(Chain chain) throws IOException {
        /*获取token */
        String token = (String) PreUtil.get(Constant.TOKEN_KEY, "");
        if (TextUtils.isEmpty(token)) {
//            Logger.e("token 为空" );
            Response response = chain.proceed(chain.request());
            return response;
        }
        if(ApiService.notAllowInterceptPath.containsAll(chain.request().url().pathSegments())){
            Response response = chain.proceed(chain.request());
            return response;
        }
//        Logger.e("token 不为空："+ token);
        Request authorization = chain.request().newBuilder()
                .addHeader("Authorization", token)
                .build();
        HttpUrl url = chain.request().url();
        url.pathSegments();
        Response response = chain.proceed(authorization);
        return response;
    }
}
