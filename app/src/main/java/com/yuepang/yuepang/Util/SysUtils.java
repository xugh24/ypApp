package com.yuepang.yuepang.Util;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.os.StatFs;
import android.text.TextUtils;

import com.yuepang.yuepang.activity.MerchantDetailActivity;

import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 */

public class SysUtils {

    public static final String YUEPANG = "yuepang";

    /**
     * @return 判断手机存储是否可用，可用返回true
     */
    public static boolean isSDCardAvailable() {
        try {
            return Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }


    /**
     * dp转化为px
     */
    public static int dip2px(Context context, float dipValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dipValue * scale + 0.5f);
    }

    /**
     * px转化dp
     */
    public static int px2dip(Context context, float pxValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);
    }

    /*
    * 将时间转换为时间戳
    */
    public static String dateToStamp(String s) throws ParseException {
        String res;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = simpleDateFormat.parse(s);
        long ts = date.getTime();
        res = String.valueOf(ts);
        return res;
    }

    /*
     * 将时间戳转换为时间
     */
    public static String stampToDate(String s) {
        String res;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        long lt = new Long(s);
        Date date = new Date(lt);
        res = simpleDateFormat.format(date);
        return res;
    }
    /*
     * 将时间戳转换为时间
     */
    public static String stampToDate(long lt) {
        String res;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date(lt);
        res = simpleDateFormat.format(date);
        return res;
    }


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

    /**
     * 获得图片缓存的地址
     */
    public static String getDBDir(Context context) {
        String path = null;
        if (Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState())) {
            path = Environment.getExternalStorageDirectory() + "/" + YUEPANG + "/db";
        } else {
            path = context.getCacheDir().getAbsolutePath();
        }
        File dir = new File(path);
        if (!dir.exists()) {
            dir.mkdirs();
        }
        LogUtils.e("数据库地址  " + path);
        return path;
    }

    /**
     * @param context
     * @return 判断当前网络是否正常
     */
    public static boolean hasNetwork(Context context) {
        ConnectivityManager con = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo workinfo = con.getActiveNetworkInfo();
        if (workinfo == null || !workinfo.isAvailable()) {
            LogUtils.e(" Android hasNetwork 网络异常");
            return false;
        } else {
            LogUtils.e(" Android hasNetwork 网络良好");
        }
        return true;
    }

    /**
     * 拨打电话程序调用
     */
    public static void call(Context context, String phoneNum) {
        Intent intent = new Intent(Intent.ACTION_DIAL);
        Uri data = Uri.parse("tel:" + phoneNum);
        intent.setData(data);
        context.startActivity(intent);
    }
}
