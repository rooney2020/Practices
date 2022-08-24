package com.tsdl.practices.activity;

import android.annotation.SuppressLint;
import android.content.res.Configuration;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;
import com.tsdl.practices.R;
import com.tsdl.practices.adapter.PagerAdapter;
import com.tsdl.practices.base.BaseActivity;
import com.tsdl.practices.databinding.ActivitySmsBinding;
import com.tsdl.practices.fragment.AssetFragment;
import com.tsdl.practices.fragment.HomeFragment;
import com.tsdl.practices.fragment.MineFragment;
import com.tsdl.practices.util.Constants;
import com.tsdl.practices.view.PageItemView;

import java.util.ArrayList;

public class SmsActivity extends BaseActivity implements ViewPager.OnPageChangeListener {

    private ActivitySmsBinding mBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_sms);
        initFragment();
    }

    @SuppressLint("UseCompatLoadingForDrawables")
    private void initFragment() {
        ArrayList<Fragment> fragmentArrayList = new ArrayList<>();
        fragmentArrayList.add(new HomeFragment());
        fragmentArrayList.add(new AssetFragment());
        fragmentArrayList.add(new MineFragment());
        mBinding.viewPager.setAdapter(new PagerAdapter(getSupportFragmentManager(), this, fragmentArrayList));
        // 关联ViewPager后清空Tab
        mBinding.tabLayout.setupWithViewPager(mBinding.viewPager);
        mBinding.tabLayout.removeAllTabs();
        // 重新添加Tab
        for (int i = 0; i < Constants.PAGE_NUM; i++) {
            mBinding.tabLayout.addTab(mBinding.tabLayout.newTab()
                    .setCustomView(new PageItemView(this)
                            .setText(getResources().getString(Constants.PAGE_TITLE_LIST.get(i)))
                            .setIcon(getResources().getDrawable(Constants.PAGE_INACTIVE_ICON_LIST.get(i)))));
        }
        mBinding.viewPager.addOnPageChangeListener(this);
    }

    @SuppressLint("UseCompatLoadingForDrawables")
    private void updatePageIcon(int position) {
        TabLayout.Tab tab;
        PageItemView customView;
        for (int i = 0; i < mBinding.tabLayout.getTabCount(); i++) {
            tab = mBinding.tabLayout.getTabAt(i);
            if (tab != null) {
                customView = (PageItemView) tab.getCustomView();
                if (customView != null) {
                    if (i == position) {
                        customView.setIcon(getResources().getDrawable(Constants.PAGE_ACTIVE_ICON_LIST.get(i)));
                    } else {
                        customView.setIcon(getResources().getDrawable(Constants.PAGE_INACTIVE_ICON_LIST.get(i)));
                    }
                }
            }
        }
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
        if (positionOffset == 0) {
            updatePageIcon(position);
        }
    }

    @Override
    public void onPageSelected(int position) {
        updatePageIcon(position);
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    @Override
    public void onConfigurationChanged(@NonNull Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        updatePageIcon(mBinding.tabLayout.getSelectedTabPosition());
    }
}