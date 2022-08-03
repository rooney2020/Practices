package com.tsdl.practices.adapter;

import android.content.Context;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import java.util.List;

public class PagerAdapter extends FragmentPagerAdapter {

    private Context mContext;
    private List<Fragment> mFragmentList;

    public PagerAdapter(FragmentManager fm, Context context, List<Fragment> fragmentList) {
        super(fm);
        this.mContext = context;
        this.mFragmentList = fragmentList;
    }

    @Override
    public Fragment getItem(int position) {
        return mFragmentList.get(position);
    }

    @Override
    public int getCount() {
        return mFragmentList.size();
    }
}
