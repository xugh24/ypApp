package com.yuepang.yuepang.protocol;

import com.android.common.utils.GsonUtils;
import com.yuepang.yuepang.activity.BaseActivity;
import com.yuepang.yuepang.control.UserCentreControl;
import com.yuepang.yuepang.interFace.LoadCallBack;
import com.yuepang.yuepang.model.UserInfo;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;


public class LoginProtocol extends JsonProtocol<UserInfo> {

    private final static String NAME = "username";
    private final static String PASSWORD = "password";

    private String name;

    private String pwd;

    public LoginProtocol(BaseActivity baseActivity, LoadCallBack loadCallBack, String name, String pwd) {
        super(baseActivity, loadCallBack);
        this.name = name;
        this.pwd = pwd;
    }

    @Override
    public void creatDataJson(JSONObject json) {
        try {
            json.put(NAME, name);
            json.put(PASSWORD, pwd);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected UserInfo analysis(String sty) {
        JSONObject data = null;
        try {
            data = new JSONObject(sty);
            String userInfo = data.optString("userInfo");
            String token = data.optString("token");
            UserCentreControl.getInstance().setToken(token);
            return GsonUtils.getInstance().fromJson(userInfo, UserInfo.class);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;

    }

    @Override
    public String getUrlToken() {
        return "user/login/";
    }


}
