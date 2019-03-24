package com.yuepang.yuepang.net;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;

import com.yuepang.yuepang.Util.LogUtils;
import com.yuepang.yuepang.Util.SysUtils;
import com.yuepang.yuepang.activity.BaseActivity;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.ResponseBody;

/**
 * Created by xugh on 2019/3/24.
 * <p>
 * okHttp 第三方框架封装类
 * okHttp 是一个处理网络请求的开源项目,是安卓端最火热的轻量级框架,由移动支付Square公司贡献(该公司还贡献了Picasso) [2]
 * 用于替代HttpUrlConnection和Apache HttpClient(android API23 6.0里已移除HttpClient)
 * 本项目使用okhttp 3.2.0 实现网络协议的请求和图片缓存功能
 */

public class MyOkHttpEngine {
    public static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");

    public BaseActivity baseActivity;// 基类activity 用户统一功能处理

    private boolean isCancel = false;// 是否取消协议请求

    public MyOkHttpEngine(BaseActivity baseActivity) {
        this.baseActivity = baseActivity;
    }


    /**
     * 网络协议请求方法
     *
     * @param requestJson 请求参数
     * @param url         请求地址
     */
    @TargetApi(Build.VERSION_CODES.KITKAT)
    public String executePost(String requestJson, String url) {
        RequestBody body = RequestBody.create(JSON, requestJson);
        OkHttpClient okHttpClient = new OkHttpClient();//Form表单格式的参数传递
        Request request = new Request.Builder().url(url).post(body).build();
        try {
            Response response = okHttpClient.newCall(request).execute();
            String data = response.body().string();
            LogUtils.e("response == " + data);
            return data;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }


    /**
     * 通过Okhttp 获得图片
     *
     * @return
     * @throws IOException
     */
    public static Drawable createGetRequest(Context context, String url) throws IOException {
        LogUtils.e("market downloadGet url:" + url);
        if (SysUtils.hasNetwork(context)) {
            OkHttpClient client = new OkHttpClient();//获取请求对象
            Request request = new Request.Builder().url(url).build();//获取响应体
            ResponseBody body = client.newCall(request).execute().body(); //获取流
            InputStream in = body.byteStream();
            Drawable drawable = Drawable.createFromStream(in, null);
            in.close();
            FileOutputStream fos = new FileOutputStream(new File(SysUtils.getImageCacheDir(context), String.valueOf(url.hashCode())));// 保存图片
            ((BitmapDrawable) drawable).getBitmap().compress(Bitmap.CompressFormat.PNG, 100, fos);
            return drawable;
        }
        return null;
    }

    /**
     *
     */
    public synchronized boolean isCancel() {
        return isCancel;
    }

    /**
     * 设置是否取消协议
     */
    public synchronized void setCancel(boolean isCancel) {
        this.isCancel = isCancel;
    }

}
