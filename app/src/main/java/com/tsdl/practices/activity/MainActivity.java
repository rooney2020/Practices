package com.tsdl.practices.activity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.tsdl.practices.R;
import com.tsdl.practices.adapter.ContentAdapter;
import com.tsdl.practices.base.BaseActivity;
import com.tsdl.practices.databinding.ActivityMainBinding;
import com.tsdl.practices.manager.DataManager;
import com.tsdl.practices.model.DemoDetail;

public class MainActivity extends BaseActivity {

    private ActivityMainBinding binding;
    private Handler mHandler = new Handler(Looper.myLooper()) {
        @Override
        public void handleMessage(@NonNull Message msg) {
            startActivity(new Intent(MainActivity.this, SmsActivity.class));
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        initView();
//        initDB();
//        mHandler.sendMessageDelayed(mHandler.obtainMessage(), 3000);
        if (ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.INTERNET) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.INTERNET}, 1);
        }
        checkPermission();
    }

    private void initView() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        ContentAdapter adapter = new ContentAdapter(this, DEMOS);
        binding.rvContent.setLayoutManager(layoutManager);
        binding.rvContent.setAdapter(adapter);
    }

    private void initDB() {
        DataManager.getsInstance(this);
    }

    private final DemoDetail[] DEMOS = {
            new DemoDetail(R.string.app_name, R.string.blank, null),
            new DemoDetail(R.string.activity_calculator, R.string.blank, this, CalculatorActivity.class),
            new DemoDetail(R.string.activity_kotlin, R.string.blank, this, KotlinActivity.class),
            new DemoDetail(R.string.activity_game, R.string.blank, this, GameActivity.class),
            new DemoDetail(R.string.activity_music, R.string.blank, this, MusicActivity.class),
            new DemoDetail(R.string.activity_sms, R.string.blank, this, SmsActivity.class),
            new DemoDetail(R.string.activity_tab_layout, R.string.blank, this, TabLayoutActivity.class),
            new DemoDetail(R.string.activity_web_config, R.string.blank, this, WebConfigActivity.class),
    };

    private void checkPermission() {
        if (Build.VERSION.SDK_INT >= 23) {
//            int write = checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE);
//            int read = checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE);
//            if (write != PackageManager.PERMISSION_GRANTED || read != PackageManager.PERMISSION_GRANTED) {
            int network = checkSelfPermission(Manifest.permission.INTERNET);
            if (network != PackageManager.PERMISSION_GRANTED) {
                requestPermissions(new String[]{Manifest.permission.INTERNET}, 300);
            }
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case 1:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    // initMediaPlayer();
                } else {
                    Toast.makeText(this, "拒绝权限将无法使用程序", Toast.LENGTH_SHORT).show();
                    finish();
                }
                break;
            default:
        }
    }

}