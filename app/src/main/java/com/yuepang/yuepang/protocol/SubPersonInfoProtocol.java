package com.yuepang.yuepang.protocol;

import android.content.Context;

import com.android.common.utils.GsonUtils;
import com.yuepang.yuepang.interFace.LoadCallBack;
import com.yuepang.yuepang.model.UserInfo;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by xugh on 2019/4/29.
 */

public class SubPersonInfoProtocol extends JsonProtocol<UserInfo> {
    private UserInfo info;

    public SubPersonInfoProtocol(Context context, LoadCallBack callBack, UserInfo info) {
        super(context, callBack);
        this.info = info;
    }

    @Override
    protected Object getUrlToken() {
        return "user/modifyUserInfo/";
    }


    @Override
    protected UserInfo analysis(JSONObject json) {
        String data  = json.optString("data");
        return GsonUtils.getInstance().fromJson(data,UserInfo.class);
    }

    @Override
    protected void creatDataJson(JSONObject reqJson) {

        try {
            reqJson.put("nick_name", info.getNick());
            reqJson.put("signature", info.getBirthday());
            reqJson.put("sex", info.getSex());
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
