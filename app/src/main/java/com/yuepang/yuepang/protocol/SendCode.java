package com.yuepang.yuepang.protocol;

import android.content.Context;

import com.yuepang.yuepang.interFace.LoadCallBack;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by xugh on 2019/5/8.
 */

public class SendCode extends JsonProtocol {

    private String tel;

    public SendCode(Context context, LoadCallBack callBack, String tel) {
        super(context, callBack);
        this.tel = tel;
    }

    @Override
    protected String getUrlToken() {
        return "user/sendCode1/";
    }

    @Override
    protected void creatDataJson(JSONObject reqJson) {
        try {
            reqJson.put("tel", tel);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
