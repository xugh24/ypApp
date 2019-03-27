package com.yuepang.yuepang.protocol;

import com.yuepang.yuepang.activity.BaseActivity;
import com.yuepang.yuepang.model.AuthCodeInfo;

import org.json.JSONObject;

/**
 * Created by xugh on 2019/3/27.
 */

public class RegisterProtocol extends JsonProtocol {


    private String pwd; // 密码

    private AuthCodeInfo info;

    public RegisterProtocol(BaseActivity baseActivity, AuthCodeInfo info, String pwd) {
        super(baseActivity);
        this.pwd = pwd;
        this.info = info;
    }

    @Override
    public void creatDataJson(JSONObject json) throws Exception {
        json.put("username", info.getmTel());
        json.put("code", info.getCode());
        json.put("mValidCode", info.getmValidCode());
        json.put("password", pwd);
    }

    @Override
    public String getUrlToken() {
        return "yuepang/reg";
    }

    @Override
    public Object onResponse(int code, String response) throws Exception {
        return null;
    }
}