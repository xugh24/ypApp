package com.yuepang.yuepang.protocol;

import android.content.Context;

import com.yuepang.yuepang.interFace.LoadCallBack;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by xugh on 2019/4/30.
 */

public class GetBusinessAreaProtocol extends JsonProtocol {

    public GetBusinessAreaProtocol(Context context, LoadCallBack callBack) {
        super(context, callBack);
    }

    @Override
    protected String getUrlToken() {
        return "yuepang/getBusinessArea/";
    }

    @Override
    protected void creatDataJson(JSONObject reqJson) {
        try {
            reqJson.put("longitude", 120.100000000);
            reqJson.put("latitude", "42.100000000");
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }
}
