package com.yuepang.yuepang.protocol;

import android.content.Context;

import com.android.common.utils.GsonUtils;
import com.yuepang.yuepang.control.UserCentreControl;
import com.yuepang.yuepang.interFace.LoadCallBack;
import com.yuepang.yuepang.model.UserInfo;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by xugh on 2019/5/8.
 */

public class CheckCodeProtocol extends JsonProtocol {

    private String tel;

    private String code;

    public CheckCodeProtocol(Context context, LoadCallBack callBack, String tel, String code) {
        super(context, callBack);
        this.tel = tel;
        this.code = code;
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
    protected String getUrlToken() {
        return "user/checkCode/";
    }
    @Override
    protected void creatDataJson(JSONObject reqJson) {
        try {
            reqJson.put("code", code);
            reqJson.put("tel", tel);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
