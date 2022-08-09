package com.tsdl.practices;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import com.tsdl.practices.adapter.PagerAdapter;
import com.tsdl.practices.base.BaseActivity;
import com.tsdl.practices.databinding.ActivityAccountBinding;
import com.tsdl.practices.fragment.IncomeFragment;
import com.tsdl.practices.fragment.OutcomeFragment;
import com.tsdl.practices.fragment.TransferAccountFragment;
import com.tsdl.practices.util.Constants;
import com.tsdl.practices.view.PageItemView;

import java.util.ArrayList;

public class AccountActivity extends BaseActivity {

    private ActivityAccountBinding mBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_account);
        initFragment();
    }

    @SuppressLint("UseCompatLoadingForDrawables")
    private void initFragment() {
        ArrayList<Fragment> fragmentArrayList = new ArrayList<>();
        fragmentArrayList.add(new OutcomeFragment());
        fragmentArrayList.add(new IncomeFragment());
        fragmentArrayList.add(new TransferAccountFragment());
        mBinding.viewPager.setAdapter(new PagerAdapter(getSupportFragmentManager(), this, fragmentArrayList));
        // 关联ViewPager后清空Tab
        mBinding.tabLayout.setupWithViewPager(mBinding.viewPager);
        mBinding.tabLayout.removeAllTabs();
        // 重新添加Tab
        for (int i = 0; i < Constants.PAGE_NUM; i++) {
            mBinding.tabLayout.addTab(mBinding.tabLayout.newTab()
                    .setCustomView(new PageItemView(this)
                            .setText(getResources().getString(Constants.PAGE_ACCOUNT_TITLE_LIST.get(i)))));
        }
        mBinding.btnBack.setOnClickListener(view -> onBackPressed());
    }
}