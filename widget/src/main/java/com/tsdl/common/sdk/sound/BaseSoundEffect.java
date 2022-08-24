package com.tsdl.common.sdk.sound;

import static android.content.Context.AUDIO_SERVICE;

import android.annotation.SuppressLint;
import android.content.Context;
import android.media.AudioManager;

public class BaseSoundEffect {
    private static final BaseSoundEffect OUR_INSTANCE = new BaseSoundEffect();

    public static BaseSoundEffect getInstance() {
        return OUR_INSTANCE;
    }

    /**
     * setViewSoundEffect
     */
    @SuppressLint("LongLogTag")
    public void setViewSoundEffect(Context context, SoundEffectEnum effectType) {
        AudioManager audioManager = (AudioManager) context.getSystemService(AUDIO_SERVICE);
        audioManager.playSoundEffect(effectType.value());
    }

    /**
     * setViewSoundEffect
     */
    @SuppressLint("LongLogTag")
    public void setViewSoundEffect(Context context, int effectType) {
        AudioManager audioManager = (AudioManager) context.getSystemService(AUDIO_SERVICE);
        audioManager.playSoundEffect(effectType);
    }

    /**
     * setViewSoundEffect
     */
    public void setViewSoundEffect(Context context, SoundEffectEnum effectType, float volume) {
        AudioManager audioManager = (AudioManager) context.getSystemService(AUDIO_SERVICE);
        audioManager.playSoundEffect(effectType.value(), volume);
    }
}
