package com.yuepang.yuepang.protocol;

import com.android.common.enums.HttpType;
import com.yuepang.yuepang.activity.BaseActivity;

import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Type;

/**
 * Created by xugh on 2019/3/27.
 *
 * 获得验证码接口
 */

public class GetCodeProtocol extends JsonProtocol {

    private String tel;

    public GetCodeProtocol(BaseActivity baseActivity, String tel) {
        super(baseActivity,null);
        this.tel = tel;
    }

    @Override
    public void creatDataJson(JSONObject json) {
        try {
            json.put("tel", tel);
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
        return null;
    }

}
