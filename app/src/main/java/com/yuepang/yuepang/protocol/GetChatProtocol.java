package com.yuepang.yuepang.protocol;

import com.yuepang.yuepang.activity.BaseActivity;

import org.json.JSONObject;

/**
 * Created by xugh on 2019/3/22.
 */



public class GetChatProtocol extends JsonProtocol {

    private int id;

    public GetChatProtocol(BaseActivity baseActivity ,int id) {
        super(baseActivity);
        this.id = id;
    }

    @Override
    public void creatDataJson(JSONObject json) throws Exception {
        json.put("topicId",id);
    }

    @Override
    public String getUrlToken() {
        return "yuepang/chatList/";
    }

    @Override
    public Object onResponse(int code, String response) throws Exception {
        return null;
    }
}
