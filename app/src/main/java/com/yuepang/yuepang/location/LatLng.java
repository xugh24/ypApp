package com.yuepang.yuepang.location;

/**
 * Created by xugh on 2019/4/2.
 */

public class LatLng {

    public double longitude;

    public double latitude;

    public LatLng(double latitude, double longitude) {
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public String toString() {
        return "latitude :" + latitude + "   longitude :" + longitude;
    }
}
