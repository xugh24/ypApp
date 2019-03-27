package com.yuepang.yuepang.model;

public class AuthCodeInfo {

    private String mTel;// 用户手机号
    private String mValidCode;// 校验码
    private String code;// 验证码


    public String getmTel() {
        return mTel;
    }

    public void setmTel(String mTel) {
        this.mTel = mTel;
    }

    public String getmValidCode() {
        return mValidCode;
    }

    public void setmValidCode(String mValidCode) {
        this.mValidCode = mValidCode;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

}
