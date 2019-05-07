package com.yuepang.yuepang.protocol;

import com.android.common.enums.HttpType;
import com.yuepang.yuepang.activity.BaseActivity;
import com.yuepang.yuepang.interFace.LoadCallBack;

import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Type;

/**
 * 创建话题接口
 */

public class AddTopicProtocol <T> extends JsonProtocol {

    private String title;

    public AddTopicProtocol(BaseActivity baseActivity, LoadCallBack<T> callBack, String title) {
        super(baseActivity,callBack);
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
    public String getUrlToken() {
        return "yuepang/addTopic/";
    }

}
