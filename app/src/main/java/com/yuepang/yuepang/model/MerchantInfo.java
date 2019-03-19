package com.yuepang.yuepang.model;

/**
 * Created by xugh on 2019/3/11.
 */

public class MerchantInfo {

    private String name;// 名称

    private String location;// 位置

    private String picture;// 图片

    private String tel;//

    private String details;// 详情


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
}
