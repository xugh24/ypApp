/**
 * File Name: Protocol.java
 * 提供一个可供参考的数据上行模式
 *
 * @author xugh
 */
package com.android.common.protocol;
import android.content.Context;
import android.os.Build;
import com.android.common.inter.HttpCallBack;
import com.android.common.utils.AppManage;
import com.android.common.utils.DeviceUtils;
import com.android.common.utils.LogUtils;
import org.json.JSONException;
import org.json.JSONObject;


public abstract class BaseJsonProtocol<T> extends BaseFirstProtocol implements HttpCallBack {

    //header 信息头
    private final static String HEADER = "header";
    private final static String VERSION = "version";
    private final static String VERSIONNAME = "versionName";
    private final static String TOKEN = "token";
    private final static String TIME = "time";
    private final static String APPNAME = "appName";
    //设备信息
    private final static String DEVICE = "device";
    private final static String DEVICEID = "deviceId";// 设备ID
    private final static String ANDROID_VERSION = "android_version";//
    private final static String ANDROID_VERSIONNAME = "android_versionName";
    private final static String NETTYPE = "netType";
    private final static String NETSERVER = "netServer";
    private final static String IMSI = "imsi";
    private final static String MAC = "MAC";
    private final static String DEVICETYPE = "deviceType";
    // data
    private final static String REQUESTDATA = "requestData";


    public BaseJsonProtocol(Context context) {
        super(context);
    }


    // ==========================================================================
    // Constructors
    // ==========================================================================

    // ==========================================================================
    // Getters
    // ==========================================================================

    /**
     * 创建请求Json
     * Json 共分为三层 HEADER 、 DEVICE 、data 、其中HEADER层和data层参数加密
     */
    protected JSONObject getRequestJson() {
        JSONObject json = new JSONObject();
        try {
            json.put(HEADER, getHeadJson()); // 插入头信息
            json.put(DEVICE, getDeviceJson()); // 设备信息
            json.put(REQUESTDATA, getReqData()); // 设备信息
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return json;
    }

    /**
     *
     */
    protected JSONObject getHeadJson() {
        JSONObject json = new JSONObject();
        try {
            json.put(VERSION, AppManage.getVersionCode(mContext));
            json.put(VERSIONNAME, AppManage.getVersionName(mContext));
            json.put(APPNAME, AppManage.getAppName(mContext));
            //TODO
            json.put(TOKEN, "");
            json.put(TIME, System.currentTimeMillis());
        } catch (Exception e) {
            LogUtils.e(e);
            return null;
        }
        return json;
    }

    /**
     * 设备参数因为手机权限或者Android版本机型等问题可能无法获得
     */
    public JSONObject getDeviceJson() {
        JSONObject json = new JSONObject();
        try {
            json.put(DEVICEID, DeviceUtils.getIMEI(mContext));// IMEI
            json.put(IMSI, DeviceUtils.getIMSI(mContext));
            json.put(ANDROID_VERSION, Build.VERSION.SDK_INT);
            json.put(ANDROID_VERSIONNAME, Build.VERSION.RELEASE);
            json.put(NETTYPE, DeviceUtils.getNetType(mContext));// 网络类型
            json.put(NETSERVER, DeviceUtils.getNetOP(mContext));// 运营商
            json.put(MAC, DeviceUtils.getMAC(mContext));
            json.put(DEVICETYPE, Build.MODEL);
        } catch (Exception e) {
            LogUtils.e(e);
        }
        return json;
    }

    /**
     * 创建自定义请求参数
     */
    private String getReqData() {
        JSONObject json = new JSONObject();
        try {
            creatDataJson(json);
            return encrypt(json.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    protected abstract String encrypt(String s);

    public abstract void creatDataJson(JSONObject json) throws Exception;

}
