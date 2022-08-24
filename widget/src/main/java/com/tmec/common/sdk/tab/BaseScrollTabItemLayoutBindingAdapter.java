package com.tmec.common.sdk.tab;

import androidx.databinding.BindingAdapter;

import java.util.List;

/**
 * author : HaonanWu
 * e-mail : Haonan_Wu@human-horizons.com
 * time   : 2022/7/1
 * desc   : This is
 * version: 1.0
 */
public class BaseScrollTabItemLayoutBindingAdapter {

    /**
     * addItems
     *
     * @param tabItemLayout HHTabItemLayout
     * @param list          List<HHTabItem>
     */
    @BindingAdapter("hryt_addScrollItems")
    public static void addItems(BaseScrollTabItemLayout tabItemLayout, List<BaseTabItem> list) {
        if (tabItemLayout != null) {
            tabItemLayout.addItems(list);
        }
    }

    @BindingAdapter("hryt_scrollItemSetRedPointShow")
    public static void setRedPointShow(BaseScrollTabItemLayout tabItemLayout, boolean show) {
        if (tabItemLayout != null) {
            tabItemLayout.setRedPointShow(show);
        }
    }

    @BindingAdapter("hryt_scrollItemSetRedPointPosition")
    public static void setRedPointPosition(BaseScrollTabItemLayout tabItemLayout, int position) {
        if (tabItemLayout != null) {
            tabItemLayout.setRedPointPosition(position);
        }
    }

    @BindingAdapter("hryt_scrollItemSetRedPointPositionList")
    public static void setRedPointPositionList(BaseScrollTabItemLayout tabItemLayout,
                                               List<Integer> positionList) {
        if (tabItemLayout != null) {
            tabItemLayout.setRedPointPositionList(positionList);
        }
    }

    /**
     * addItems
     *
     * @param tabItemLayout HHTabItemLayout
     * @param items         CharSequence[]
     */
    @BindingAdapter("hryt_addScrollTextItems")
    public static void addItems(BaseScrollTabItemLayout tabItemLayout, CharSequence[] items) {
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
    @BindingAdapter("hryt_addScrollItem")
    public static void addItem(BaseScrollTabItemLayout tabItemLayout, BaseTabItem item) {
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
    @BindingAdapter("hryt_scrollTabItemSelected")
    public static void setSelectedItem(BaseScrollTabItemLayout tabItemLayout, int position) {
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
    @BindingAdapter("hryt_scrollTabItemEnabled")
    public static void setEnabled(BaseScrollTabItemLayout tabItemLayout, boolean isEnabled) {
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
    @BindingAdapter(value = {"hryt_scrollTabItemPosition", "hryt_ScrollTabItemEnabled"}, requireAll = true)
    public static void setItemEnabled(BaseScrollTabItemLayout tabItemLayout, int position, boolean isEnable) {
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
    @BindingAdapter("hryt_onClickScrollBeforeAnimation")
    public static void setOnClickBeforeAnimation(BaseScrollTabItemLayout tabItemLayout, boolean beforeAnimation) {
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
    @BindingAdapter("hryt_scrollTabItemMode")
    public static void setMode(BaseScrollTabItemLayout tabItemLayout, @BaseScrollTabItemLayout.Mode int mode) {
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
    @BindingAdapter("hryt_onScrollTabItemClickFromUserListener")
    public static void setOnSegItemClickListener(BaseScrollTabItemLayout tabItemLayout,
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
    @BindingAdapter("hryt_scrollTabItemChildEnable")
    public static void setIsChildType(BaseScrollTabItemLayout tabItemLayout,
                                      Boolean isChild) {
        if (tabItemLayout != null) {
            tabItemLayout.setIsChildType(isChild);
        }
    }
}
