package com.yuepang.yuepang.protocol;

import com.yuepang.yuepang.Util.LogUtils;
import com.yuepang.yuepang.activity.BaseActivity;
import com.yuepang.yuepang.model.TopicItemInfo;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by xugh on 2019/3/22.
 */


public class GetChatProtocol extends JsonProtocol {

    private int id;

    public GetChatProtocol(BaseActivity baseActivity, int id) {
        super(baseActivity);
        this.id = id;
    }

    @Override
    public void creatDataJson(JSONObject json) throws Exception {
        json.put("topicId", id);
    }

    @Override
    public String getUrlToken() {
        return "yuepang/chatList/";
    }

    @Override
    public Object onResponse(int code, String response) throws Exception {
        List<TopicItemInfo> infos = new ArrayList<>();
        JSONArray array = new JSONArray(response);
        if (array.length() > 0) {
            for (int i = 0; i < array.length(); i++) {
                TopicItemInfo info = new TopicItemInfo();
                JSONObject json = new JSONObject(array.optString(i));
                LogUtils.e("json"+json);
                info.setName(json.optString("username"));
                info.setUrl(json.optString("userAvatar"));
                info.setId(json.optInt("id"));
                info.setMsg(json.optString("msg"));
                infos.add(info);
            }
        }
        return infos;
    }
}
