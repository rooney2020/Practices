package com.tmec.common.sdk.core;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.Configuration;
import android.graphics.drawable.Drawable;
import android.os.Build;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;

import java.util.ArrayList;
import java.util.List;

public class SkinManager {

    @SuppressLint("StaticFieldLeak")
    private static final SkinManager INSTANCE = new SkinManager();

    private final List<IUiModeChangeListener> mListeners = new ArrayList<>();

    private Context mContext;
    private boolean mIsNight;

    private SkinManager() {

    }

    public static SkinManager getInstance() {
        return INSTANCE;
    }

    public void initBaseContext(Context context) {
        mContext = context;
    }

    public boolean isNight() {
        return mIsNight;
    }

    public Drawable getDrawable(Context context, int resId) {
        if (mContext != null) {
            return mContext.getResources().getDrawable(resId, mContext.getTheme());
        }
        if (context != null) {
            return context.getResources().getDrawable(resId, context.getTheme());
        }
        return null;
    }

    public int getColor(Context context, int resId) {
        if (mContext != null) {
            return mContext.getResources().getColor(resId, mContext.getTheme());
        }
        if (context != null) {
            return context.getResources().getColor(resId, context.getTheme());
        }
        return 0;
    }

    @RequiresApi(api = Build.VERSION_CODES.R)
    public void onConfigurationChanged(@NonNull Configuration newConfig) {
        mIsNight = newConfig.isNightModeActive();
        onSkinModeChange();
    }

    private void onSkinModeChange() {
        for (IUiModeChangeListener listener : mListeners) {
            if (listener != null) {
                listener.onSkinModeChange();
            }
        }
    }

    public void register(IUiModeChangeListener listener) {
        if (listener != null) {
            mListeners.add(listener);
        }
    }
}
