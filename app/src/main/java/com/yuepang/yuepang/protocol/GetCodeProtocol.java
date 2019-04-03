package com.yuepang.yuepang.protocol;

import com.yuepang.yuepang.activity.BaseActivity;

import org.json.JSONObject;

/**
 * Created by xugh on 2019/3/27.
 *
 * 获得验证码接口
 */

public class GetCodeProtocol extends JsonProtocol {

    private String tel;

    public GetCodeProtocol(BaseActivity baseActivity, String tel) {
        super(baseActivity);
        this.tel = tel;
    }

    @Override
    public void creatDataJson(JSONObject json) throws Exception {
        json.put("tel", tel);
    }

    @Override
    public String getUrlToken() {
        return null;
    }

    @Override
    public Object onResponse(int code, String response) throws Exception {
        return null;
    }
}
