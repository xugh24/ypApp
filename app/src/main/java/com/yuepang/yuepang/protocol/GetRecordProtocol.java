package com.yuepang.yuepang.protocol;

import com.android.common.enums.HttpType;
import com.android.common.utils.GsonUtils;
import com.yuepang.yuepang.activity.BaseActivity;
import com.yuepang.yuepang.interFace.LoadCallBack;
import com.yuepang.yuepang.model.PayItem;
import com.yuepang.yuepang.model.RecordInfo;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by xugh on 2019/3/27.
 *
 * 获得买单记录接口
 */

public class GetRecordProtocol extends JsonProtocol <List<RecordInfo>> {

    public GetRecordProtocol(BaseActivity baseActivity, LoadCallBack<List<RecordInfo>> loadCallBack) {
        super(baseActivity,loadCallBack);
    }

    @Override
    protected List<RecordInfo> analysis(String data) {
        List<RecordInfo>  list = new ArrayList<>();
        try {
            JSONArray array = new JSONArray(data);
            for(int i = 0; i<array.length();i++){
                RecordInfo payItem = GsonUtils.getInstance().fromJson(array.optString(i),RecordInfo.class);
                list.add(payItem);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public void creatDataJson(JSONObject json) {
    }

    @Override
    public HttpType getHttpType() {
        return HttpType.GET;
    }


    @Override
    public String getUrlToken() {
        return "yuepang/getOrderList/";
    }


}
