package com.tsdl.practices.base;

import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.tsdl.practices.util.LogUtils;

public class BaseActivity extends AppCompatActivity {

    public final String TAG = BaseActivity.this.getClass().getName();

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
