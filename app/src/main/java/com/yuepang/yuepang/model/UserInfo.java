package com.yuepang.yuepang.model;

import org.json.JSONArray;

import java.util.List;

public class UserInfo {

    private String sex;// 性别
    private String signature;// 生日
    private String tel;// 手机号
    private String avatar; // 头像地址
    private String nick_name;// 昵称
    private int id;
    private List<Integer> favorite;


    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getBirthday() {
        return signature;
    }

    public void setBirthday(String birthday) {
        this.signature = birthday;
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
        signature = null;
        tel = null;// 手机号
        avatar = null; // 头像地址
        nick_name = null;// 昵称
        id = 0;
        favorite = null;
    }

    public List<Integer> getFavorite() {
        return favorite;
    }

    public void setFavorite(List<Integer> favorite) {
        this.favorite = favorite;
    }


    // ==========================================================================
    // Getters
    // ==========================================================================

    // ==========================================================================
    // Setters
    // ==========================================================================


}
