package com.yuepang.yuepang.location;

import com.yuepang.yuepang.Util.LogUtils;

/**
 * Created by xugh on 2019/4/2.
 */

public class LatLng {

    public double longitude;

    public double latitude;

    public LatLng(double latitude, double longitude) {
        this.latitude = latitude;
        this.longitude = longitude;
        LogUtils.e("新建地址 ：" + toString());
    }
    public String toString() {
        return "latitude :" + latitude + "   longitude :" + longitude;
    }
}
