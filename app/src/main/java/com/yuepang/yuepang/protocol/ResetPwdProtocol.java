package com.yuepang.yuepang.protocol;

import com.android.common.enums.HttpType;
import com.yuepang.yuepang.activity.BaseActivity;
import com.yuepang.yuepang.model.AuthCodeInfo;

import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Type;

/**
 * Created by xugh on 2019/3/27.
 */

public class ResetPwdProtocol extends JsonProtocol {
    private String pwd; // 密码

    private AuthCodeInfo info;

    public ResetPwdProtocol(BaseActivity baseActivity, AuthCodeInfo info, String pwd) {
        super(baseActivity, null);
        this.info = info;
        this.pwd = pwd;
    }

    @Override
    public void creatDataJson(JSONObject json) {
        try {
            json.put("username", info.getmTel());
            json.put("code", info.getCode());
            json.put("mValidCode", info.getmValidCode());
            json.put("password", pwd);
        } catch (JSONException e) {
            e.printStackTrace();
        }

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
