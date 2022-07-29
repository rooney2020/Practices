package com.tsdl.practices.activity;

import android.annotation.SuppressLint;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.view.View;

import androidx.databinding.DataBindingUtil;

import com.tsdl.practices.R;
import com.tsdl.practices.base.BaseActivity;
import com.tsdl.practices.databinding.ActivityMusicBinding;
import com.tsdl.practices.service.MusicService;
import com.tsdl.practices.util.LogUtils;

public class MusicActivity extends BaseActivity implements View.OnClickListener {

    private ActivityMusicBinding binding;
    private MusicService.MusicBinder musicBinder;
    private final ServiceConnection connection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName componentName, IBinder service) {
            LogUtils.logD(TAG, "onServiceConnected");
            musicBinder = (MusicService.MusicBinder) service;
            musicBinder.play();
        }

        @Override
        public void onServiceDisconnected(ComponentName componentName) {
            LogUtils.logD(TAG, "onServiceDisconnected");
        }
    };

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_music);
        initView();
        initData();
        initObserver();
    }

    private void initView() {
        binding.startServiceBtn.setOnClickListener(this);
        binding.stopServiceBtn.setOnClickListener(this);
        binding.bindServiceBtn.setOnClickListener(this);
        binding.unbindServiceBtn.setOnClickListener(this);
    }

    private void initData() {

    }

    private void initObserver() {

    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.start_service_btn:
                Intent startServiceIntent = new Intent(MusicActivity.this, MusicService.class);
                MusicActivity.this.startService(startServiceIntent);
                break;
            case R.id.stop_service_btn:
                Intent stopServiceIntent = new Intent(MusicActivity.this, MusicService.class);
                MusicActivity.this.stopService(stopServiceIntent);
                break;
            case R.id.bind_service_btn:
                Intent bindServiceIntent = new Intent(MusicActivity.this, MusicService.class);
                MusicActivity.this.bindService(bindServiceIntent, connection, BIND_AUTO_CREATE);
                break;
            case R.id.unbind_service_btn:
                MusicActivity.this.unbindService(connection);
                break;
            default:
                break;
        }
    }
}