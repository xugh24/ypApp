package com.android.common.model;

import android.os.Parcel;
import android.os.Parcelable;

import javax.xml.transform.Result;

/**
 * Created by xugh on 2019/4/15.
 *
 * 支付结果返回信息
 */

public class PayResultInfo implements Parcelable{
    private int resultCode;

    private int errorCode;

    private String resultMsg;

    protected PayResultInfo(Parcel in) {
        resultCode = in.readInt();
        errorCode = in.readInt();
        resultMsg = in.readString();
    }

    public static final Creator<PayResultInfo> CREATOR = new Creator<PayResultInfo>() {
        @Override
        public PayResultInfo createFromParcel(Parcel in) {
            return new PayResultInfo(in);
        }

        @Override
        public PayResultInfo[] newArray(int size) {
            return new PayResultInfo[size];
        }
    };

    public int getResultCode() {
        return resultCode;
    }

    public void setResultCode(int resultCode) {
        this.resultCode = resultCode;
    }

    public int getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }

    public String getResultMsg() {
        return resultMsg;
    }

    public void setResultMsg(String resultMsg) {
        this.resultMsg = resultMsg;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(resultCode);
        dest.writeInt(errorCode);
        dest.writeString(resultMsg);
    }
}
