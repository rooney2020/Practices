package com.tsdl.practices.activity;

import android.os.Bundle;

import androidx.databinding.DataBindingUtil;

import com.tsdl.practices.R;
import com.tsdl.practices.base.BaseActivity;
import com.tsdl.practices.config.WebConfig;
import com.tsdl.practices.databinding.ActivityWebConfigBinding;
import com.tsdl.practices.util.ShareUtils;
import com.tsdl.practices.util.ToastUtils;
import com.tsdl.practices.util.TypeUtils;

public class WebConfigActivity extends BaseActivity {

    private ActivityWebConfigBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
    }

    protected void initView() {
        binding = DataBindingUtil.setContentView(this, R.layout.activity_web_config);
        initNavBar();
        String address = ShareUtils.getString(ShareUtils.SP_KEY_ADDRESS);
        if (TypeUtils.isNotEmpty(address)) {
            WebConfig.setBaseUrl(address);
        }
        binding.etAddress.setText(address);
        binding.btnConfirm.setOnClickListener(v -> {
            if (TypeUtils.isEmpty(binding.etAddress.getText().toString())) {
                ToastUtils.show("IP地址不能为空");
                return;
            }
            WebConfig.setBaseUrl(binding.etAddress.getText().toString());
            ShareUtils.putString(ShareUtils.SP_KEY_ADDRESS, binding.etAddress.getText().toString());
            go(LoginActivity.class);
            finish();
        });
    }

    private void initNavBar() {
        binding.navBar.btnBack.setOnClickListener(v -> {
            onBackPressed();
        });
        binding.navBar.tvTitle.setText("网络配置");
        binding.navBar.btnWebConfig.setOnClickListener(v -> {
            if (!this.getClass().getSimpleName().equals(WebConfigActivity.class.getSimpleName())) {
                go(WebConfigActivity.class, true);
            }
        });
    }
}