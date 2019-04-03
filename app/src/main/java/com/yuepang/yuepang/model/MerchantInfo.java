package com.yuepang.yuepang.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 */

public class MerchantInfo implements Parcelable {

    private String name;// 名称

    private String location;// 位置

    private String picture;// 图片

    private String tel;//

    private String details;// 详情

    private double longitude;// 经度

    private double latitude;// 纬度

    private float discount;// 折扣

    public MerchantInfo(){

    }


    protected MerchantInfo(Parcel in) {
        name = in.readString();
        location = in.readString();
        picture = in.readString();
        tel = in.readString();
        details = in.readString();
        longitude = in.readDouble();
        latitude = in.readDouble();
        discount = in.readFloat();
    }

    public static final Creator<MerchantInfo> CREATOR = new Creator<MerchantInfo>() {
        @Override
        public MerchantInfo createFromParcel(Parcel in) {
            return new MerchantInfo(in);
        }

        @Override
        public MerchantInfo[] newArray(int size) {
            return new MerchantInfo[size];
        }
    };

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public float getDiscount() {
        return discount;
    }

    public void setDiscount(float discount) {
        this.discount = discount;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeString(location);
        dest.writeString(picture);
        dest.writeString(tel);
        dest.writeString(details);
        dest.writeDouble(longitude);
        dest.writeDouble(latitude);
        dest.writeFloat(discount);
    }
}
