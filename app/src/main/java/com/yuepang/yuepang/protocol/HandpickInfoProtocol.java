package com.yuepang.yuepang.protocol;

import com.yuepang.yuepang.activity.BaseActivity;
import com.yuepang.yuepang.control.UserCentreControl;

import org.json.JSONObject;

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
        super(baseActivity);
        this.areaId = areaId;
    }

    @Override
    public void creatDataJson(JSONObject json) throws Exception {
        json.put(LONGITUDE, UserCentreControl.getInstance().getLatLng().longitude);
        json.put(LATITUDE, UserCentreControl.getInstance().getLatLng().latitude);
        json.put(AREAID, areaId);
    }

    @Override
    public String getUrlToken() {
        return null;
    }

    @Override
    public Object onResponse(int code, String response) throws Exception {
        return null;
    }
}
