package com.android.common.control;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by xugh on 2019/4/25.
 */

public abstract class BaseDataControl {

    protected Context mContext;

    protected abstract String getSpName();

    protected SharedPreferences getSharedPreference() {
        return mContext.getSharedPreferences(getSpName(), Context.MODE_PRIVATE);
    }

    protected SharedPreferences.Editor getEdit() {
        return mContext.getSharedPreferences(getSpName(), Context.MODE_PRIVATE).edit();
    }

    protected String getString(String key) {
        return getSharedPreference().getString(key, null);
    }

    protected void setString(String key, String name) {
        getEdit().putString(key, name).commit();
    }



}
