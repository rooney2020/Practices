/*
 * Copyright @ 2021 - 2022 Human Horizons (Shanghai) Cloud Computing Technology Co., Ltd.
 * All Rights Reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are NOT permitted except as agreed by
 * Human Horizons (Shanghai) Cloud Computing Technology Co., Ltd.
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 */
package com.tmec.common.sdk.tab;

import androidx.databinding.BindingAdapter;

import java.util.List;

/**
 * @ClassName HHTabItemBindingAdapter
 * @Description This is a HHTabItemBindingAdapter
 * @Author zhaozhaoli
 * @E-mail : Zhaozhao_Li1@human-horizons.com
 * @Date 2021/11/24
 * @Version 1.0
 */
public class BaseTabItemBindingAdapter {

    /**
     * addItems
     *
     * @param tabItemLayout HHTabItemLayout
     * @param list          List<HHTabItem>
     */
    @BindingAdapter("hryt_addItems")
    public static void addItems(BaseTabItemLayout tabItemLayout, List<BaseTabItem> list) {
        if (tabItemLayout != null) {
            tabItemLayout.addItems(list);
        }
    }

    /**
     * addItems
     *
     * @param tabItemLayout HHTabItemLayout
     * @param items         CharSequence[]
     */
    @BindingAdapter("hryt_addTextItems")
    public static void addItems(BaseTabItemLayout tabItemLayout, CharSequence[] items) {
        if (tabItemLayout != null) {
            tabItemLayout.addItems(items);
        }
    }

    /**
     * addItem
     *
     * @param tabItemLayout HHTabItemLayout
     * @param item          HHTabItem
     */
    @BindingAdapter("hryt_addItem")
    public static void addItem(BaseTabItemLayout tabItemLayout, BaseTabItem item) {
        if (tabItemLayout != null) {
            tabItemLayout.addItem(item);
        }
    }

    /**
     * setSelectedItem
     *
     * @param tabItemLayout HHTabItemLayout
     * @param position      int
     */
    @BindingAdapter("hryt_tabItemSelected")
    public static void setSelectedItem(BaseTabItemLayout tabItemLayout, int position) {
        if (tabItemLayout != null) {
            tabItemLayout.setSelectedItem(position);
        }
    }

    /**
     * setEnabled
     *
     * @param tabItemLayout HHTabItemLayout
     * @param isEnabled     boolean
     */
    @BindingAdapter("hryt_tabItemEnabled")
    public static void setEnabled(BaseTabItemLayout tabItemLayout, boolean isEnabled) {
        if (tabItemLayout != null) {
            tabItemLayout.setEnabled(isEnabled);
        }
    }

    /**
     * setItemEnabled
     *
     * @param tabItemLayout HHTabItemLayout
     * @param position      int
     * @param isEnable      boolean
     */
    @BindingAdapter(value = {"hryt_tabItemPosition", "hryt_tabItemEnabled"}, requireAll = true)
    public static void setItemEnabled(BaseTabItemLayout tabItemLayout, int position, boolean isEnable) {
        if (tabItemLayout != null) {
            tabItemLayout.setItemEnabled(position, isEnable);
        }
    }

    /**
     * setOnClickBeforeAnimation
     *
     * @param tabItemLayout   HHTabItemLayout
     * @param beforeAnimation boolean
     */
    @BindingAdapter("hryt_onClickBeforeAnimation")
    public static void setOnClickBeforeAnimation(BaseTabItemLayout tabItemLayout, boolean beforeAnimation) {
        if (tabItemLayout != null) {
            tabItemLayout.setOnClickBeforeAnimation(beforeAnimation);
        }
    }

    /**
     * setMode
     *
     * @param tabItemLayout HHTabItemLayout
     * @param mode          int
     */
    @BindingAdapter("hryt_tabItemMode")
    public static void setMode(BaseTabItemLayout tabItemLayout, @BaseTabItemLayout.Mode int mode) {
        if (tabItemLayout != null) {
            tabItemLayout.setMode(mode);
        }
    }

    /**
     * setOnSegItemClickListener
     *
     * @param tabItemLayout HHTabItemLayout
     * @param listener      OnSegItemClickFromUserListener
     */
    @BindingAdapter("hryt_onTabItemClickFromUserListener")
    public static void setOnSegItemClickListener(BaseTabItemLayout tabItemLayout,
                                                 BaseTabItemLayout.OnSegItemClickFromUserListener listener) {
        if (tabItemLayout != null) {
            tabItemLayout.setOnSegItemClickListener(listener);
        }
    }

    /**
     * set Is Child Type
     *
     * @param tabItemLayout HHTabItemLayout
     * @param isChild       OnSegItemClickFromUserListener
     */
    @BindingAdapter("hryt_tabItemChildEnable")
    public static void setIsChildType(BaseTabItemLayout tabItemLayout,
                                      Boolean isChild) {
        if (tabItemLayout != null) {
            tabItemLayout.setIsChildType(isChild);
        }
    }
}
