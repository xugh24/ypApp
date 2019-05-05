package com.yuepang.yuepang.protocol;

import android.content.Context;

import com.android.common.enums.HttpType;
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

public class GetBusinessAreaProtocol extends JsonProtocol<List<MerchantInfo>> {

    public GetBusinessAreaProtocol(Context context, LoadCallBack callBack) {
        super(context, callBack);
    }

    @Override
    protected String getUrlToken() {
        return "yuepang/getBusinessAreaList/";
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

    }

    @Override
    public HttpType getHttpType() {
        return HttpType.GET;
    }
}
