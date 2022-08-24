package com.tsdl.common.sdk.base;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.Resources;
import android.widget.Toast;

import androidx.annotation.IdRes;
import androidx.annotation.StringRes;

public class SingleToast extends Toast {

    private static Toast mToast;
    private static final int DURATION = 3000;

    private SingleToast(Context context) {
        super(context);
    }

    public static Toast makeText(Context context, CharSequence text, int duration) {
        if (mToast != null) {
            mToast.cancel();
        }
        mToast = Toast.makeText(context, text, duration);
        return mToast;
    }

    @SuppressLint("ResourceType")
    public static Toast makeText(Context context, @StringRes int resId, int duration) {
        return makeText(context, context.getResources().getText(resId), duration);
    }

    @SuppressLint("WrongConstant")
    public static Toast makeText(Context context, CharSequence text) {
        return makeText(context, text, DURATION);
    }

    @SuppressLint("ResourceType")
    public static Toast makeText(Context context, @IdRes int resId)
            throws Resources.NotFoundException {
        return makeText(context, context.getResources().getText(resId));
    }
}
