package com.tsdl.common.sdk.util;

import android.content.Context;

public class DensityUtil {
    private static float scale;

    public static final float DEFAULT_NUMBER = 0.5f;

    /**
     * dip2px.
     *
     * @param context context .
     * @param dpValue dp value.
     * @return px value.
     */
    public static int dip2px(Context context, float dpValue) {
        if (scale == 0) {
            scale = context.getResources().getDisplayMetrics().density;
        }
        return (int) (dpValue * scale + DEFAULT_NUMBER);
    }
}
