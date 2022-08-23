package com.tsdl.practices.base;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.tmec.common.sdk.base.SingleToast;
import com.tsdl.common.util.LogUtils;

public class BaseActivity extends AppCompatActivity {

    public final String TAG = BaseActivity.this.getClass().getName();
    public final String LIFECYCLE_TAG = "LifeCycle";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        log(LIFECYCLE_TAG, TAG + " onCreate");
    }

    @Override
    protected void onStart() {
        super.onStart();
        log(LIFECYCLE_TAG, TAG + " onStart");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        log(LIFECYCLE_TAG, TAG + " onRestart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        log(LIFECYCLE_TAG, TAG + " onResume");
    }

    @Override
    protected void onPause() {
        super.onPause();
        log(LIFECYCLE_TAG, TAG + " onPause");
    }

    @Override
    protected void onStop() {
        super.onStop();
        log(LIFECYCLE_TAG, TAG + " onStop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        log(LIFECYCLE_TAG, TAG + " onDestroy");
    }

    protected void toast(String msg) {
        SingleToast.makeText(BaseActivity.this, msg, SingleToast.LENGTH_SHORT).show();
    }

    private void log(String msg) {
        log(TAG, msg);
    }

    private void log(String tag, String msg) {
        LogUtils.logD(tag, msg);
    }
}
