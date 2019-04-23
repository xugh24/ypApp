package com.android.common.utils;


import android.nfc.Tag;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Switch;

import java.io.PrintWriter;
import java.io.StringWriter;

public class LogUtils {
    //==========================================================================
    // Constants
    //==========================================================================
    // Max value is 3984
    private static final int MAX_LOG_LINE_LENGTH = 2048;
    private static final int E = 0x01;
    private static final int V = 0x02;
    private static final int I = 0x03;
    private static final int D = 0x04;
    private static final int W = 0x05;

    private static String className;//类名
    private static String methodName;//方法名
    private static int lineNumber;//行数

    //==========================================================================
    // Fields
    //==========================================================================
    private static String sTag = "yuepang";
    private static boolean sDebuggable = true;
    private static long sTimestamp = 0;


    /**
     * 获取文件名、方法名、所在行数
     *
     * @param sElements
     */
    private static void getMethodNames(StackTraceElement[] sElements) {
        className = sElements[1].getFileName();
        methodName = sElements[1].getMethodName();
        lineNumber = sElements[1].getLineNumber();
    }

    public static void e(final String message) {
        if (sDebuggable) {
            getMethodNames(new Throwable().getStackTrace());
            log(E, message);
        }
    }

    public static void v(String message) {
        if (sDebuggable) {
            getMethodNames(new Throwable().getStackTrace());
            log(V, message);
        }
    }

    public static void i(String message) {
        if (sDebuggable) {
            getMethodNames(new Throwable().getStackTrace());
            log(I, message);
        }
    }

    public static void d(String message) {
        if (sDebuggable) {
            getMethodNames(new Throwable().getStackTrace());
            log(D, message);
        }
    }

    public static void w(String message) {
        if (sDebuggable) {
            getMethodNames(new Throwable().getStackTrace());
            log(W, message);
        }
    }

    public static void e(Throwable tr) {
        if (sDebuggable) {
            Log.e(sTag, "!!!error!!!", tr);
        }
    }

    private static void log(int type, String msg) {
        if (null != msg && msg.length() > 0) {
            int start = 0;
            int end = 0;
            int len = msg.length();
            Log.i(sTag, "className=== " + className);
            Log.i(sTag, "methodName=== " + methodName);
            Log.i(sTag, "lineNumber=== " + lineNumber);
            while (true) {
                start = end;
                end = start + MAX_LOG_LINE_LENGTH;
                if (end >= len) {
                    logByTyoe(type, msg.substring(start, len));
                    break;
                } else {
                    logByTyoe(type, msg.substring(start, len));
                }
            }
        }
    }

    private static void logByTyoe(int type, String msg) {
        switch (type) {
            case E:
                Log.e(sTag, msg);
                break;
            case I:
                Log.i(sTag, msg);
                break;
            case W:
                Log.w(sTag, msg);
                break;
            case D:
                Log.d(sTag, msg);
                break;
            case V:
                Log.v(sTag, msg);
                break;
        }
    }


    //==========================================================================
    // Inner/Nested Classes
    //==========================================================================
}
