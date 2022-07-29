package com.tsdl.practices.custom.base;

import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class TmecSkinConfigurationManager {
    private static volatile TmecSkinConfigurationManager sInstance;
    private List<ISkinChangedListener> iColorChangedListeners;
    private boolean mIsNight = true;

    public static TmecSkinConfigurationManager getInstance() {
        if (null == sInstance) {
            synchronized (TmecSkinConfigurationManager.class)  {
                if (null == sInstance) {
                    sInstance = new TmecSkinConfigurationManager();
                }
            }
        }
        return sInstance;
    }

    public TmecSkinConfigurationManager() {
        iColorChangedListeners = new ArrayList<>();
    }

    public void register(ISkinChangedListener listener) {
        if (!iColorChangedListeners.contains(listener)) {
            iColorChangedListeners.add(listener);
        }
    }

    public void onSkinChanged(boolean isNight) {
        Log.e("TmecSkinConfigurationManager", "onSkinChanged: " + isNight);
        mIsNight = isNight;
        for (ISkinChangedListener iColorChangedListener:iColorChangedListeners) {
            iColorChangedListener.onSkinChanged();
        }
    }

    public boolean isNight() {
        return mIsNight;
    }

}
