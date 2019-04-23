package com.android.common.utils;

import com.google.gson.Gson;

public class GsonUtils {
    public static Gson instance;

    public static Gson getInstance() {
        if (instance==null){
            synchronized (GsonUtils.class){
                if (instance==null)
                    instance=new Gson();
            }
        }
    return instance;
    }

}
