package com.tsdl.practices.util;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.Log;
import android.widget.Toast;

public class ToastUtils {

    private static final String TAG = ToastUtils.class.getSimpleName();

    @SuppressLint("StaticFieldLeak")
    private static Context mContext;

    private static Toast toast;

    public static void init(Context context) {
        Log.i(TAG, "Now is running init()");
        if (context == null) {
            Log.e(TAG, "context is null");
            return;
        }
        mContext = context;
        toast = Toast.makeText(mContext, "", Toast.LENGTH_SHORT);
    }

    public static void show(String msg) {
        Log.i(TAG, "Now is running show()");
        toast.cancel();
        toast = Toast.makeText(mContext, msg, Toast.LENGTH_SHORT);
        toast.show();
    }

}
