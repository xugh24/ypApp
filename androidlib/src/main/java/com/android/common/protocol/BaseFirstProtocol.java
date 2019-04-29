package com.android.common.protocol;

import android.content.Context;

import com.android.common.async.CommonTaskExecutor;
import com.android.common.enums.HttpType;
import com.android.common.inter.HttpCallBack;
import com.android.common.model.ResultInfo;
import com.android.common.net.HttpConfig;
import com.android.common.net.OkhttpEngine;
import com.android.common.utils.LogUtils;

import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by xugh on 2019/4/25.
 */

public abstract class BaseFirstProtocol<T> implements HttpCallBack {


    public Context mContext; // android context

    private OkhttpEngine mHttpEngine; // http 请求类

    private HttpConfig httpConfig; // http数据类

    public BaseFirstProtocol(Context context) {
        mContext = context;
        mHttpEngine = new OkhttpEngine(mContext);
    }

    public int request() {
        JSONObject json = getRequestJson();
        try {
            executePost(json);
        } catch (Exception e) {
            LogUtils.e(e);
        }
        return 1;
    }

    protected void executePost(JSONObject json) throws Exception {
        httpConfig = new HttpConfig(mContext, getHttpType(), getUrl(), json);
        httpConfig.setHeaderParams(getHeadMap());
        CommonTaskExecutor.execute(new Runnable() {
            @Override
            public void run() {
                mHttpEngine.startLoad(httpConfig, BaseFirstProtocol.this);
            }
        });
    }

    @Override
    public abstract void onStart();

    @Override
    public abstract void logout();

    @Override
    public abstract void onSuccess(ResultInfo info);

    @Override
    public abstract void onFinish();


    /**
     * 获得请求地址
     */
    protected abstract String getUrl();

    /**
     * 获得请求数据
     */
    protected abstract JSONObject getRequestJson();

    /**
     * 获得请求类型
     */
    public abstract HttpType getHttpType();

    public abstract Map<String, String> getHeadMap();
}
