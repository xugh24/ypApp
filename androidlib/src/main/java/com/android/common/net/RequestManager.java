package com.android.common.net;

import android.content.Context;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by xugh on 2019/4/15.
 * <p>
 * okhhtp 管理类
 */

public class RequestManager {

    private OkHttpClient mOkHttpClient;

    /**
     * 初始化RequestManager
     */
    public RequestManager(Context context) {
        //初始化OkHttpClient
        mOkHttpClient = new OkHttpClient().newBuilder()
                .connectTimeout(10, TimeUnit.SECONDS)//设置超时时间
                .readTimeout(10, TimeUnit.SECONDS)//设置读取超时时间
                .writeTimeout(10, TimeUnit.SECONDS)//设置写入超时时间
                .build();
        // 动态添加header 不会覆盖之前的header
        mOkHttpClient.interceptors().add(new Interceptor() {
            @Override
            public Response intercept(Interceptor.Chain chain) throws IOException {
                Request original = chain.request();
                // Request customization: add request headers
                Request.Builder requestBuilder = original.newBuilder()
                        .addHeader("header-key", "value1")
                        .addHeader("header-key", "value2");
                Request request = requestBuilder.build();
                return chain.proceed(request);
            }
        });
        mOkHttpClient.interceptors().add(new Interceptor() {
            @Override
            public Response intercept(Interceptor.Chain chain) throws IOException {
                Request original = chain.request();
                Request.Builder requestBuilder = original.newBuilder()
                        .header("headerkey", "header-value"); // <-- this is the important line
                Request request = requestBuilder.build();
                return chain.proceed(request);
            }
        });





        //初始化Handler
//        okHttpHandler = new Handler(context.getMainLooper());
    }

}
