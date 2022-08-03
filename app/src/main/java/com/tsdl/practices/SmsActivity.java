package com.tsdl.practices;

import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.tsdl.practices.adapter.PagerAdapter;
import com.tsdl.practices.base.BaseActivity;
import com.tsdl.practices.databinding.ActivitySmsBinding;
import com.tsdl.practices.fragment.AssetFragment;
import com.tsdl.practices.fragment.MineFragment;
import com.tsdl.practices.fragment.ScrollingFragment;
import com.tsdl.practices.util.Constants;
import com.tsdl.practices.util.LogUtils;
import com.tsdl.practices.view.PageItemView;

import java.util.ArrayList;

public class SmsActivity extends BaseActivity {

    private ActivitySmsBinding mBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_sms);
        ArrayList<Fragment> fragmentArrayList = new ArrayList<>();
        fragmentArrayList.add(new ScrollingFragment());
        fragmentArrayList.add(new AssetFragment());
        fragmentArrayList.add(new MineFragment());
        mBinding.viewPager.setAdapter(new PagerAdapter(getSupportFragmentManager(), this, fragmentArrayList));
        // 关联ViewPager后清空Tab
        mBinding.tabLayout.setupWithViewPager(mBinding.viewPager);
        mBinding.tabLayout.removeAllTabs();
        // 重新添加Tab
        mBinding.tabLayout.addTab(mBinding.tabLayout.newTab()
                .setCustomView(new PageItemView(this)
                        .setText(getResources().getString(R.string.fragment_home))
                        .setIcon(getResources().getDrawable(R.drawable.icon_home_active))));
        mBinding.tabLayout.addTab(mBinding.tabLayout.newTab()
                .setCustomView(new PageItemView(this)
                        .setText(getResources().getString(R.string.fragment_asset))
                        .setIcon(getResources().getDrawable(R.drawable.icon_asset_inactive))));
        mBinding.tabLayout.addTab(mBinding.tabLayout.newTab()
                .setCustomView(new PageItemView(this)
                        .setText(getResources().getString(R.string.fragment_mine))
                        .setIcon(getResources().getDrawable(R.drawable.icon_mine_inactive))));
        mBinding.viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                updatePageIcon(position);
            }

            @Override
            public void onPageSelected(int position) {
                updatePageIcon(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    private void updatePageIcon(int position) {
        try {
            for (int i = 0; i < Constants.PAGE_NUM; i++) {
                if (i == position) {
                    ((PageItemView) mBinding.tabLayout.getTabAt(i).getCustomView())
                            .setIcon(getResources().getDrawable(Constants.PAGE_ACTIVE_ICON_LIST.get(i)));
                } else {
                    ((PageItemView) mBinding.tabLayout.getTabAt(i).getCustomView())
                            .setIcon(getResources().getDrawable(Constants.PAGE_INACTIVE_ICON_LIST.get(i)));
                }
            }
        } catch (Exception exception) {
            LogUtils.logE(TAG, exception.toString());
        }
    }
}