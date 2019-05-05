package com.yuepang.yuepang.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 */

public class MerchantInfo implements Parcelable {

    private int id;

    private String name;// 名称

    private String address;// 位置

    private String image;// 图片

    private String tel;//

    private String detail;// 详情

    private double longitude;// 经度

    private double latitude;// 纬度

    private float discount;// 折扣

    public MerchantInfo(){

    }


    protected MerchantInfo(Parcel in) {
        name = in.readString();
        address = in.readString();
        image = in.readString();
        tel = in.readString();
        detail = in.readString();
        longitude = in.readDouble();
        latitude = in.readDouble();
        discount = in.readFloat();
        id=in.readInt();
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
        return address;
    }

    public void setLocation(String location) {
        this.address = location;
    }

    public String getPicture() {
        return image;
    }

    public void setPicture(String picture) {
        this.image = picture;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getDetails() {
        return detail;
    }

    public void setDetails(String details) {
        this.detail = details;
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
        dest.writeString(address);
        dest.writeString(image);
        dest.writeString(tel);
        dest.writeString(detail);
        dest.writeDouble(longitude);
        dest.writeDouble(latitude);
        dest.writeFloat(discount);
        dest.writeInt(id);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
