package com.xwzx.equipmanager.framwork;

import com.orhanobut.logger.Logger;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;


public class RetryIntercepter implements Interceptor {

    public int maxRetry;
    private int retryNum = 0;//最大可能请求4次（默认1次+3次重试）

    public RetryIntercepter(int maxRetry) {
        this.maxRetry = maxRetry;
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();
//        Logger.e("-----out-----retryNum=" + retryNum);
        Response response = chain.proceed(request);
        while (!response.isSuccessful() && retryNum < maxRetry) {
            retryNum++;
            Logger.e("-----in-----retryNum=" + retryNum);
            response = chain.proceed(request);
        }
        return response;
    }
}
