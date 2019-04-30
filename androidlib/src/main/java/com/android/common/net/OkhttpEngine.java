package com.android.common.net;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;

import com.android.common.inter.HttpCallBack;
import com.android.common.inter.HttpEngine;
import com.android.common.model.ResultInfo;
import com.android.common.utils.ConfigBuild;
import com.android.common.utils.DeviceUtils;
import com.android.common.utils.LogUtils;

import org.json.JSONObject;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.concurrent.TimeUnit;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.ResponseBody;

import static com.android.common.model.ResultInfo.LOGIN_INVALID;


/**
 * Created by xugh on 2019/4/15.
 */

public class OkhttpEngine<T> implements HttpEngine {

    private Context context;

    private HttpConfig httpConfig;

    private HttpCallBack httpCallBack;

    private String url;

    public OkhttpEngine(Context context) {
        this.context = context;
    }

    @Override
    public void startLoad(HttpConfig httpConfig, HttpCallBack httpCallBack) {
        this.httpCallBack = httpCallBack;
        this.httpConfig = httpConfig;
        url = httpConfig.getUrl();
        OkHttpClient client = new OkHttpClient.Builder().readTimeout(HttpConfig.TIMEOUT, TimeUnit.SECONDS).build();
        clientCall(httpConfig.getContext(), url, client, getBuilder().build(), httpCallBack);
    }


    private Request.Builder getBuilder() {
        Request.Builder builder = new Request.Builder()
                .headers(OkEngineUtils.getHeaders(httpConfig.getHeaderParams()))
                .url(url);
        switch (httpConfig.getHttpType()) {
            case POST:
                if (httpConfig.isPostFile()) {
                    builder.post(getMulBody());
                } else {
                    builder.post(getBody());
                }
                break;
            case DELETE:
                builder.delete(getBody());
                break;
            case PUT:
                builder.put(getBody());
            default:
                break;
        }
        return builder;
    }


    /**
     * 开始发起请求
     *
     * @param client
     * @param request
     * @param callBack
     * @param
     */
    private void clientCall(final Context context, final String url, OkHttpClient client, Request request, final HttpCallBack callBack) {
        callBack.onStart();
        LogUtils.e(" onResponse url:" + url);
        try {
            client.dispatcher().setMaxRequestsPerHost(10);
        } catch (Exception e) {
            LogUtils.e(e);
        }
        //检查网络
        if (!DeviceUtils.hasNetwork(context)) {
            callBack.onFailed(ResultInfo.getNoNetInfo());//
            callBack.onFinish();// 结束访问
            return;
        }
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, final IOException e) {
                callBack.onFailed(ResultInfo.getErrorInfo(e.getMessage()));//
                callBack.onFinish();// 结束访问
            }

            @Override
            public void onResponse(final Call call, Response response) throws IOException {
                try {
                    String json = response.body().string();
                    LogUtils.e(" onResponse:" + json);
                    JSONObject object = new JSONObject(json);
                    final int code = object.optInt("code");
                    final String desc = object.optString("desc");
                    final String data = (object.optString("data"));
                    if (code == 200) {
                        ResultInfo info = new ResultInfo(code,desc);
                        info.setData(data);
                        callBack.onSuccess(info);
                    } else {
                        if (code == LOGIN_INVALID) {
                            callBack.logout();
                        } else {
                            callBack.onFailed(new ResultInfo(code, desc));
                        }
                    }
                } catch (final Exception e) {
                    callBack.onFailed(ResultInfo.getErrorInfo(e.getMessage()));//
                } finally {
                    callBack.onFinish();
                }
            }
        });
    }


    /**
     * 通过Okhttp 获得图片
     *
     * @return
     * @throws IOException
     */
    public static Drawable createGetRequest(Context context, String url) throws IOException {
        LogUtils.e("market downloadGet url:" + url);
        if (DeviceUtils.hasNetwork(context)) {
            OkHttpClient client = new OkHttpClient();//获取请求对象
            Request request = new Request.Builder().url(url).build();//获取响应体
            ResponseBody body = client.newCall(request).execute().body(); //获取流
            InputStream in = body.byteStream();
            Drawable drawable = Drawable.createFromStream(in, null);
            in.close();
            FileOutputStream fos = new FileOutputStream(new File(ConfigBuild.getImageCacheDir(context), String.valueOf(url.hashCode())));// 保存图片
            ((BitmapDrawable) drawable).getBitmap().compress(Bitmap.CompressFormat.PNG, 100, fos);
            fos.close();
            return drawable;
        }
        return null;
    }

    private RequestBody getBody() {
        RequestBody body = FormBody.create(MediaType.parse("application/json; charset=utf-8"), httpConfig.getParaJson());
        LogUtils.e("request:" + httpConfig.getParaJson());
        return body;
    }

    private MultipartBody getMulBody() {
        MultipartBody.Builder b = OkEngineUtils.getMultipartBodyBodyBuilder(httpConfig.getParams());
        LogUtils.e("request:" + httpConfig.getParams());
        return b.build();
    }
}
