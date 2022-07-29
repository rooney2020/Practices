package com.tsdl.practices.manager;

import com.tsdl.practices.enums.SkinMode;

import java.util.ArrayList;
import java.util.List;

public class SkinManager {

    private final List<OnSkinModeChangeListener> mListeners = new ArrayList<>();
    private SkinMode mSkinMode = SkinMode.LIGHT;

    private SkinManager() {

    }

    public SkinMode getSkinMode() {
        return mSkinMode;
    }

    public void setSkinMode(SkinMode skinMode) {
        mSkinMode = skinMode;
        onSkinModeChange();
    }

    public void nextSkinMode() {
        mSkinMode = SkinMode.getNextMode(mSkinMode);
        onSkinModeChange();
    }

    private void onSkinModeChange() {
        for (OnSkinModeChangeListener listener : mListeners) {
            if (listener != null) {
                listener.onSkinModeChange(mSkinMode);
            }
        }
    }

    public void register(OnSkinModeChangeListener listener) {
        if (listener != null) {
            mListeners.add(listener);
            listener.onSkinModeChange(mSkinMode);
        }
    }

    public static class Singleton {
        private static final SkinManager INSTANCE = new SkinManager();

        public static SkinManager getInstance() {
            return INSTANCE;
        }
    }

    public interface OnSkinModeChangeListener {
        void onSkinModeChange(SkinMode skinMode);
    }
}
