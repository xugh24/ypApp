/*
 * File Name: AuthCodeInfo.java 
 * History:
 * Created by zhangzhiyong on 2013-8-7
 */
package com.yuepang.yuepang.model;

public class AuthCodeInfo {

    private String mTel;// 用户手机号
    private String mValidCode;// 校验码
    private String code;// 验证码
    private int codeType;// 验证码类型


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

    public int getCodeType() {
        return codeType;
    }

    public void setCodeType(int codeType) {
        this.codeType = codeType;
    }
}
