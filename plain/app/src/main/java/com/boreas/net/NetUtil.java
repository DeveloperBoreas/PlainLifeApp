package com.boreas.net;

import android.app.Activity;
import android.util.Log;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

import okhttp3.Cache;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * Created by Administrator on 2017/7/11.
 */

public class NetUtil {
    public static OkHttpClient mOkHttpClient;
    public static final MediaType MEDIA_TYPE_MARKDOWN = MediaType.parse("text/x-markdown; charset=utf-8");
    private static final MediaType MEDIA_TYPE_PNG = MediaType.parse("image/png");

    static {
        File sdcache = new File("/mnt/sdcard/cash");
        int cacheSize = 10 * 1024 * 1024;
        OkHttpClient.Builder builder = new OkHttpClient.Builder().connectTimeout(15, TimeUnit.SECONDS).writeTimeout
                (20, TimeUnit.SECONDS).readTimeout(20, TimeUnit.SECONDS).cache(new Cache(sdcache.getAbsoluteFile(),
                cacheSize));
        mOkHttpClient = builder.build();
    }

    public static void initOkHttpClient() {
        File sdcache = new File("/mnt/sdcard/cash");
        int cacheSize = 10 * 1024 * 1024;
        OkHttpClient.Builder builder = new OkHttpClient.Builder().connectTimeout(15, TimeUnit.SECONDS).writeTimeout
                (20, TimeUnit.SECONDS).readTimeout(20, TimeUnit.SECONDS).cache(new Cache(sdcache.getAbsoluteFile(),
                cacheSize));
        mOkHttpClient = builder.build();
    }

    /**
     * get异步请求
     */
    public static void requestForGet(final Activity context, String url, Map<String, String> parametersMap, final
    NetCallBack netCallBack) {
        if (parametersMap != null && parametersMap.size() > 0) {
            url += "?";
            for (Map.Entry<String, String> entry : parametersMap.entrySet()) {
                url += entry.getKey();
                url += "=";
                url += entry.getValue();
                url += "&";
            }
            url = url.substring(0, url.length() - 1);
        }

        Request.Builder requestBuilder = new Request.Builder().url(url).removeHeader("User-Agent").addHeader("User-Agent",
                "Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/66.0.3359.117 Safari/537.36");
        requestBuilder.method("GET", null);
        requestBuilder.tag(url);
        Request request = requestBuilder.build();
        Call mcall = mOkHttpClient.newCall(request);
        mcall.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, final IOException e) {
                context.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        netCallBack.onError(e);
                    }
                });

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                final String str = response.body().string();
                context.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Log.d("aaaa",str+"");
                        netCallBack.onSuccess(str);
                        Toast.makeText(context, "请求成功", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }

    /**
     * post异步请求
     */
    public static void requestForPost(final Activity context, String url, Map<String, String> parametersMap, final
    NetCallBack netCallBack) {
//        RequestBody formBody = new FormBody.Builder().add("size", "10").build();
        FormBody.Builder formBodyBuilder = new FormBody.Builder();
        if (parametersMap.size() > 0) {
            for (Map.Entry<String, String> entry : parametersMap.entrySet()) {
                formBodyBuilder.add(entry.getKey(),entry.getValue());
            }
        }
        RequestBody formBody = formBodyBuilder.build();
        Request request = new Request.Builder().url(url).post
                (formBody).tag(url).build();
        Call call = mOkHttpClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, final IOException e) {
                context.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        netCallBack.onError(e);
                    }
                });

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                final String str = response.body().string();

                context.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        netCallBack.onSuccess(str);
                        //Toast.makeText(context, "请求成功", Toast.LENGTH_SHORT).show();
                    }
                });
            }

        });
    }

    /**
     * 异步上传文件
     */
    public static void requestForPostFile(final Activity context, String url, String filePath, final
    NetCallBack netCallBack) {
        File file = new File(filePath);
        Request request = new Request.Builder().url(url).tag(url).post(RequestBody.create
                (MEDIA_TYPE_MARKDOWN, file)).build();
        mOkHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, final IOException e) {
                context.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        netCallBack.onError(e);
                    }
                });

            }

            @Override
            public void onResponse(Call call, final Response response) throws IOException {
                context.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            netCallBack.onSuccess(response.body().string());
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                });
            }
        });
    }


    /**
     * 异步下载文件
     */
    public static void requestForDownFile(final Activity context, String url, final String filePath, final
    NetCallBack netCallBack) {
//        String url = "http://img.my.csdn.net/uploads/201603/26/1458988468_5804.jpg";
        Request request = new Request.Builder().url(url).tag(url).build();
        mOkHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, final IOException e) {
                context.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        netCallBack.onError(e);
                    }
                });
            }

            @Override
            public void onResponse(Call call, Response response) {
                InputStream inputStream = response.body().byteStream();
                FileOutputStream fileOutputStream = null;
                try {
                    fileOutputStream = new FileOutputStream(new File(filePath));
                    byte[] buffer = new byte[2048];
                    int len = 0;
                    while ((len = inputStream.read(buffer)) != -1) {
                        fileOutputStream.write(buffer, 0, len);
                    }
                    fileOutputStream.flush();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                context.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        netCallBack.onSuccess("");
                    }
                });
            }
        });
    }

    public static void cancle(String tag) {
        for (Call call : mOkHttpClient.dispatcher().queuedCalls()) {
            if (tag.equals(call.request().tag())) {
                call.cancel();
            }
        }
        for (Call call : mOkHttpClient.dispatcher().runningCalls()) {
            if (tag.equals(call.request().tag())) {
                call.cancel();
            }
        }
    }

    public static void sendMultipart() {
        RequestBody requestBody = new MultipartBody.Builder().setType(MultipartBody.FORM).addFormDataPart("title",
                "wangshu").addFormDataPart("image", "wangshu.jpg", RequestBody.create(MEDIA_TYPE_PNG, new File
                ("/sdcard/wangshu.jpg"))).build();

        Request request = new Request.Builder().header("Authorization", "Client-ID " + "...").url("https://api.imgur"
                + ".com/3/image").post(requestBody).build();

        mOkHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                Log.i("wangshu", response.body().string());
            }
        });
    }

    public interface NetCallBack {
        void onError(Throwable message);

        void onSuccess(String str);
    }
}
