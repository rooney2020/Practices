package com.tsdl.practices.activity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;

import androidx.databinding.DataBindingUtil;

import com.tsdl.practices.R;
import com.tsdl.practices.base.BaseActivity;
import com.tsdl.practices.databinding.ActivityGameBinding;

public class GameActivity extends BaseActivity implements View.OnClickListener {

    private ActivityGameBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_game);
        initView();
    }

    private void initView() {
        binding.btnStart.setOnClickListener(this);
        binding.btnRestart.setOnClickListener(this);
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_start:
            case R.id.btn_restart:
                binding.gameView.startNewGame();
                break;
            default:
                break;
        }
    }
}