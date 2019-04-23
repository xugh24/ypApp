package com.android.common.net;

import android.content.Context;
import com.android.common.inter.HttpCallBack;
import com.android.common.inter.HttpEngine;
import com.android.common.model.ResultInfo;
import com.android.common.utils.DeviceUtils;
import com.android.common.utils.GsonUtils;
import com.android.common.utils.LogUtils;
import org.json.JSONObject;
import java.io.IOException;
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
        clientCall(httpConfig.getContext(),url, client, getBuilder().build(), httpCallBack);
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
        try {
            client.dispatcher().setMaxRequestsPerHost(10);
        } catch (Exception e) {
            LogUtils.e(e);
        }
        //检查网络
        if (!DeviceUtils.hasNetwork(context)) {
            ResultInfo info = new ResultInfo(-1, "网络无法连接");
            callBack.callBack(info);
        }
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, final IOException e) {
                ResultInfo info = new ResultInfo(-1, e.getMessage());
                callBack.callBack(info);
            }

            @Override
            public void onResponse(final Call call, Response response) throws IOException {
                try {
                    String json = response.body().string();
                    LogUtils.e(" onResponse:" + json);
                    JSONObject object = new JSONObject(json);
                    final int code = object.optInt("code");
                    final String desc = object.optString("desc");
                    final String data = object.optString("data");
                    if (code != 200) {
                        if (code == 444) {
                            callBack.logout();
                        } else {
                            callBack.callBack(new ResultInfo(code, desc));
                        }
                    } else {
                        try {
                            ResultInfo<T> info = new ResultInfo(code, desc);
                            T bean = GsonUtils.getInstance().fromJson(data, callBack.getType());
                            info.setData(bean);
                            callBack.onSuccess(info);
                        } catch (Exception e) {
                            callBack.callBack(new ResultInfo(-1, e.getMessage()));
                        }
                    }

                } catch (final Exception e) {
                    callBack.callBack(new ResultInfo(-1, e.toString()));
                } finally {
                    callBack.onFinish();
                }
            }
        });
    }

    private RequestBody getBody() {
        RequestBody body = FormBody.create(MediaType.parse("application/json; charset=utf-8"), httpConfig.getParaJson());
        LogUtils.e("request:" + httpConfig.getParaJson());
        return body;
    }
    private MultipartBody getMulBody() {
        MultipartBody.Builder b =   OkEngineUtils.getMultipartBodyBodyBuilder(httpConfig.getParams());
        LogUtils.e("request:" + httpConfig.getParams());
        return b.build();
    }
}
