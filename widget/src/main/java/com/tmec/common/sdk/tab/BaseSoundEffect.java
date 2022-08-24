/*
 * Copyright © 2019-Now Human Horizons (Shanghai) Cloud Computing Technology Co., Ltd. All
 * Rights Reserved.
 */
package com.tmec.common.sdk.tab;
/**
 * author : xianqingzhang
 * e-mail : Xianqing_Zhang@human-horizons.com
 * time   : 19-12-27
 * desc   : This is HHSoundEffect
 * version: 1.0
 */

import static android.content.Context.AUDIO_SERVICE;

import android.annotation.SuppressLint;
import android.content.Context;
import android.media.AudioManager;

public class BaseSoundEffect {
    private static final BaseSoundEffect OURINSTANCE = new BaseSoundEffect();

    public static BaseSoundEffect getInstance() {
        return OURINSTANCE;
    }

    /**
     * setViewSoundEffect
     *
     * @param context
     * @param effectType
     */
    @SuppressLint("LongLogTag")
    public void setViewSoundEffect(Context context, SoundEffectEnum effectType) {
        AudioManager audioManager = (AudioManager) context.getSystemService(AUDIO_SERVICE);
        audioManager.playSoundEffect(effectType.value());
    }

    /**
     * setViewSoundEffect
     *
     * @param context
     * @param effectType
     */
    @SuppressLint("LongLogTag")
    public void setViewSoundEffect(Context context, int effectType) {
        AudioManager audioManager = (AudioManager) context.getSystemService(AUDIO_SERVICE);
        audioManager.playSoundEffect(effectType);
    }

    /**
     * setViewSoundEffect
     *
     * @param context
     * @param effectType
     * @param volume
     */
    public void setViewSoundEffect(Context context, SoundEffectEnum effectType, float volume) {
        AudioManager audioManager = (AudioManager) context.getSystemService(AUDIO_SERVICE);
        audioManager.playSoundEffect(effectType.value(), volume);
    }
}
