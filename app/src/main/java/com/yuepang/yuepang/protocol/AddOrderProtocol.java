package com.yuepang.yuepang.protocol;

import android.content.Context;

import com.android.common.utils.GsonUtils;
import com.android.common.utils.LogUtils;
import com.yuepang.yuepang.interFace.LoadCallBack;
import com.yuepang.yuepang.interFace.LoginState;
import com.yuepang.yuepang.model.PayItem;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by xugh on 2019/5/5.
 */

public class AddOrderProtocol  extends JsonProtocol <PayItem>{

    private int shopId ;

    private float price;


    public AddOrderProtocol(Context context, LoadCallBack callBack,int  shopId,float price) {
        super(context, callBack);
        this.shopId = shopId;
        this.price = price;
    }

    @Override
    protected PayItem analysis(String data) {
        LogUtils.e("data "+data);
        PayItem payItem = GsonUtils.getInstance().fromJson(data,PayItem.class);
        return payItem;
    }

    @Override
    protected String getUrlToken() {
        return "yuepang/addOrder/";
    }
    @Override
    protected void creatDataJson(JSONObject reqJson) {
        try {
            reqJson.put("shopId",shopId);
            reqJson.put("price",price);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
