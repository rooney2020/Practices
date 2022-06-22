package com.tsdl.practices.util;

import android.util.Log;

public class LogUtils {

    private static boolean debugFlag = true;

    public static void logI(String tag, String msg) {
        if (debugFlag) {
            Log.i(tag, msg);
        }
    }

    public static void logD(String tag, String msg) {
        if (debugFlag) {
            Log.d(tag, msg);
        }
    }

    public static void logE(String tag, String msg) {
        if (debugFlag) {
            Log.e(tag, msg);
        }
    }

    public static void logV(String tag, String msg) {
        if (debugFlag) {
            Log.v(tag, msg);
        }
    }

    public static void logW(String tag, String msg) {
        if (debugFlag) {
            Log.w(tag, msg);
        }
    }
}
