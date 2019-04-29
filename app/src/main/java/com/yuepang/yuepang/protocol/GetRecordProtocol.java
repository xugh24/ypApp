package com.yuepang.yuepang.protocol;

import com.android.common.enums.HttpType;
import com.yuepang.yuepang.activity.BaseActivity;
import com.yuepang.yuepang.model.RecordInfo;

import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.List;

/**
 * Created by xugh on 2019/3/27.
 *
 * 获得买单记录接口
 */

public class GetRecordProtocol extends JsonProtocol {
    public GetRecordProtocol(BaseActivity baseActivity) {
        super(baseActivity,null);
    }

    @Override
    public void creatDataJson(JSONObject json) {
    }

    @Override
    public HttpType getHttpType() {
        return null;
    }


    @Override
    public String getUrlToken() {
        return null;
    }


}
