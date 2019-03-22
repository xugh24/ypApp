package com.yuepang.yuepang.model;

public class UserInfo {

    private int sex;// 性别
    private String birthday;// 生日
    private String tel;// 手机号
    private String headerImgUrl; // 头像地址
    private String pwd;// 密码
    private String nick;// 昵称


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


    public String getName() {
        return nick;
    }

    public void setName(String name) {
        this.nick = name;
    }




    // ==========================================================================
    // Getters
    // ==========================================================================

    // ==========================================================================
    // Setters
    // ==========================================================================


}
