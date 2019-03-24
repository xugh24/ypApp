package com.yuepang.yuepang.control;

import com.yuepang.yuepang.info.UserInfo;
import com.yuepang.yuepang.interFace.LoginSuccess;

import java.util.ArrayList;
import java.util.List;

/**
 */

public class UserCentreControl {

    private static UserCentreControl centreControl;

    private UserInfo info = new UserInfo();// 用户信息

    private String token;

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

    public void relieveLoginMonitor(LoginSuccess loginSuccess){
        loginSuccesses.remove(loginSuccess);
    }
    public void addLoginSuccesses() {
        if (loginSuccesses.size() > 0) {
            for (LoginSuccess loginSuccess : loginSuccesses) {
                loginSuccess.loginSuccess();
            }
        }
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
}
