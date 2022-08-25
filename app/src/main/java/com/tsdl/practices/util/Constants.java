package com.tsdl.practices.util;

import com.tsdl.practices.R;

import java.util.Arrays;
import java.util.List;

public class Constants {

    public static final int PAGE_NUM = 3;
    public static final List<Integer> PAGE_TITLE_LIST = Arrays.asList(R.string.fragment_home,
            R.string.fragment_asset, R.string.fragment_mine);
    public static final List<Integer> PAGE_ACCOUNT_TITLE_LIST = Arrays.asList(R.string.fragment_outcome,
            R.string.fragment_income, R.string.fragment_transfer_account);
    public static final List<Integer> PAGE_ACTIVE_ICON_LIST = Arrays.asList(R.drawable.icon_home_active,
            R.drawable.icon_asset_active, R.drawable.icon_mine_active);
    public static final List<Integer> PAGE_INACTIVE_ICON_LIST = Arrays.asList(R.drawable.icon_home_inactive,
            R.drawable.icon_asset_inactive, R.drawable.icon_mine_inactive);
}
