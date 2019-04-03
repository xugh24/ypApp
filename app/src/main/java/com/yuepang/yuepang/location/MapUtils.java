package com.yuepang.yuepang.location;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.widget.Toast;

import com.yuepang.yuepang.Util.AppManage;

/**
 * Created by xugh on 2019/4/2.
 * 地图管理类
 * 定位系统使用不同的坐标系
 * 1、WGS-84坐标系：地心坐标系，GPS原始坐标体系
 * 在中国，任何一个地图产品都不允许使用GPS坐标，据说是为了保密。
 * 2、GCJ-02 坐标系：国测局坐标，火星坐标系
 * 1）国测局02年发布的坐标体系，它是一种对经纬度数据的加密算法，即加入随机的偏差。
 * 2）互联网地图在国内必须至少使用GCJ-02进行首次加密，不允许直接使用WGS-84坐标下的地理数据，同时任何坐标系均不可转换为WGS-84坐标。
 * 3）是国内最广泛使用的坐标体系，高德、腾讯、Google中国地图都使用它。
 * 3、CGCS2000坐标系：国家大地坐标系
 * 该坐标系是通过中国GPS 连续运行基准站、 空间大地控制网以及天文大地网与空间地网联合平差建立的地心大地坐标系统。
 * 4、BD-09坐标系
 * 百度中国地图所采用的坐标系，由GCJ-02进行进一步的偏移算法得到。
 * 5、搜狗坐标系
 * 搜狗地图所采用的坐标系，由GCJ-02进行进一步的偏移算法得到。
 * 6、图吧坐标系
 * 图吧地图所采用的坐标系，由GCJ-02进行进一步的偏移算法得到。
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
