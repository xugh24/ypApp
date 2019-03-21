package com.yuepang.yuepang.protocol;

import com.yuepang.yuepang.activity.BaseActivity;

import org.json.JSONObject;

/**
 * Created by xugh on 2019/3/21.
 */

public class LoginProtocol extends JsonProtocol {

    private final static String NAME = "username";
    private final static String PASSWORD = "password";

    private String name;

    private String pwd;

    public LoginProtocol(BaseActivity baseActivity, String name, String pwd) {
        super(baseActivity);
        this.name = name;
        this.pwd = pwd;
    }

    @Override
    public void creatDataJson(JSONObject json) throws Exception {
        json.put(NAME, name);
        json.put(PASSWORD, pwd);
    }

    @Override
    public String getUrlToken() {
        return "/user/login/";
    }

    @Override
    public Object onResponse(int code, JSONObject response) throws Exception {
        return null;
    }
}
