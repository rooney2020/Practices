package com.tsdl.practices.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;

import androidx.annotation.NonNull;
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
            new DemoDetail(R.string.item_callback, R.string.blank, null),
    };

}