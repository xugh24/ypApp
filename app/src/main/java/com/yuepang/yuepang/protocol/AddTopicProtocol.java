package com.yuepang.yuepang.protocol;

import com.android.common.enums.HttpType;
import com.yuepang.yuepang.activity.BaseActivity;

import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Type;

/**
 * 创建话题接口
 */

public class AddTopicProtocol extends JsonProtocol {

    private String title;

    public AddTopicProtocol(BaseActivity baseActivity, String title) {
        super(baseActivity,null);
        this.title = title;
    }

    @Override
    protected void creatDataJson(JSONObject json)  {
        try {
            json.put("title", title);
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
        return "yuepang/addTopic/";
    }

}
