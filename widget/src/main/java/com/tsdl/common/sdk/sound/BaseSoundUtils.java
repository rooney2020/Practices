package com.tsdl.common.sdk.sound;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;

import com.tsdl.common.sdk.R;

public class BaseSoundUtils {
    public static int SOUND_DEFAULT = 0;

    /**
     * initAttribute
     */
    public static int initAttribute(Context context, AttributeSet attrs) {
        int soundEffect = 0;
        if (attrs != null) {
            TypedArray ta = null;
            try {
                ta = context.obtainStyledAttributes(attrs, R.styleable.BaseSound);
                soundEffect = ta.getInteger(R.styleable.BaseSound_soundEffect,
                        SOUND_DEFAULT);
            } finally {
                if (ta != null) {
                    ta.recycle();
                }
            }
        }
        return soundEffect;
    }
}
