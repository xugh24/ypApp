package com.yuepang.yuepang.protocol;

import com.android.common.enums.HttpType;
import com.android.common.utils.GsonUtils;
import com.yuepang.yuepang.activity.BaseActivity;
import com.yuepang.yuepang.control.UserCentreControl;
import com.yuepang.yuepang.interFace.LoadCallBack;
import com.yuepang.yuepang.model.AuthCodeInfo;
import com.yuepang.yuepang.model.UserInfo;

import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Type;

/**
 * Created by xugh on 2019/3/27.
 */

public class RegisterProtocol extends JsonProtocol <UserInfo>{


    private String pwd; // 密码

    private String tel;

    public RegisterProtocol(BaseActivity baseActivity, LoadCallBack loadCallBack, String tel, String pwd) {
        super(baseActivity,loadCallBack);
        this.tel = tel;
        this.pwd = pwd;
    }

    @Override
    public void creatDataJson(JSONObject json) {
        try {
            json.put("username", tel);
            json.put("password", pwd);
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
        return "yuepang/register/";
    }

}
