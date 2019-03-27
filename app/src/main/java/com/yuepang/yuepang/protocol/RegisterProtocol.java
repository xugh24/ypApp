package com.yuepang.yuepang.protocol;

import com.yuepang.yuepang.activity.BaseActivity;

import org.json.JSONObject;

/**
 * Created by xugh on 2019/3/27.
 */

public class RegisterProtocol extends JsonProtocol {

    private String tel; // 手机号

    private String pwd; // 密码

    private String code;// 验证码


    public RegisterProtocol(BaseActivity baseActivity, String tel, String pwd, String code) {
        super(baseActivity);
        this.tel = tel;
        this.pwd = pwd;
        this.code = code;
    }

    @Override
    public void creatDataJson(JSONObject json) throws Exception {
        json.put("username",tel);
        json.put("password",pwd);
        json.put("code",code);
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
