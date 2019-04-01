package com.yuepang.yuepang.protocol;

import com.yuepang.yuepang.activity.BaseActivity;
import com.yuepang.yuepang.model.RecordInfo;

import org.json.JSONObject;

import java.util.List;

/**
 * Created by xugh on 2019/3/27.
 */

public class GetRecordProtocol extends JsonProtocol {
    public GetRecordProtocol(BaseActivity baseActivity) {
        super(baseActivity);
    }

    @Override
    public void creatDataJson(JSONObject json) throws Exception {

    }

    @Override
    public String getUrlToken() {
        return null;
    }

    @Override
    public List<RecordInfo> onResponse(int code, String response) throws Exception {
        return null;
    }
}
