package com.tsdl.practices.activity;

import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.tsdl.practices.R;
import com.tsdl.practices.adapter.ContentAdapter;
import com.tsdl.practices.databinding.ActivityMainBinding;
import com.tsdl.practices.model.DemoDetail;

public class MainActivity extends BaseActivity {

    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        initView();
    }

    private void initView() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        ContentAdapter adapter = new ContentAdapter(this, DEMOS);
        binding.rvContent.setLayoutManager(layoutManager);
        binding.rvContent.setAdapter(adapter);
    }

    private static final DemoDetail[] DEMOS = {
            new DemoDetail(R.string.app_name, R.string.blank, null),
            new DemoDetail(R.string.activity_calculator, R.string.blank, CalculatorActivity.class),
            new DemoDetail(R.string.activity_kotlin, R.string.blank, KotlinActivity.class),
            new DemoDetail(R.string.activity_game, R.string.blank, GameActivity.class),
    };
}