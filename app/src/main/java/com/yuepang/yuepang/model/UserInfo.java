/*
 * File Name: UserInfo.java 
 * History:
 * Created by Administrator on 2013-7-30
 */
package com.yuepang.yuepang.model;

import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;


public class UserInfo {

    private String userName ; // 用户名
    private int sex;// 性别
    private String birthday;// 生日
    private String tel ;// 手机号
    private String headerImgUrl ; // 头像地址
    private String pwd;// 密码
    private String id;// 身份证
    private String name;// 姓名

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public int getSex() {
        return sex;
    }

    public void setSex(int sex) {
        this.sex = sex;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getHeaderImgUrl() {
        return headerImgUrl;
    }

    public void setHeaderImgUrl(String headerImgUrl) {
        this.headerImgUrl = headerImgUrl;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    // ==========================================================================
    // Getters
    // ==========================================================================

    // ==========================================================================
    // Setters
    // ==========================================================================



}
