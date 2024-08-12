package com.tsdl.practices.base;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.tsdl.common.sdk.base.SingleToast;
import com.tsdl.common.util.LogUtils;

import java.util.Timer;
import java.util.TimerTask;

public class BaseActivity extends AppCompatActivity {

    public final String TAG = BaseActivity.this.getClass().getName();
    public final String LIFECYCLE_TAG = "LifeCycle";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        logD(LIFECYCLE_TAG + " onCreate");
    }

    @Override
    protected void onStart() {
        super.onStart();
        logD(LIFECYCLE_TAG + " onStart");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        logD(LIFECYCLE_TAG + " onRestart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        logD(LIFECYCLE_TAG + " onResume");
    }

    @Override
    protected void onPause() {
        super.onPause();
        logD(LIFECYCLE_TAG + " onPause");
    }

    @Override
    protected void onStop() {
        super.onStop();
        logD(LIFECYCLE_TAG + " onStop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        logD(LIFECYCLE_TAG + " onDestroy");
    }

    protected void toast(String msg) {
        SingleToast.makeText(BaseActivity.this, msg, SingleToast.LENGTH_SHORT).show();
    }

    public void logI(String msg) {
        Log.i(TAG, msg);
    }

    public void logW(String msg) {
        Log.w(TAG, msg);
    }

    public void logD(String msg) {
        Log.d(TAG, msg);
    }

    public void logE(String msg) {
        Log.e(TAG, msg);
    }

    public void logV(String msg) {
        Log.v(TAG, msg);
    }

    /**
     * 页面跳转
     *
     * @param clz      目标
     */
    public void go(Class clz) {
        go(clz, false);
    }

    /**
     * 页面跳转
     *
     * @param clz      目标
     * @param isFinish 是否关闭当前Activity
     */
    public void go(Class clz, boolean isFinish) {
        startActivity(new Intent(this, clz));
        if (isFinish) {
            finish();
        }
    }

    /**
     * 页面跳转
     *
     * @param cls      目标
     * @param isFinish 是否关闭当前Activity
     */
    protected void toIntent(Class cls, boolean isFinish) {
        startActivity(new Intent(this, cls));
    }

    /**
     * 延迟delay毫秒后执行任务
     *
     * @param task  任务
     * @param delay 毫秒
     */
    protected void sleep(TimerTask task, int delay) {
        Timer timer = new Timer();
        timer.schedule(task, delay);
    }
}
