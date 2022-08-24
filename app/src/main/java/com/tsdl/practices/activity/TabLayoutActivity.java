package com.tsdl.practices.activity;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.tmec.common.sdk.tab.BaseTabItem;
import com.tsdl.practices.R;
import com.tsdl.practices.databinding.ActivityTabLayoutBinding;

public class TabLayoutActivity extends AppCompatActivity {
    
    private ActivityTabLayoutBinding mBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(TabLayoutActivity.this, R.layout.activity_tab_layout);
        initView();
    }
    
    private void initView() {
        String tabItemName = "tab";
        int i = 0;
//        mBinding.itemVertical.addItem(new BaseTabItem(tabItemName + (++i), com.tmec.common.sdk.R.drawable.ic_hht_setting_1,
//                com.tmec.common.sdk.R.drawable.ic_hht_setting_2, BaseTabItem.ITEMIMG));
//        mBinding.itemVertical.addItem(new BaseTabItem(tabItemName + (++i), com.tmec.common.sdk.R.drawable.ic_hht_setting_1,
//                com.tmec.common.sdk.R.drawable.ic_hht_setting_2, BaseTabItem.ITEMIMG));
//        mBinding.itemVertical.addItem(new BaseTabItem(tabItemName + (++i), com.tmec.common.sdk.R.drawable.ic_hht_setting_1,
//                com.tmec.common.sdk.R.drawable.ic_hht_setting_2, BaseTabItem.ITEMIMG));
//        mBinding.itemVertical.addItem(new BaseTabItem(tabItemName + (++i), com.tmec.common.sdk.R.drawable.ic_hht_setting_1,
//                com.tmec.common.sdk.R.drawable.ic_hht_setting_2, BaseTabItem.ITEMIMG));
//        mBinding.itemVertical.addItem(new BaseTabItem(tabItemName + (++i), com.tmec.common.sdk.R.drawable.ic_hht_setting_1,
//                com.tmec.common.sdk.R.drawable.ic_hht_setting_2, BaseTabItem.ITEMIMG));
//        mBinding.itemVertical.addItem(new BaseTabItem(tabItemName + (++i), com.tmec.common.sdk.R.drawable.ic_hht_setting_1,
//                com.tmec.common.sdk.R.drawable.ic_hht_setting_2, BaseTabItem.ITEMIMG));


        mBinding.itemVertical.addItem(new BaseTabItem(tabItemName + (++i), BaseTabItem.ITEMSTRING));
        mBinding.itemVertical.addItem(new BaseTabItem(tabItemName + (++i), BaseTabItem.ITEMSTRING, true));
        mBinding.itemVertical.addItem(new BaseTabItem(tabItemName + (++i), BaseTabItem.ITEMSTRING, true));
    }
}