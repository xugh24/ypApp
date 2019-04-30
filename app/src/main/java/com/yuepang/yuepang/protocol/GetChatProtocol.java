package com.yuepang.yuepang.protocol;

import com.android.common.enums.HttpType;
import com.android.common.utils.GsonUtils;
import com.yuepang.yuepang.Util.LogUtils;
import com.yuepang.yuepang.activity.BaseActivity;
import com.yuepang.yuepang.interFace.LoadCallBack;
import com.yuepang.yuepang.model.TopicItemInfo;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * 获得话题聊天信息接口
 */


public class GetChatProtocol extends JsonProtocol <List<TopicItemInfo>> {

    private int id;

    public GetChatProtocol(BaseActivity baseActivity, LoadCallBack<List<TopicItemInfo>> callBack, int id) {
        super(baseActivity, callBack);
        this.id = id;
    }

    @Override
    protected void creatDataJson(JSONObject json) {
        try {
            json.put("topicId", id);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }


    @Override
    public String getUrlToken() {
        return "yuepang/chatList/";
    }

    @Override
    protected List<TopicItemInfo> analysis(String data) {
        List<TopicItemInfo> infos = new ArrayList<>();

        try {
            JSONArray array = new JSONArray(data);
            if (array.length() > 0) {
                for (int i = 0; i < array.length(); i++) {
                    String s = array.optString(i);
                    TopicItemInfo info = GsonUtils.getInstance().fromJson(s,TopicItemInfo.class);
                    infos.add(info);
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return infos;
    }

}
