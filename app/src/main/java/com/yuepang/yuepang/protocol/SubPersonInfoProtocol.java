package com.yuepang.yuepang.protocol;

import android.content.Context;

import com.android.common.utils.GsonUtils;
import com.android.common.utils.LogUtils;
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
    protected String getUrlToken() {
        return "user/modifyUserInfo/";
    }


    @Override
    protected UserInfo analysis(String st) {
        return GsonUtils.getInstance().fromJson(st, UserInfo.class);
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
