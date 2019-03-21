/**
 * File Name: Protocol.java
 * History:
 * @author xugh
 */
package com.yuepang.yuepang.protocol;

import android.content.Context;
import android.os.Build;
import android.text.TextUtils;
import com.yuepang.yuepang.Util.DomainUtil;
import com.yuepang.yuepang.Util.LogUtils;
import com.yuepang.yuepang.activity.BaseActivity;
import com.yuepang.yuepang.control.DataControl;
import com.yuepang.yuepang.net.HttpEngine;

import org.json.JSONObject;

public abstract class JsonProtocol<T> {
    // ==========================================================================
    // Constants
    // ==========================================================================

    public static final int CODE_OK = 200;
    private static final String CODE = "code";//
    private static final String MSG = "msg";
    public static final String DATA = "data";

    protected static final String TAG = "JsonProtocol";


    // ==========================================================================
    // Fields
    // ==========================================================================

    private String mCodeDesc = "";
    public int mCode = 0;
    protected JSONObject mResponse;
    protected T mData;
    private HttpEngine mHttpEngine;
    protected BaseActivity baseActivity;


    public JsonProtocol(BaseActivity baseActivity) {
        this.baseActivity = baseActivity;
        mHttpEngine = new HttpEngine(baseActivity);

    }
    /**
     * 协议返回code描述信息
     *
     * @return
     */
    public String getCodeDesc() {
        return mCodeDesc;
    }

    /**
     * 协议返回code
     *
     * @return
     */

    public int getCode() {
        return mCode;
    }

    public T getData() {
        return mData;
    }

    // ==========================================================================
    // Setters
    // ==========================================================================

    // ==========================================================================
    // Methods
    // ==========================================================================
    public int request() {
        JSONObject json = new JSONObject();
        try {
            creatDataJson(json);
            mCode = executePost(json);
            return mCode;
        } catch (Exception e) {
            LogUtils.e(e);
        }
        return mCode;
    }

    public String getHeardUrl() {
        return DomainUtil.MAIN_URL;
    }

    protected int executePost(JSONObject json) throws Exception {
        String reqAddr = getHeardUrl() + getUrlToken();
        String response = mHttpEngine.executePost(json.toString(),reqAddr);
        if (mHttpEngine.isCanceled()) {
            LogUtils.w("protocol canceled ignore!!");
            return -2;
        }
        if (!TextUtils.isEmpty(response)) {
            mResponse = new JSONObject(response);
            mCode = mResponse.optInt(CODE);
            mCodeDesc = mResponse.optString(MSG);
            String msg = mResponse.optString(DATA);
            if (!TextUtils.isEmpty(msg)) {

            }
        } else {

        }
        return mCode;
    }

    public abstract void creatDataJson(JSONObject json) throws Exception;

    public abstract String getUrlToken();

    public abstract T onResponse(int code, JSONObject response) throws Exception;

    // ==========================================================================
    // Inner/Nested Classes
    // ==========================================================================

}
