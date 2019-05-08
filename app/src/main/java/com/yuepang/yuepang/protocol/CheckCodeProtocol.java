package com.yuepang.yuepang.protocol;

import android.content.Context;

import com.yuepang.yuepang.interFace.LoadCallBack;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by xugh on 2019/5/8.
 */

public class CheckCodeProtocol extends JsonProtocol {

    private String tel;

    private String code;

    public CheckCodeProtocol(Context context, LoadCallBack callBack, String tel, String code) {
        super(context, callBack);
        this.tel = tel;
        this.code = code;
    }

    @Override
    protected String getUrlToken() {
        return "user/checkCode/";
    }
    @Override
    protected void creatDataJson(JSONObject reqJson) {
        try {
            reqJson.put("code", code);
            reqJson.put("tel", tel);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
