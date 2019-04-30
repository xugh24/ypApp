package com.yuepang.yuepang.protocol;

import android.content.Context;

import com.yuepang.yuepang.interFace.LoadCallBack;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by xugh on 2019/4/29.
 */

public class AddLikeProtocol extends JsonProtocol {
    private String msg;

    public AddLikeProtocol(Context context, LoadCallBack callBack, String msg) {
        super(context, callBack);
        this.msg = msg;
    }

    @Override
    protected String getUrlToken() {
        return "yuepang/addFavorite/";
    }

    @Override
    protected void creatDataJson(JSONObject reqJson) {
        try {
            reqJson.put("favorites",msg);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
