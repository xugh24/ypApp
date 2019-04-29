package com.yuepang.yuepang.control;

import android.content.Context;

import com.android.common.activity.ActivityManage;
import com.yuepang.yuepang.activity.BaseActivity;
import com.yuepang.yuepang.activity.FirstActivity;
import com.yuepang.yuepang.db.YuePangExternalDB;
import com.yuepang.yuepang.interFace.LoginSuccess;
import com.yuepang.yuepang.location.LatLng;
import com.yuepang.yuepang.location.Location;
import com.yuepang.yuepang.model.UserInfo;

import java.util.ArrayList;
import java.util.List;

/**
 */

public class UserCentreControl {

    private static UserCentreControl centreControl;

    private UserInfo info = new UserInfo();// 用户信息

    private String token = ""; // 用户登录token

    private LatLng latLng;


    private List<LoginSuccess> loginSuccesses = new ArrayList<>();

    public static synchronized UserCentreControl getInstance() {
        if (centreControl == null) {
            centreControl = new UserCentreControl();
        }
        return centreControl;
    }

    /**
     * 程序初始化方法
     */
    public void init(Context context) {
        YuePangExternalDB.getInstance(context);
        initLocation(context);
    }

    public void addLoginMonitor(LoginSuccess loginSuccess) {
        if (loginSuccess != null) {
            loginSuccesses.add(loginSuccess);
        }
    }

    public void relieveLoginMonitor(LoginSuccess loginSuccess) {
        loginSuccesses.remove(loginSuccess);
    }

    public void loginSuccesses() {
        if (loginSuccesses.size() > 0) {
            for (LoginSuccess loginSuccess : loginSuccesses) {
                loginSuccess.loginSuccess();
            }
        }
    }

    /**
     * 初始化位置信息
     */
    private void initLocation(Context context) {
        Location location = new Location(context);
        location.initLocationOption();
    }


    public void outLogin(BaseActivity activity) {
        token = "";//置空当前token
        info.clear();// 清理用户信息
        activity.startActivity(FirstActivity.class);
        ActivityManage.finishAll(FirstActivity.class);
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


    public LatLng getLatLng() {
        if (latLng == null) {
            latLng = new LatLng(0, 0);
        }
        return latLng;
    }
}
