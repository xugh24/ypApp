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

    private String image; // 商品图片地址

    private String name;// 商品名称

    private String detail;// 商品简介

    private int favorite;//

    private int id;

    private int shop;


    public GoodInfo(){

    }


    protected GoodInfo(Parcel in) {
        info = in.readParcelable(MerchantInfo.class.getClassLoader());
        image = in.readString();
        name = in.readString();
        detail = in.readString();
        id = in.readInt();
        shop = in.readInt();
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
        return image;
    }

    public void setPicUrl(String picUrl) {
        this.image = picUrl;
    }

    public String getTitle() {
        return name;
    }

    public void setTitle(String title) {
        this.name = title;
    }

    public String getMsg() {
        return detail;
    }

    public void setMsg(String msg) {
        this.detail = msg;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {

        dest.writeParcelable(info, flags);
        dest.writeString(image);
        dest.writeString(name);
        dest.writeString(detail);
        dest.writeInt(id);
        dest.writeInt(shop);
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getShop() {
        return shop;
    }

    public void setShop(int shop) {
        this.shop = shop;
    }

    public int getFavorite() {
        return favorite;
    }

    public void setFavorite(int favorite) {
        this.favorite = favorite;
    }
}
