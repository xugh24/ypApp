package com.android.common.utils;

import android.annotation.SuppressLint;
import android.app.ActivityManager;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.telephony.TelephonyManager;
import android.text.TextUtils;


/**
 * Created by xugh on 2019/4/15.
 */

public class DeviceUtils {

    public final static int MOB_TYPE_YIDONG = 1;// 移动
    public final static int MOB_TYPE_LIANTONG = 2;// 联通
    public final static int MOB_TYPE_DIANXIN = 3;// 电信
    public final static int MOB_TYPE_NULL = 0;// 为获取

    public final static int NET_TYPE_MOBILE = 1; // 手机网络类型为 手机
    public final static int NET_TYPE_WIFI = NET_TYPE_MOBILE + 1; // 手机网络类型为 wifi


    /**
     * @param context
     * @return
     */
    @SuppressLint("MissingPermission")
    public static boolean hasNetwork(Context context) {
        ConnectivityManager con = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo workinfo = con.getActiveNetworkInfo();
        if (workinfo == null || !workinfo.isAvailable()) {
            LogUtils.e(" market hasNetwork 网络异常");
            return false;
        } else {

            LogUtils.e(" market hasNetwork 网络良好");
        }
        return true;
    }

    /**
     * 获得手机设备号，注意如果为空则返回"";
     * 6.0系统需要检查权限
     */
    @SuppressLint("MissingPermission")
    public static String getIMEI(Context context) {
        String imei = "";
        final String DEVICEID = "deviceId";
        final String PREFIX = "anz";
        TelephonyManager tm = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
        try {
            imei = tm.getDeviceId();
        } catch (Exception e) {
            LogUtils.e(e);
        }
        return imei;
    }


    /**
     * 获取IMSI 如果IMSI为空或者方法异常 则返回;
     * 6.0系统需要检查权限
     */
    @SuppressLint("MissingPermission")
    public static String getIMSI(Context context) {
        String imsi = null;
        TelephonyManager tm = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
        try {
            imsi = tm.getSubscriberId();
        } catch (Exception e) {
            LogUtils.e(e);
            return "";
        }
        if (TextUtils.isEmpty(imsi)) {
            return "";
        }
        return imsi;
    }

    /**
     * 获得MAC地址，如果方法异常或者为空则返回 "";
     */
    @SuppressLint("MissingPermission")
    public static String getMAC(Context context) {
        String mac = "";
        try {
            WifiManager wm = (WifiManager) context.getSystemService(Context.WIFI_SERVICE);
            WifiInfo info = wm.getConnectionInfo();
            mac = info.getMacAddress();
        } catch (Exception e) {
            LogUtils.e(e);
        }
        return mac;
    }

    /**
     * 获得运营商的类型 MOB_TYPE_YIDONG ： 移动 MOB_TYPE_LIANTONG ：联通 MOB_TYPE_DIANXIN ： 电信 MOB_TYPE_NULL ： 为获取
     */
    public static int getNetOP(Context context) {
        String imsi = getIMSI(context);
        if (imsi == null || imsi.length() == 0) {
            return MOB_TYPE_NULL;
        }
        if (imsi.startsWith("46000") || imsi.startsWith("46002") || imsi.startsWith("46004")
                || imsi.startsWith("46007")) {
            return MOB_TYPE_YIDONG;// 移动
        } else if (imsi.startsWith("46001") || imsi.startsWith("46006") || imsi.startsWith("46009")) {
            return MOB_TYPE_LIANTONG; // 联通
        } else if (imsi.startsWith("46003") || imsi.startsWith("46005") || imsi.startsWith("46011")) {
            return MOB_TYPE_DIANXIN;// 电信
        }
        return MOB_TYPE_NULL;
    }


    /**
     * 获得当前的网络类型,返回值类型
     * <p>
     * NET_TYPE_WIFI : 手机网络类型为 wifi
     * <p>
     * NET_TYPE_MOBILE :手机网络类型为 手机
     */
    @SuppressLint("MissingPermission")
    public static int getNetType(Context context) {
        int type = 0;
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo info = cm.getActiveNetworkInfo();
        if (info != null && info.isAvailable()) {
            if (info.getType() == ConnectivityManager.TYPE_MOBILE) {
                type = NET_TYPE_MOBILE;
            } else if (info.getType() == ConnectivityManager.TYPE_WIFI) {
                type = NET_TYPE_WIFI;
            }
        }
        return type;
    }


    /**
     * @return 获得分配的内存
     * @description
     */
    public static int getMemoryCacheSize(Context context) {
        final int memClass = ((ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE)).getMemoryClass();
        return 1024 * 1024 * memClass;
    }
}