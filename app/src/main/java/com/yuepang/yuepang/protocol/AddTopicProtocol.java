package com.yuepang.yuepang.protocol;

import com.yuepang.yuepang.activity.BaseActivity;

import org.json.JSONObject;

/**
 * Created by xugh on 2019/3/22.
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
        return "/yuepang/addTopic/";
    }

    @Override
    public Object onResponse(int code, JSONObject response) throws Exception {
        return null;
    }
}
