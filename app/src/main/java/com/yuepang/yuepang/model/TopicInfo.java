package com.yuepang.yuepang.model;

public class TopicInfo {

    private String title;

    private int id;

    private String topicCreateTime;

    private int user;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTime() {
        return topicCreateTime;
    }

    public void setTime(String time) {
        this.topicCreateTime = time;
    }

    public int getUser() {
        return user;
    }

    public void setUser(int user) {
        this.user = user;
    }
}
