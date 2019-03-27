/**
 * File Name: Protocol.java
 */
package com.yuepang.yuepang.protocol;

import android.text.TextUtils;

import com.yuepang.yuepang.Util.DomainUtil;
import com.yuepang.yuepang.Util.LogUtils;
import com.yuepang.yuepang.activity.BaseActivity;
import com.yuepang.yuepang.net.MyOkHttpEngine;

import org.json.JSONObject;

public abstract class JsonProtocol<T> {
    // ==========================================================================
    // Constants
    // ==========================================================================

    private static final String CODE = "code";//
    private static final String MSG = "desc";
    public static final String DATA = "data";



    // ==========================================================================
    // Fields
    // ==========================================================================

    private String mCodeDesc = "";
    public int mCode = 0;
    protected JSONObject mResponse;
    protected T mData;
    private MyOkHttpEngine mHttpEngine;
    protected BaseActivity baseActivity;


    public JsonProtocol(BaseActivity baseActivity) {
        this.baseActivity = baseActivity;
        mHttpEngine = new MyOkHttpEngine(baseActivity);
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
        String response = mHttpEngine.executePost(json.toString(), reqAddr);
        if (mHttpEngine.isCancel()) {
            LogUtils.w("protocol canceled ignore!!");
            return -2;
        }
        if (!TextUtils.isEmpty(response)) {
            mResponse = new JSONObject(response);
            mCode = mResponse.optInt(CODE);
            mCodeDesc = mResponse.optString(MSG);
            if(mCode == 200){
                mData = onResponse(mCode, mResponse.optString(DATA));
            }
        }
        return mCode;
    }

    /**
     * 设置请求数据
     */
    public abstract void creatDataJson(JSONObject json) throws Exception;

    /**
     * @return 接口名
     */
    public abstract String getUrlToken();

    public abstract T onResponse(int code, String response) throws Exception;

    // ==========================================================================
    // Inner/Nested Classes
    // ==========================================================================

}
