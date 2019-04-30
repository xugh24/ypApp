package com.yuepang.yuepang.protocol;

import com.android.common.enums.HttpType;
import com.yuepang.yuepang.activity.BaseActivity;
import com.yuepang.yuepang.control.UserCentreControl;
import com.yuepang.yuepang.interFace.LoadCallBack;

import org.json.JSONException;
import org.json.JSONObject;

/**
 */

public class SendMsgProtocol extends JsonProtocol {

    private int id;

    private String msg;

    public SendMsgProtocol(BaseActivity baseActivity, LoadCallBack callBack, int id, String msg) {
        super(baseActivity, callBack);
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
    public String getUrlToken() {
        return "yuepang/sendMsg/";
    }

}
