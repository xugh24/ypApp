package com.yuepang.yuepang.protocol;

import com.yuepang.yuepang.Util.LogUtils;
import com.yuepang.yuepang.activity.BaseActivity;
import com.yuepang.yuepang.model.TopicInfo;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by xugh on 2019/3/22.
 */

public class GetTopicProtocol extends JsonProtocol {
    public GetTopicProtocol(BaseActivity baseActivity) {
        super(baseActivity);
    }

    @Override
    public void creatDataJson(JSONObject json) throws Exception {

    }

    @Override
    public String getUrlToken() {
        return "/yuepang/topicList/";
    }

    @Override
    public List<TopicInfo> onResponse(int code, String response) throws Exception {
        List<TopicInfo> infos = new ArrayList<>();
        JSONArray array = new JSONArray(response);
        if (array.length() > 0) {
            for (int i = 0; i < array.length(); i++) {
                try {
                    JSONObject json = new JSONObject(array.optString(i));
                    LogUtils.e("json "+json);
                    TopicInfo info = new TopicInfo();
                    info.setId(json.optInt("id"));
                    info.setTitle(json.optString("title"));
                    info.setTime(json.optString("topicCreateTime"));
                    infos.add(info);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }
        return infos;
    }
}
