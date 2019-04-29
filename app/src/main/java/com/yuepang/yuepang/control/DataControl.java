package com.yuepang.yuepang.control;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;


import com.android.common.control.BaseDataControl;

import org.json.JSONArray;

import java.util.ArrayList;
import java.util.List;

public class DataControl extends BaseDataControl {

    public final static String KEY_LOGINGNAME = "KEY_LOGINGNAME";// 登录名
    public final static String KEY_PWD = "KEY_PWD";// 密码

    private static DataControl dControl;

    private DataControl(Context mContext) {
        this.mContext = mContext.getApplicationContext();
    }

    public static synchronized DataControl getInstance(Context mContext) {
        if (dControl == null) {
            dControl = new DataControl(mContext);
        }
        return dControl;
    }

    public String getLoginName() {
        return getString(KEY_LOGINGNAME);
    }

    public void setLoginName(String name) {
        setString(KEY_LOGINGNAME, name);
    }

    public void setPwd(String pwd) {
        setString(KEY_PWD, pwd);
    }

    public String getPwd() {
        return getString(KEY_PWD);
    }

    @Override
    protected String getSpName() {
        return "yuepang";
    }
}
