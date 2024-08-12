package com.tsdl.practices.util;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

public class ShareUtils {

    private static final String TAG = ShareUtils.class.getSimpleName();

    private static final String SP_NAME = "test";
    public static final String SP_KEY_USERNAME = "username";
    public static final String SP_KEY_PASSWORD = "password";
    public static final String SP_KEY_TOKEN = "token";
    public static final String SP_KEY_EXPIRE = "expire";
    public static final String SP_KEY_ADDRESS = "address";
    public static final String SP_KEY_IS_EXPIRE = "is_expire";

    @SuppressLint("StaticFieldLeak")
    private static Context mContext;

    private static SharedPreferences sp;

    public static void init(Context context) {
        Log.i(TAG, "Now is running init()");
        if (context == null) {
            Log.e(TAG, "context is null");
            return;
        }
        mContext = context;
        sp = mContext.getSharedPreferences(SP_NAME, Context.MODE_PRIVATE);
    }

    public static String getString(String key) {
        return sp.getString(key, "");
    }

    public static long getLong(String key) {
        return sp.getLong(key, 0);
    }

    public static int getInt(String key) {
        return sp.getInt(key, 0);
    }

    public static float getFloat(String key) {
        return sp.getFloat(key, 0);
    }

    public static boolean getBoolean(String key) {
        return sp.getBoolean(key, false);
    }

    public static boolean contains(String key) {
        return sp.contains(key);
    }

    public static void putString(String key, String value) {
        sp.edit().putString(key, value).apply();
    }

    public static void putInt(String key, int value) {
        sp.edit().putInt(key, value).apply();
    }

    public static void putLong(String key, long value) {
        sp.edit().putLong(key, value).apply();
    }

    public static void putFloat(String key, float value) {
        sp.edit().putFloat(key, value).apply();
    }

    public static void putBoolean(String key, boolean value) {
        sp.edit().putBoolean(key, value).apply();
    }

}
