package com.yuepang.yuepang.control;

import android.content.Context;

import com.yuepang.yuepang.interFace.LoginSuccess;
import com.yuepang.yuepang.location.Location;
import com.yuepang.yuepang.model.UserInfo;

import java.util.ArrayList;
import java.util.List;

/**
 */

public class UserCentreControl {

    private static UserCentreControl centreControl;

    private UserInfo info = new UserInfo();// 用户信息

    private String token; // 用户登录token

    /**
     * 用户纬度信息
     */
    private double latitude;

    /**
     * 用户经度信息
     */
    private double longitude;

    private List<LoginSuccess> loginSuccesses = new ArrayList<>();

    public static synchronized UserCentreControl getInstance() {
        if (centreControl == null) {
            centreControl = new UserCentreControl();
        }
        return centreControl;
    }

    public void addLoginMonitor(LoginSuccess loginSuccess) {
        if (loginSuccess != null) {
            loginSuccesses.add(loginSuccess);
        }
    }

    public void relieveLoginMonitor(LoginSuccess loginSuccess) {
        loginSuccesses.remove(loginSuccess);
    }

    public void addLoginSuccesses() {
        if (loginSuccesses.size() > 0) {
            for (LoginSuccess loginSuccess : loginSuccesses) {
                loginSuccess.loginSuccess();
            }
        }
    }

    public void initLocation(Context context){
        Location location = new Location(context);
        location.initLocationOption();
    }


    public void outLogin() {

    }

    public UserInfo getInfo() {
        return info;
    }

    public void setInfo(UserInfo info) {
        this.info = info;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
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
}
