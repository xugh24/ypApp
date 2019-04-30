package com.yuepang.yuepang.protocol;

import com.android.common.utils.GsonUtils;
import com.google.gson.reflect.TypeToken;
import com.yuepang.yuepang.activity.BaseActivity;
import com.yuepang.yuepang.interFace.LoadCallBack;
import com.yuepang.yuepang.model.TopicInfo;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;
import java.util.List;

/**
 * 获得话题接口
 */

public class GetTopicProtocol extends JsonProtocol<List<TopicInfo>> {
    public GetTopicProtocol(BaseActivity baseActivity, LoadCallBack<List<TopicInfo>> callBack) {
        super(baseActivity, callBack);
    }

    @Override
    public void creatDataJson(JSONObject json) {

    }


    @Override
    public String getUrlToken() {
        return "yuepang/topicList/";
    }

    @Override
    protected List<TopicInfo> analysis(String st) {
        List<TopicInfo> list = new ArrayList<>();
        JSONArray data = null;
        try {
            data = new JSONArray(st);
            if (data.length() > 0) {
                for (int i = 0; i < data.length(); i++) {
                    String s = data.optString(i);
                    TopicInfo info = GsonUtils.getInstance().fromJson(s, TopicInfo.class);
                    list.add(info);
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return list;
    }

}
