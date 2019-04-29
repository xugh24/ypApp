package com.android.common.net;

import android.content.Context;
import android.text.TextUtils;


import com.android.common.enums.HttpType;

import org.json.JSONObject;

import java.io.PipedOutputStream;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by xugh on 2019/4/23.
 */

public class HttpConfig {

    public static int TIMEOUT = 10;

    private Context context;

    private HttpType httpType;// http 请求方式

    private String url;// 网络请求地址

    private JSONObject paraJson;// 参数

    private Map<String, String> headerParams;


    public HttpConfig(Context context, HttpType httpType, String url, JSONObject paraJson) {
        this.context = context;
        this.httpType = httpType;
        this.url = url;
        this.paraJson = paraJson;
    }

    private void init() {
    }

    public HttpType getHttpType() {
        return httpType;
    }


    public String getParaJson() {
        return paraJson.toString();
    }

    public String getUrl() {
        return url;
    }

    public Context getContext() {
        return context;
    }

    public Map<String, String> getHeaderParams() {
        return headerParams;
    }


    /**
     * 判断Json的传值
     */
    public boolean isPareJson() {
        return paraJson == null || paraJson.length() == 0 ? false : true;
    }

    public boolean isPostFile() {
        return false;
    }

    public Map<String,Object> getParams() {
        return null;
    }

    public void setHeaderParams(Map<String, String> headerParams) {
        this.headerParams = headerParams;
    }
}
