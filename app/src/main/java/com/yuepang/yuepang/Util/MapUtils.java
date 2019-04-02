package com.yuepang.yuepang.Util;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.widget.Toast;

/**
 * Created by xugh on 2019/4/2.
 */

public class MapUtils {

    /**
     * BD-09 坐标转换成 GCJ-02 坐标
     */
    public static LatLng BD2GCJ(LatLng bd) {
        double x = bd.longitude - 0.0065, y = bd.latitude - 0.006;
        double z = Math.sqrt(x * x + y * y) - 0.00002 * Math.sin(y * Math.PI);
        double theta = Math.atan2(y, x) - 0.000003 * Math.cos(x * Math.PI);

        double lng = z * Math.cos(theta);//lng
        double lat = z * Math.sin(theta);//lat
        return new LatLng(lat, lng);
    }

    /**
     * GCJ-02 坐标转换成 BD-09 坐标
     */
    public static LatLng GCJ2BD(LatLng bd) {
        double x = bd.longitude, y = bd.latitude;
        double z = Math.sqrt(x * x + y * y) + 0.00002 * Math.sin(y * Math.PI);
        double theta = Math.atan2(y, x) + 0.000003 * Math.cos(x * Math.PI);
        double tempLon = z * Math.cos(theta) + 0.0065;
        double tempLat = z * Math.sin(theta) + 0.006;
        return new LatLng(tempLat, tempLon);
    }


    /**
     * 跳转百度地图
     */
    private void goToBaiduMap(Context context, LatLng latLng, String mAddressStr) {
        if (!AppManage.isInstalled("com.baidu.BaiduMap", context)) {
            Toast.makeText(context, "请先安装百度地图客户端", Toast.LENGTH_SHORT).show();
            return;
        }
        Intent intent = new Intent();
        intent.setData(Uri.parse("baidumap://map/direction?destination=latlng:"
                + latLng.latitude + ","
                + latLng.longitude + "|name:" + mAddressStr + // 终点
                "&mode=driving" + // 导航路线方式
                "&src=" + context.getPackageName()));
        context.startActivity(intent); // 启动调用
    }


    /**
     * 跳转高德地图
     */
    private void goToGaodeMap(Context context, LatLng latLng, String mAddressStr) {
        if (!AppManage.isInstalled("com.autonavi.minimap", context)) {
            Toast.makeText(context, "请先安装高德地图客户端", Toast.LENGTH_SHORT).show();
            return;
        }
        LatLng endPoint = BD2GCJ(new LatLng(latLng.latitude, latLng.longitude));//坐标转换
        StringBuffer stringBuffer = new StringBuffer("androidamap://navi?sourceApplication=").append("amap");
        stringBuffer.append("&lat=").append(endPoint.latitude)
                .append("&lon=").append(endPoint.longitude).append("&keywords=" + mAddressStr)
                .append("&dev=").append(0)
                .append("&style=").append(2);
        Intent intent = new Intent("android.intent.action.VIEW", Uri.parse(stringBuffer.toString()));
        intent.setPackage("com.autonavi.minimap");
        context.startActivity(intent);
    }


}
