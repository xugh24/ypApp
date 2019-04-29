package com.android.common.model;

import org.json.JSONObject;

/**
 * Created by xugh on 2019/4/15.
 * <p>
 * 协议请求类的返回的数据
 * code 为返回码
 * msg 为放回参数
 */

public class ResultInfo {

    public static int HTTP_NINET = -1;

    public static int HTTP_OK = 200;

    public static int LOGIN_INVALID = 444;

    private int code;

    private String msg;

    private JSONObject data;

    public ResultInfo(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public JSONObject getData() {
        return data;
    }

    public void setData(JSONObject data) {
        this.data = data;
    }


    public static ResultInfo getNoNetInfo() {
        return getErrorInfo("网络连接失败");
    }

    public static ResultInfo getErrorInfo(String e) {
        return new ResultInfo(HTTP_NINET, e);
    }
}
