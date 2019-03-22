package com.yuepang.yuepang.protocol;

import com.yuepang.yuepang.activity.BaseActivity;

import org.json.JSONObject;

/**
 * Created by xugh on 2019/3/22.
 */

public class SendMsgProtocol extends JsonProtocol {
    public SendMsgProtocol(BaseActivity baseActivity) {
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
    public Object onResponse(int code, JSONObject response) throws Exception {
        return null;
    }
}
