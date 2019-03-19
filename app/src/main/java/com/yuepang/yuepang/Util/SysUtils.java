package com.yuepang.yuepang.Util;

import android.os.Build;
import android.os.Environment;
import android.os.StatFs;
import android.text.TextUtils;

/**
 * Created by xugh on 2019/3/19.
 */

public class SysUtils {

    public static String[] getStoragePath(Object storageManager, boolean withSlash) {
        String sdcardPath = null;
        String internalPath = null;
        if (Build.VERSION.SDK_INT >= 14) {
            Object sm = storageManager;
            if (sm != null) {
                Object[] svArray = ReflectUtils.invoke(Object[].class, sm.getClass(), "getVolumeList", null,
                        sm, null);
                // StorageVolume[] svArray = sm.getVolumeList();
                if (svArray != null) {
                    for (int i = 0; i < svArray.length; i++) {
                        String path = ReflectUtils.invoke(String.class, svArray[i].getClass(), "getPath",
                                null, svArray[i], null);
                        if (!FileUtils.isWriteable(path)) {
                            LogUtils.w("path read only:" + path);
                            continue;
                        }
                        LogUtils.i("path:" + path);
                        if ((ReflectUtils.invoke(boolean.class, svArray[i].getClass(), "isRemovable", null,
                                svArray[i], null))) {
                            sdcardPath = path + (withSlash ? "/" : "");
                        } else {
                            internalPath = path + (withSlash ? "/" : "");
                        }
                    }
                }
            }
        } else if (isSDCardAvailable()) {
            sdcardPath = Environment.getExternalStorageDirectory().getAbsolutePath() + (withSlash ? "/" : "");
            if (!TextUtils.isEmpty(sdcardPath)) {
                if (!FileUtils.isWriteable(sdcardPath) || getAvailableSpace(sdcardPath) < 1024) {
                    sdcardPath = null;
                }
            }

        }
        LogUtils.i("sdcardPath:" + sdcardPath);
        LogUtils.i("internalPath:" + internalPath);
        return new String[]{sdcardPath, internalPath};
    }

    private static StatFs sStatFs = null;

    public static long getAvailableSpace(String path) {
        long availableSpace = -1l;
        if (path == null) {
            return availableSpace;
        }
        try {
            if (sStatFs == null)
                sStatFs = new StatFs(path);
            else
                sStatFs.restat(path);
            availableSpace = sStatFs.getAvailableBlocks() * (long) sStatFs.getBlockSize();
        } catch (Exception e) {
            LogUtils.e(e);
        }

        return availableSpace;
    }


    public static boolean isSDCardAvailable() {
        /**
         * mk_u950-userdebug 4.4.4 KTU84P 6e8311657c test-keys 机型 java.lang.NullPointerException at
         * android.os.Environment.getStorageState(Environment.java:719) at
         * android.os.Environment.getExternalStorageState(Environment.java:694) at
         * de.robv.android.xposed.XposedBridge.invokeOriginalMethodNative(Native Method) at
         * de.robv.android.xposed.XposedBridge.handleHookedMethod(XposedBridge.java:631) at
         * android.os.Environment.getExternalStorageState(Native Method) at rn.b(SystemUtils.java:483) 在某些机型上出现该空指针异常
         * 此处给进行catch处理 2015-11-03
         */
        try {
            return Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}