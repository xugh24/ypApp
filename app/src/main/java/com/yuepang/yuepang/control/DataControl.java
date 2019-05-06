package com.yuepang.yuepang.control;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.text.TextUtils;


import com.android.common.control.BaseDataControl;
import com.yuepang.yuepang.Util.LogUtils;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;
import java.util.List;

public class DataControl extends BaseDataControl {

    public final static String KEY_LOGINGNAME = "KEY_LOGINGNAME";// 登录名
    public final static String KEY_PWD = "KEY_PWD";// 密码
    public final static String KEY_COLLECT = "KEY_COLLECT";

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

    public JSONArray getCollect() {
        String key = UserCentreControl.getInstance().getInfo().getId() + "_" + KEY_COLLECT;
        String data = getString(key);
        if (!TextUtils.isEmpty(data)) {
            try {
                JSONArray array = new JSONArray(data);
                LogUtils.e("array" + array);
                return array;
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    public void setCollect(int id) {
        String key = UserCentreControl.getInstance().getInfo().getId() + "_" + KEY_COLLECT;
        JSONArray array = getCollect();
        if (array == null) {
            array = new JSONArray();
        }
        array.put(id);
        setString(key, array.toString());
    }


    public void removeCollect(int id) {
        String key = UserCentreControl.getInstance().getInfo().getId() + "_" + KEY_COLLECT;
        JSONArray array = getCollect();
        if (array != null && array.length() > 0) {
            for (int i = 0; i < array.length(); i++) {
                if (id == array.optInt(i)) {
                    array.remove(i);
                    setString(key, array.toString());
                    return;
                }
            }
        }
    }

    public boolean checkCollect(int id) {
        String key = UserCentreControl.getInstance().getInfo().getId() + "_" + KEY_COLLECT;
        JSONArray array = getCollect();
        if (array != null && array.length() > 0) {
            for (int i = 0; i < array.length(); i++) {
                if (id == array.optInt(i)) {
                    return true;
                }
            }
        }
        return false;
    }
}
