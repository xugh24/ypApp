package com.yuepang.yuepang.protocol;

import com.android.common.enums.HttpType;
import com.yuepang.yuepang.activity.BaseActivity;
import com.yuepang.yuepang.control.UserCentreControl;

import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Type;

/**
 */

public class SendMsgProtocol extends JsonProtocol {

    private int id;

    private String msg;

    public SendMsgProtocol(BaseActivity baseActivity, int id, String msg) {
        super(baseActivity, null);
        this.msg = msg;
        this.id = id;
    }

    @Override
    public void creatDataJson(JSONObject json) {
        try {
            json.put("username", UserCentreControl.getInstance().getInfo().getTel());
            json.put("topicId", id);
            json.put("msg", msg);
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
        return "yuepang/sendMsg/";
    }

}
