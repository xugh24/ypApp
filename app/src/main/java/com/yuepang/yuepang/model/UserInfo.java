package com.yuepang.yuepang.model;

public class UserInfo {

    private String sex;// 性别
    private String birthday;// 生日
    private String tel;// 手机号
    private String avatar; // 头像地址
    private String pwd;// 密码
    private String nick_name;// 昵称
    private int id;


    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
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
        return avatar;
    }

    public void setHeaderImgUrl(String headerImgUrl) {
        this.avatar = headerImgUrl;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNick() {
        return nick_name;
    }

    public void setNick(String nick) {
        this.nick_name = nick;
    }

    public String getSexTxte() {
        if ("1".equals(sex)) {
            return "男";
        }
        return "女";
    }

    /**
     * 清理用户信息
     */
    public void clear() {
        sex = "1";
        birthday = null;
        tel = null;// 手机号
        avatar = null; // 头像地址
        pwd = null;// 密码
        nick_name = null;// 昵称
        id = 0;
    }


    // ==========================================================================
    // Getters
    // ==========================================================================

    // ==========================================================================
    // Setters
    // ==========================================================================


}
