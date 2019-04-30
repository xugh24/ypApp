package com.yuepang.yuepang.protocol;

import android.content.Context;

import com.yuepang.yuepang.interFace.LoadCallBack;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by xugh on 2019/4/30.
 */

public class GetShopListProtocol extends JsonProtocol {
    public GetShopListProtocol(Context context, LoadCallBack callBack) {
        super(context, callBack);
    }

    @Override
    protected String getUrlToken() {
        return "yuepang/getShopList/";
    }

    @Override
    protected void creatDataJson(JSONObject reqJson) {
        try {
            reqJson.put("businessAreaId", 1);
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }
}
