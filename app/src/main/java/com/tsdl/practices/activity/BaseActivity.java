package com.tsdl.practices.activity;

import android.app.Activity;
import android.widget.Toast;

import com.tsdl.practices.util.LogUtils;

public class BaseActivity extends Activity {

    private final String TAG = BaseActivity.this.getClass().getName();

    @Override
    protected void onResume() {
        super.onResume();
        toast(TAG);
    }

    protected void toast(String msg) {
        Toast.makeText(BaseActivity.this, msg, Toast.LENGTH_SHORT).show();
    }

    private void log(String msg) {
        LogUtils.logD(TAG, msg);
    }
}
