package com.yuepang.yuepang.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by xugh on 2019/4/2.
 * <p>
 * 商品信息类
 */

public class GoodInfo implements Parcelable {

    private MerchantInfo info;

    private String picUrl; // 商品图片地址

    private String title;// 商品名称

    private String msg;// 商品简介

    public GoodInfo(){

    }

    protected GoodInfo(Parcel in) {
        info = in.readParcelable(MerchantInfo.class.getClassLoader());
        picUrl = in.readString();
        title = in.readString();
        msg = in.readString();
    }

    public static final Creator<GoodInfo> CREATOR = new Creator<GoodInfo>() {
        @Override
        public GoodInfo createFromParcel(Parcel in) {
            return new GoodInfo(in);
        }

        @Override
        public GoodInfo[] newArray(int size) {
            return new GoodInfo[size];
        }
    };

    public MerchantInfo getInfo() {
        return info;
    }

    public void setInfo(MerchantInfo info) {
        this.info = info;
    }

    public String getPicUrl() {
        return picUrl;
    }

    public void setPicUrl(String picUrl) {
        this.picUrl = picUrl;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeParcelable(info, flags);
        dest.writeString(picUrl);
        dest.writeString(title);
        dest.writeString(msg);
    }
}
