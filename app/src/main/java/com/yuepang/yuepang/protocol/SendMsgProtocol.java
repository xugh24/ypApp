package com.yuepang.yuepang.protocol;

import com.yuepang.yuepang.activity.BaseActivity;
import com.yuepang.yuepang.control.UserCentreControl;

import org.json.JSONObject;

/**
 */

public class SendMsgProtocol extends JsonProtocol {

    private int id;

    private String msg;

    public SendMsgProtocol(BaseActivity baseActivity, int id, String msg) {
        super(baseActivity);
        this.msg = msg;
        this.id = id;
    }

    @Override
    public void creatDataJson(JSONObject json) throws Exception {
        json.put("username", UserCentreControl.getInstance().getInfo().getTel());
        json.put("topicId", id);
        json.put("msg", msg);
    }

    @Override
    public String getUrlToken() {
        return "yuepang/sendMsg/";
    }

    @Override
    public Object onResponse(int code, String response) throws Exception {
        return null;
    }
}
