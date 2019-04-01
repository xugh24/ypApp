package com.yuepang.yuepang.protocol;

import com.yuepang.yuepang.activity.BaseActivity;

import org.json.JSONObject;

/**
 */

public class LoginProtocol extends JsonProtocol {

    private final static String NAME = "username";
    private final static String PASSWORD = "password";

    public final static String USERINFO = "userInfo";

    public final static String TOKEN = "token";

    public final static String AVATAR = "avatar";//头像地址

    public final static String ID = "id"; // 用户ID

    public final static String NICK = "nick_name";// 昵称

    public final static String SEX = "sex";// 性别

    public final static String tel = "tel";// 电话

    public final static String USERNAME = "username";// 用户名


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
        return "user/login/";
    }

    @Override
    public JSONObject onResponse(int code, String response) throws Exception {
        return new JSONObject(response);
    }
}
