package com.yuepang.yuepang.protocol;

import com.yuepang.yuepang.activity.BaseActivity;

import org.json.JSONObject;

/**
 */

public class AddTopicProtocol extends JsonProtocol {

    private String title;

    public AddTopicProtocol(BaseActivity baseActivity, String title) {
        super(baseActivity);
        this.title = title;
    }

    @Override
    public void creatDataJson(JSONObject json) throws Exception {
        json.put("title", title);
    }

    @Override
    public String getUrlToken() {
        return "yuepang/addTopic/";
    }

    @Override
    public Object onResponse(int code, String response) throws Exception {
        return new JSONObject(response);
    }
}
