package com.tsdl.practices;

import com.tsdl.common.sdk.base.BaseApplication;
import com.tsdl.practices.util.ShareUtils;
import com.tsdl.practices.util.ToastUtils;

public class PracticesApplication extends BaseApplication {

    @Override
    public void onCreate() {
        super.onCreate();
        ShareUtils.init(getApplicationContext());
        ToastUtils.init(getApplicationContext());
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
    }
}
