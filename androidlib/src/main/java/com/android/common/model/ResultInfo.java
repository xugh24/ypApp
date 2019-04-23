package com.android.common.model;

/**
 * Created by xugh on 2019/4/15.
 */

public class ResultInfo<T> {
    private int code;

    private String msg;

    private T data;

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

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
