package com.boreas.net;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;


public class HttpRequest {
    private static OkHttpClient client = null;

    public static void init(OkHttpClient okHttpClient) {
        client= okHttpClient;
    }

    public static void doGet(String url,CallBack cb) {
        Request.Builder builder = new Request.Builder()
                .get()
                .url(url);
        Request request = builder.build();
        enqueueRequest(request,cb);
//        return executeRequest(request);
    }

    public void doPost() {
        Request request = new Request.Builder()
                .url("")
                .post(null)
                .build();
//        executeRequest(request, cb);
    }

    private String executeRequest(Request request) {
        Call call = client.newCall(request);
        try {
            Response response = call.execute();
            String result = response.body().string();
            System.out.println("------executeRequest-----"+result);
            return result;
        } catch (IOException e) {
            e.printStackTrace();
            return "";
        }
    }

    /**
     * 同步
     * @param request
     * @param cb
     */
    private void executeRequest(Request request, CallBack cb) {
        Call call = client.newCall(request);
        try {
            Response response = call.execute();
            String result = response.body().toString();
            cb.onSuccess(result);
        } catch (IOException e) {
            e.printStackTrace();
            cb.onFail(e);
        }
    }

    /**
     * 异步
     * @param request
     * @param cb
     */
    private static void enqueueRequest(Request request, CallBack cb) {
        Call call = client.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                System.out.print("onFailure:" + e.getMessage());
                e.printStackTrace();
                cb.onFail(e);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                final String res = response.body().string();
//                System.out.println("-------------- onResponse res : " + res);
                cb.onSuccess(res);
            }
        });
    }
}
