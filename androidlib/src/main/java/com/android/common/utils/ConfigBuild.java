package com.android.common.utils;

import android.content.Context;
import android.os.Environment;

import java.io.File;

/**
 * Created by xugh on 2019/4/15.
 * <p>
 * 程序常用 常量
 */

public class ConfigBuild {
    public static String MAIN_RUL = "";
    public static final String YUEPANG = "yuepang";

    /**
     * 获得图片缓存的地址
     */
    public static String getImageCacheDir(Context context) {
        String path = null;
        if (Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState())) {
            path = Environment.getExternalStorageDirectory() + "/" + YUEPANG + "/img_cache";
        } else {
            path = context.getCacheDir().getAbsolutePath();
        }
        File dir = new File(path);
        if (!dir.exists()) {
            dir.mkdirs();
        }
        LogUtils.e("图片缓存地址  " + path);
        return path;
    }
}
