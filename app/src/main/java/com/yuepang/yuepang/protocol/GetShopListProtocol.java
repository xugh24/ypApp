package com.yuepang.yuepang.protocol;

import android.content.Context;

import com.android.common.utils.GsonUtils;
import com.yuepang.yuepang.interFace.LoadCallBack;
import com.yuepang.yuepang.model.MerchantInfo;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by xugh on 2019/4/30.
 */

public class GetShopListProtocol  extends JsonProtocol  {

    private int id;

    public GetShopListProtocol(Context context, LoadCallBack callBack,int id) {
        super(context, callBack);
        this.id = id;
    }

    @Override
    protected String getUrlToken() {
        return "yuepang/getShopList/";
    }


    @Override
    protected List<MerchantInfo> analysis(String data) {
        List<MerchantInfo> merchantInfos = new ArrayList<>();
        try {
            JSONArray array = new JSONArray(data);
            for (int i = 0; i < array.length(); i++) {
                merchantInfos.add(GsonUtils.getInstance().fromJson(array.optString(i), MerchantInfo.class));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return merchantInfos;
    }

    @Override
    protected void creatDataJson(JSONObject reqJson) {
        try {
            reqJson.put("businessAreaId", id);
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }
}
