package com.tmec.common.sdk.base;

import android.app.Application;
import android.content.res.Configuration;
import android.os.Build;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;

import com.tmec.common.sdk.core.SkinManager;

public class BaseApplication extends Application {

    private static final String TAG = BaseApplication.class.getSimpleName();

    @Override
    public void onCreate() {
        super.onCreate();
        SkinManager.getInstance().initBaseContext(getBaseContext());
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
    }

    @RequiresApi(api = Build.VERSION_CODES.R)
    @Override
    public void onConfigurationChanged(@NonNull Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        SkinManager.getInstance().onConfigurationChanged(newConfig);
    }
}
