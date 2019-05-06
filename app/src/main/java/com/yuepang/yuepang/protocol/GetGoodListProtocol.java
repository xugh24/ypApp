package com.yuepang.yuepang.protocol;

import android.content.Context;

import com.android.common.utils.GsonUtils;
import com.yuepang.yuepang.interFace.LoadCallBack;
import com.yuepang.yuepang.model.GoodInfo;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by xugh on 2019/5/6.
 */

public class GetGoodListProtocol extends JsonProtocol<List<GoodInfo>> {

    private int id;

    public GetGoodListProtocol(Context context, LoadCallBack callBack, int id) {
        super(context, callBack);
        this.id = id;
    }

    @Override
    protected List<GoodInfo> analysis(String data) {
        List<GoodInfo> infos = new ArrayList<>();
        try {
            JSONArray array = new JSONArray(data);
            if (array != null && array.length() > 0) {
                for (int i = 0; i < array.length(); i++) {
                    infos.add(GsonUtils.getInstance().fromJson(array.optString(i), GoodInfo.class));
                }
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return infos;
    }

    @Override
    protected String getUrlToken() {
        return "yuepang/getGoodList/";
    }

    @Override
    protected void creatDataJson(JSONObject reqJson) {
        try {
            reqJson.put("shopId", id);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
