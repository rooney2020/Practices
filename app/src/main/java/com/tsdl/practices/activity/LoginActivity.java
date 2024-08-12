package com.tsdl.practices.activity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;

import com.bumptech.glide.Glide;
import com.tsdl.practices.R;
import com.tsdl.practices.api.AuthApi;
import com.tsdl.practices.config.WebConfig;
import com.tsdl.practices.databinding.ActivityLoginBinding;
import com.tsdl.practices.entity.auth.CodeResult;
import com.tsdl.practices.entity.auth.LoginForm;
import com.tsdl.practices.entity.auth.LoginResult;
import com.tsdl.practices.util.HttpUtils;
import com.tsdl.practices.util.ShareUtils;
import com.tsdl.practices.util.ToastUtils;
import com.tsdl.practices.util.TypeUtils;
import com.tsdl.practices.base.BaseActivity;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class LoginActivity extends BaseActivity {

    private ActivityLoginBinding binding;
    private String uuid;
    private String token;
    private boolean expire;

    private final Handler mHandler = new Handler(Looper.myLooper()) {
        @Override
        public void handleMessage(@NonNull Message msg) {
            logI("Now is running handleMessage");
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_login);
        initNavBar();
        token = ShareUtils.getString(ShareUtils.SP_KEY_TOKEN);
        expire = ShareUtils.getBoolean(ShareUtils.SP_KEY_IS_EXPIRE);
        if (!TypeUtils.isEmpty(token) && !expire) {
//            ToastUtils.show("token不为空，跳转详情页");
            go(DishActivity.class);
            finish();
            return;
        }
        binding.etAccount.setText(ShareUtils.getString(ShareUtils.SP_KEY_USERNAME));
        binding.etPwd.setText(ShareUtils.getString(ShareUtils.SP_KEY_PASSWORD));
        getCaptcha();
        binding.btnCaptchaRefresh.setOnClickListener(view -> {
            getCaptcha();
        });
        binding.btnLogin.setOnClickListener(view -> {
            if (TypeUtils.isEmpty(binding.etAccount.getText().toString())) {
                ToastUtils.show("账号不能为空！");
                return;
            }
            if (TypeUtils.isEmpty(binding.etPwd.getText().toString())) {
                ToastUtils.show("密码不能为空！");
                return;
            }
            if (TypeUtils.isEmpty(binding.etCaptcha.getText().toString())) {
                ToastUtils.show("验证码不能为空！");
                return;
            }
            LoginForm loginForm = new LoginForm(binding.etAccount.getText().toString(), binding.etPwd.getText().toString(),
                    binding.etCaptcha.getText().toString(), uuid);
            logI("登录:" + loginForm);
            setFormDisable();
            login(loginForm);
        });
        binding.btnRegister.setOnClickListener(view -> {
            ToastUtils.show("注册");
        });
    }

    private void login(LoginForm loginForm) {
        Retrofit retrofit = new Retrofit.Builder().baseUrl(WebConfig.getBaseUrl())
                .addConverterFactory(GsonConverterFactory.create())//设置数据解析器
                .build();
        AuthApi authApi = retrofit.create(AuthApi.class);
        Call<LoginResult> call = authApi.login(loginForm);
        //异步
        call.enqueue(new Callback<LoginResult>() {
            @Override
            public void onResponse(Call<LoginResult> call, Response<LoginResult> response) {
                runOnUiThread(() -> {
                    LoginResult loginResult = response.body();
                    if (loginResult == null) {
                        ToastUtils.show("登录失败！响应数据为空");
                        return;
                    }
                    setFormEnable();
                    logI(String.valueOf(response));
                    if (HttpUtils.isSuccess(loginResult.getCode())) {
                        ShareUtils.putString(ShareUtils.SP_KEY_USERNAME, loginForm.getUsername());
                        ShareUtils.putString(ShareUtils.SP_KEY_PASSWORD, loginForm.getPassword());
                        ShareUtils.putString(ShareUtils.SP_KEY_TOKEN, loginResult.getData().getAccess_token());
                        ShareUtils.putInt(ShareUtils.SP_KEY_EXPIRE, loginResult.getData().getExpires_in());
                        ShareUtils.putBoolean(ShareUtils.SP_KEY_IS_EXPIRE, false);
                        go(DishActivity.class);
                        finish();
                    } else {
                        ToastUtils.show(loginResult.getMsg());
                        getCaptcha();
                    }
                });
            }

            @Override
            public void onFailure(Call<LoginResult> call, Throwable throwable) {
                runOnUiThread(() -> {
                    setFormEnable();
                    ToastUtils.show("获取验证码失败！" + throwable.getMessage());
                });
            }
        });
    }

    private void getCaptcha() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(WebConfig.getBaseUrl())
                .addConverterFactory(GsonConverterFactory.create())//设置数据解析器
                .build();
        AuthApi authApi = retrofit.create(AuthApi.class);
        Call<CodeResult> call = authApi.getCode();
        //异步
        call.enqueue(new Callback<CodeResult>() {
            @Override
            public void onResponse(Call<CodeResult> call, Response<CodeResult> response) {
                runOnUiThread(() -> {
                    CodeResult codeResult = response.body();
                    if (codeResult == null) {
                        ToastUtils.show("获取验证码失败！响应数据为空");
                        return;
                    }
                    Glide.with(getBaseContext()).load("data:image/gif;base64," + codeResult.getImg()).into(binding.ivCaptcha);
                    uuid = codeResult.getUuid();
                });
            }

            @Override
            public void onFailure(Call<CodeResult> call, Throwable throwable) {
                runOnUiThread(() -> {
                    ToastUtils.show("获取验证码失败！" + throwable.getMessage());
                });
            }
        });
    }

    private void setFormEnable() {
        binding.etAccount.setEnabled(true);
        binding.etPwd.setEnabled(true);
        binding.etCaptcha.setEnabled(true);
        binding.btnLogin.setEnabled(true);
        binding.btnRegister.setEnabled(true);
    }

    private void setFormDisable() {
        binding.etAccount.setEnabled(false);
        binding.etPwd.setEnabled(false);
        binding.etCaptcha.setEnabled(false);
        binding.btnLogin.setEnabled(false);
        binding.btnRegister.setEnabled(false);
    }

    private void initNavBar() {
        binding.navBar.btnBack.setOnClickListener(v -> {
            onBackPressed();
        });
        binding.navBar.tvTitle.setText("登录");
        binding.navBar.btnWebConfig.setOnClickListener(v -> {
            if (!this.getClass().getSimpleName().equals(WebConfigActivity.class.getSimpleName())) {
                go(WebConfigActivity.class, true);
            }
        });
    }

}