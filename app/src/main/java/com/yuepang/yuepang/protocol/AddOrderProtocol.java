package com.yuepang.yuepang.protocol;

import android.content.Context;

import com.yuepang.yuepang.interFace.LoadCallBack;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by xugh on 2019/5/5.
 */

public class AddOrderProtocol extends JsonProtocol {

    private int shopId ;

    private float price;


    public AddOrderProtocol(Context context, LoadCallBack callBack,int  shopId,float price) {
        super(context, callBack);
        this.shopId = shopId;
        this.price = price;
    }

    @Override
    protected String getUrlToken() {
        return "yuepang/addOrder/";
    }
    @Override
    protected void creatDataJson(JSONObject reqJson) {
        try {
            reqJson.put("shopId",shopId);
            reqJson.put("price",1.0f);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
