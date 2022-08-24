/*
 * Copyright © 2019-Now Human Horizons (Shanghai) Cloud Computing Technology Co., Ltd. All
 * Rights Reserved.
 */
package com.tmec.common.sdk.tab;
/**
 * author : xianqingzhang
 * e-mail : Xianqing_Zhang@human-horizons.com
 * time   : 19-12-27
 * desc   : This is HHSoundUtils
 * version: 1.0
 */

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;

import com.tmec.common.sdk.R;

public class BaseSoundUtils {
    public static int SOUND_DEFAULT = 0;

    /**
     * initAttribute
     *
     * @param context
     * @param attrs
     * @return
     */
    public static int initAttribute(Context context, AttributeSet attrs) {
        int hrytSoundEffect = 0;
        if (attrs != null) {
            TypedArray ta = null;
            try {
                ta = context.obtainStyledAttributes(attrs, R.styleable.HHSound);
                hrytSoundEffect = ta.getInteger(R.styleable.HHSound_hryt_soundEffect,
                        SOUND_DEFAULT);
            } finally {
                if (ta != null) {
                    ta.recycle();
                }
            }
        }
        return hrytSoundEffect;
    }
}
