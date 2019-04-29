package com.yuepang.yuepang.protocol;

import com.android.common.enums.HttpType;
import com.yuepang.yuepang.Util.LogUtils;
import com.yuepang.yuepang.activity.BaseActivity;
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


public class GetChatProtocol extends JsonProtocol {

    private int id;

    public GetChatProtocol(BaseActivity baseActivity, int id) {
        super(baseActivity,null);
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
    public HttpType getHttpType() {
        return null;
    }



    @Override
    public String getUrlToken() {
        return "yuepang/chatList/";
    }

//    @Override
//    public Object onResponse(int code, String response) throws Exception {
//        List<TopicItemInfo> infos = new ArrayList<>();
//        JSONArray array = new JSONArray(response);
//        if (array.length() > 0) {
//            for (int i = 0; i < array.length(); i++) {
//                TopicItemInfo info = new TopicItemInfo();
//                JSONObject json = new JSONObject(array.optString(i));
//                LogUtils.e("json"+json);
//                info.setName(json.optString("username"));
//                info.setUrl(json.optString("userAvatar"));
//                info.setId(json.optInt("id"));
//                info.setMsg(json.optString("msg"));
//                infos.add(info);
//            }
//        }
//        return infos;
//    }
}
