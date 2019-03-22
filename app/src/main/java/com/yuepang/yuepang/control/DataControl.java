package com.yuepang.yuepang.control;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;


import org.json.JSONArray;

import java.util.ArrayList;
import java.util.List;

public class DataControl {

    public final static String SP_NAME = "yuepang";
    public final static String KEY_LOGINGNAME = "KEY_LOGINGNAME";// 登录名
    public final static String KEY_PWD = "KEY_PWD";// 密码

    private static DataControl dControl;
    private Context mContext;

    private Object mUserLock = new Object();

    public static synchronized DataControl getInstance(Context mContext) {
        if (dControl == null) {
            dControl = new DataControl(mContext);
        }
        return dControl;
    }

    private DataControl(Context mContext) {
        this.mContext = mContext.getApplicationContext();
    }

    public SharedPreferences getSharedPreference() {
        return mContext.getSharedPreferences(SP_NAME, Context.MODE_PRIVATE);
    }

    public Editor getEdit() {
        return mContext.getSharedPreferences(SP_NAME, Context.MODE_PRIVATE).edit();
    }

    private String getString(String key) {
        return getSharedPreference().getString(key, null);
    }

    private void setString(String key, String name) {
        getEdit().putString(key,name).commit();
    }

    public String getLoginName() {
        return getString(KEY_LOGINGNAME);
    }

    public void setLoginName(String name){
        setString(KEY_LOGINGNAME,name);
    }

   public void setPwd(String pwd){
        setString(KEY_PWD,pwd);
   }

   public String getPwd(){
       return getString(KEY_PWD);
   }


}
