package com.yuepang.yuepang.protocol;

import com.android.common.enums.HttpType;
import com.yuepang.yuepang.activity.BaseActivity;
import com.yuepang.yuepang.control.UserCentreControl;

import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Type;

/**
 * Created by xugh on 2019/4/3.
 * <p>
 * 根据商圈id  或者地理位置信息获得精选商品目录
 */

public class HandpickInfoProtocol extends JsonProtocol {

    private final static String LONGITUDE = "longitude";// 经度

    private final static String LATITUDE = "latitude";// 纬度

    private final static String AREAID = "areaId";// 商圈id

    private int areaId;


    public HandpickInfoProtocol(BaseActivity baseActivity, int areaId) {
        super(baseActivity,null);
        this.areaId = areaId;
    }

    @Override
    public void creatDataJson(JSONObject json)  {
        try {
            json.put(LONGITUDE, UserCentreControl.getInstance().getLatLng().longitude);
            json.put(LATITUDE, UserCentreControl.getInstance().getLatLng().latitude);
            json.put(AREAID, areaId);
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
