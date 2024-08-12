package com.tsdl.practices.activity;

import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Spinner;

import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.tsdl.practices.R;
import com.tsdl.practices.adapter.DishAdapter;
import com.tsdl.practices.api.FoodDishApi;
import com.tsdl.practices.base.BaseActivity;
import com.tsdl.practices.config.WebConfig;
import com.tsdl.practices.databinding.ActivityDishBinding;
import com.tsdl.practices.entity.DishQueryParams;
import com.tsdl.practices.entity.menu.DishListResult;
import com.tsdl.practices.util.HttpUtils;
import com.tsdl.practices.util.ShareUtils;
import com.tsdl.practices.util.ToastUtils;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class DishActivity extends BaseActivity {

    private ActivityDishBinding binding;
    private Dialog mSearchDialog;
    private DishAdapter dishAdapter;
    private final DishQueryParams dishQueryParams = new DishQueryParams();
    private String token;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
    }

    protected void initView() {
        binding = DataBindingUtil.setContentView(this, R.layout.activity_dish);
        initNavBar();
        initDialog();
        token = ShareUtils.getString(ShareUtils.SP_KEY_TOKEN);
        binding.btnSearch.setOnClickListener(view -> mSearchDialog.show());
        dishAdapter = new DishAdapter(DishActivity.this, null);
        LinearLayoutManager layoutManager = new LinearLayoutManager(DishActivity.this);
        binding.rvList.setLayoutManager(layoutManager);
        binding.rvList.setAdapter(dishAdapter);
        initData();
    }

    private void initData() {
        Retrofit retrofit = new Retrofit.Builder().baseUrl(WebConfig.getBaseUrl())
                .addConverterFactory(GsonConverterFactory.create())//设置数据解析器
                .build();
        FoodDishApi foodDishApi = retrofit.create(FoodDishApi.class);
        Call<DishListResult> call = foodDishApi.getDishListPage("Bearer " + token, 1, 10);
        //异步
        call.enqueue(new Callback<DishListResult>() {
            @Override
            public void onResponse(Call<DishListResult> call, Response<DishListResult> response) {
                runOnUiThread(() -> {
                    DishListResult dishListResult = response.body();
                    if (dishListResult == null) {
                        ToastUtils.show("查询失败！响应数据为空");
                        return;
                    }
                    if (HttpUtils.isSuccess(dishListResult.getCode())) {
                        dishAdapter.setData(dishListResult.getRows());
                        dishAdapter.notifyDataSetChanged();
                    } else if (HttpUtils.isExpire(dishListResult.getCode())) {
                        ShareUtils.putBoolean(ShareUtils.SP_KEY_IS_EXPIRE, true);
                        ToastUtils.show("Token过期，请重新登录！");
                        go(LoginActivity.class, true);
                    } else {
                        ToastUtils.show("查询失败！" + dishListResult.getMsg());
                    }
                });
            }

            @Override
            public void onFailure(Call<DishListResult> call, Throwable throwable) {
                runOnUiThread(() -> {
                    ToastUtils.show("获取失败！" + throwable.getMessage());
                });
            }
        });
    }

    private void initTypeData() {
        DishQueryParams typeQueryParams = new DishQueryParams();
        typeQueryParams.setType(1);
        //        HttpUtils.get(WebConfig.URL_TYPE_LIST + typeQueryParams.toString(), null, new HttpCallback() {
        //            @SuppressLint("NotifyDataSetChanged")
        //            @Override
        //            public void onFinish(Object response) {
        //                super.onFinish(response);
        //                TypeListResult typeListResult = new Gson().fromJson(String.valueOf(response), TypeListResult.class);
        //
        //                runOnUiThread(() -> {
        //                    if (!typeListResult.isSuccess() || !typeListResult.isTokenValid()) {
        //                        ToastUtils.show(typeListResult.getMsg());
        //                        ShareUtils.putBoolean(ShareUtils.SP_KEY_IS_EXPIRE, true);
        //                        goLogin();
        //                        finish();
        //                        return;
        //                    }
        //                    List<String> typeData = new ArrayList<>();
        //                    for (Type type : typeListResult.getPage().getList()) {
        //                        typeData.add(type.getName());
        //                    }
        //                    ArrayAdapter<String> typeArrayAdapter = new ArrayAdapter<>(DishActivity.this,
        //                            android.R.layout.simple_spinner_item, typeData);
        //                    typeArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //                    typeSpinner.setAdapter(typeArrayAdapter);
        //                });
        //
        //            }
        //
        //            @Override
        //            public void onError(Exception e) {
        //                super.onError(e);
        //                ShareUtils.putBoolean(ShareUtils.SP_KEY_IS_EXPIRE, true);
        //            }
        //        });
    }

    private void initDialog() {
        mSearchDialog = new Dialog(this);
        View view = mSearchDialog.getLayoutInflater().inflate(R.layout.dialog_search, null);
        WindowManager.LayoutParams layoutParams = new WindowManager.LayoutParams();
        layoutParams.width = 800;
        layoutParams.height = 800;
        mSearchDialog.setContentView(view, layoutParams);
        mSearchDialog.setCanceledOnTouchOutside(false);
        Button confirm = view.findViewById(R.id.btn_confirm);
        Button cancel = view.findViewById(R.id.btn_cancel);
        cancel.setOnClickListener(btnCancel -> mSearchDialog.hide());
        confirm.setOnClickListener(btnCancel -> {
            dishQueryParams.setKey("锅包肉");
            initData();
            mSearchDialog.hide();
        });
        Spinner typeSpinner = view.findViewById(R.id.spinner_type);
        initTypeData();
    }

    private void initNavBar() {
        binding.navBar.btnBack.setOnClickListener(v -> {
            onBackPressed();
        });
        binding.navBar.tvTitle.setText("菜单");
        binding.navBar.btnWebConfig.setOnClickListener(v -> {
            if (!this.getClass().getSimpleName().equals(WebConfigActivity.class.getSimpleName())) {
                go(WebConfigActivity.class, true);
            }
        });
    }
}