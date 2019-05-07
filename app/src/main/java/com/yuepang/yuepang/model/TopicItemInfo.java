package com.yuepang.yuepang.model;

/**
 */

public class TopicItemInfo {

    private String username;

    private String msg;

    private int id;

    private String userAvatar;

    public String getName() {
        return username;
    }

    public void setName(String name) {
        this.username = name;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUrl() {
        return userAvatar;
    }

    public void setUrl(String url) {
        this.userAvatar = url;
    }
}
