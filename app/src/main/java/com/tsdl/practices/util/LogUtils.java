package com.tsdl.practices.util;

import android.util.Log;

public class LogUtils {

    public static void logI(String tag, String msg) {
        Log.i(tag, msg);
    }

    public static void logD(String tag, String msg) {
        Log.d(tag, msg);
    }

    public static void logE(String tag, String msg) {
        Log.e(tag, msg);
    }

    public static void logV(String tag, String msg) {
        Log.v(tag, msg);
    }

    public static void logW(String tag, String msg) {
        Log.w(tag, msg);
    }
}
