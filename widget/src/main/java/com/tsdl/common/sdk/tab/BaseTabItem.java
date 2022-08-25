/*
 * Copyright © 2019-Now Human Horizons (Shanghai) Cloud Computing Technology Co., Ltd. All
 * Rights Reserved.
 */
package com.tsdl.common.sdk.tab;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;

import androidx.core.content.ContextCompat;

/**
 * @ClassName BaseTabItem
 * @Description This is a BaseTabItem
 * @Author zhaozhaoli
 * @E-mail : Zhaozhao_Li1@human-horizons.com
 * @Date 2021/11/24
 * @Version 1.0
 */
public class BaseTabItem {
    /**
     * the item is string
     */
    public static final int ITEMSTRING = 1;
    /**
     * the item is image
     */
    public static final int ITEMIMG = 2;
    private String mItemName;
    private Drawable mBackDrawable;
    private Drawable mItemDrawable;
    private Drawable mDisableDrawable;
    private int mBgResId = 0;
    private int mItemResId = 0;
    private int mDisableResId = 0;
    private boolean mIsEnable = true;
    private int mType = ITEMSTRING;
    private float mTextSize;
    private float mSelectTextSize;

    public BaseTabItem(String name, int type) {
        this.mItemName = name;
        this.mType = type;
    }

    public BaseTabItem(String name, int type, boolean enable) {
        this.mItemName = name;
        this.mType = type;
        this.mIsEnable = enable;
    }

    public BaseTabItem(String name, int type, int textSize) {
        this.mItemName = name;
        this.mType = type;
        this.mTextSize = textSize;
    }

    public BaseTabItem(String name, int type, boolean enable, int textSize) {
        this.mItemName = name;
        this.mType = type;
        this.mIsEnable = enable;
        this.mTextSize = textSize;
    }

    public BaseTabItem(String name, int type, boolean enable, int textSize,
                       int selectTextSize) {
        this.mItemName = name;
        this.mType = type;
        this.mIsEnable = enable;
        this.mTextSize = textSize;
        this.mSelectTextSize = selectTextSize;
    }

    public BaseTabItem(int bgResId, int itemResId, int type) {
        this.mBgResId = bgResId;
        this.mItemResId = itemResId;
        this.mType = type;
    }

    public BaseTabItem(String name, int bgResId, int itemResId, int type) {
        this.mBgResId = bgResId;
        this.mItemResId = itemResId;
        this.mItemName = name;
        this.mType = type;
    }

    public BaseTabItem(String name, int bgResId, int itemResId, int disableResId, int type) {
        this.mBgResId = bgResId;
        this.mItemResId = itemResId;
        this.mDisableResId = disableResId;
        this.mItemName = name;
        this.mType = type;
    }

    public BaseTabItem(String name, Drawable backDrawable, Drawable itemDrawable, int type) {
        this.mItemName = name;
        this.mBackDrawable = backDrawable;
        this.mItemDrawable = itemDrawable;
        this.mType = type;
    }

    public BaseTabItem(Drawable backDrawable, Drawable itemDrawable, int type) {
        this.mBackDrawable = backDrawable;
        this.mItemDrawable = itemDrawable;
        this.mType = type;
    }

    public BaseTabItem(Drawable backDrawable, Drawable itemDrawable, int type,
                       boolean enable) {
        this.mBackDrawable = backDrawable;
        this.mItemDrawable = itemDrawable;
        this.mType = type;
        this.mIsEnable = enable;
    }

    public BaseTabItem(Drawable backDrawable, Drawable itemDrawable,
                       Drawable disableDrawable, int type, boolean enable) {
        this.mBackDrawable = backDrawable;
        this.mItemDrawable = itemDrawable;
        this.mDisableDrawable = disableDrawable;
        this.mType = type;
        this.mIsEnable = enable;
    }

    /**
     * get item string name.
     *
     * @return string the item name
     */
    public String getName() {
        return mItemName;
    }

    /**
     * set item name string.
     *
     * @param name string the item name
     */
    public void setName(String name) {
        this.mItemName = name;
    }

    /**
     * if item is image to git item image background.
     *
     * @return Drawable the item back layout
     */
    public Drawable getBackDrawable() {
        return mBackDrawable;
    }

    /**
     * if item is image to git item image background.
     *
     * @param context context
     * @return Drawable the item back layout
     */
    @SuppressLint("UseCompatLoadingForDrawables")
    public Drawable getBackDrawable(Context context) {
        if (mBackDrawable != null) {
            return mBackDrawable;
        }
        if (mBgResId != 0 && context != null) {
            return context.getResources().getDrawable(mBgResId, context.getTheme());
        }
        return null;
    }

    /**
     * if item is image to git item image background.
     *
     * @param resources Resources
     * @return Drawable the item back layout
     */
    @SuppressLint("UseCompatLoadingForDrawables")
    public Drawable getBackDrawable(Resources resources) {
        if (mBackDrawable != null) {
            return mBackDrawable;
        }
        if (mBgResId != 0 && resources != null) {
            return resources.getDrawable(mBgResId);
        }
        return null;
    }

    /**
     * if item is image to set item image background.
     *
     * @param backDrawable Drawable the item back layout
     */
    public void setBackDrawable(Drawable backDrawable) {
        this.mBackDrawable = backDrawable;
    }

    /**
     * if item is image to git item image select.
     *
     * @return Drawable
     */
    public Drawable getItemDrawable() {
        return mItemDrawable;
    }

    /**
     * if item is image to git item image select.
     *
     * @param context Context
     * @return Drawable
     */
    @SuppressLint("UseCompatLoadingForDrawables")
    public Drawable getItemDrawable(Context context) {
        if (mItemDrawable != null) {
            return mItemDrawable;
        }
        if (context != null && mItemResId != 0) {
            return context.getResources().getDrawable(mItemResId, context.getTheme());
        }
        return null;
    }

    /**
     * if item is image to git item image select.
     *
     * @param resources Resources
     * @return Drawable
     */
    @SuppressLint("UseCompatLoadingForDrawables")
    public Drawable getItemDrawable(Resources resources) {
        if (mItemDrawable != null) {
            return mItemDrawable;
        }
        if (resources != null && mItemResId != 0) {
            return resources.getDrawable(mItemResId);
        }
        return null;
    }

    /**
     * if item is image to set item image select.
     *
     * @param itemDrawable the item layout
     */
    public void setItemDrawable(Drawable itemDrawable) {
        this.mItemDrawable = itemDrawable;
    }

    /**
     * if item is image to git item image disable
     *
     * @return Drawable the item layout
     */
    public Drawable getDisableDrawable() {
        return mDisableDrawable;
    }

    /**
     * if item is image to git item image disable
     *
     * @param context Context
     * @return Drawable the item layout
     */
    @SuppressLint("UseCompatLoadingForDrawables")
    public Drawable getDisableDrawable(Context context) {
        if (mDisableDrawable != null) {
            return mDisableDrawable;
        }
        if (context != null && mDisableResId != 0) {
            return context.getResources().getDrawable(mDisableResId, context.getTheme());
        }
        return null;
    }

    /**
     * if item is image to git item image disable
     *
     * @param resources Resources
     * @return Drawable the item layout
     */
    @SuppressLint("UseCompatLoadingForDrawables")
    public Drawable getDisableDrawable(Resources resources) {
        if (mDisableDrawable != null) {
            return mDisableDrawable;
        }
        if (resources != null && mDisableResId != 0) {
            return resources.getDrawable(mDisableResId);
        }
        return null;
    }

    /**
     * if item is image to set item image disable
     *
     * @param disableDrawable Drawable
     */
    public void setDisableDrawable(Drawable disableDrawable) {

        this.mDisableDrawable = disableDrawable;
    }

    /**
     * get item type is string or image.
     *
     * @return int the item type
     */
    public int getType() {
        return mType;
    }

    /**
     * set item type is string or image.
     *
     * @param type the item type
     */
    public void setType(int type) {
        this.mType = type;
    }

    /**
     * get item is can click.
     *
     * @return boolean  the item is or not enable state
     */
    public boolean isEnable() {
        return mIsEnable;
    }

    /**
     * set item is can click.
     *
     * @param enable the item is or not enable state
     */
    public void setEnable(boolean enable) {
        mIsEnable = enable;
    }

    /**
     * get item text size
     *
     * @return int text size
     */
    public float getTextSize() {
        return mTextSize;
    }

    /**
     * set item text size
     *
     * @param textSize int text size
     */
    public void setTextSize(float textSize) {
        this.mTextSize = textSize;
    }

    /**
     * get select text size
     *
     * @return int text size
     */
    public float getSelectTextSize() {
        return mSelectTextSize;
    }

    /**
     * set select text size
     *
     * @param selectTextSize int text size
     */
    public void setSelectTextSize(float selectTextSize) {
        this.mSelectTextSize = selectTextSize;
    }

    public int getBgResId() {
        return mBgResId;
    }

    public void setBgResId(int bgResId) {
        this.mBgResId = bgResId;
    }

    public int getItemResId() {
        return mItemResId;
    }

    public void setItemResId(int itemResId) {
        this.mItemResId = itemResId;
    }

    public void onOverlayChanged(Context context) {
        if (mType == ITEMIMG && mBgResId != 0 && mItemResId != 0) {
            mBackDrawable = ContextCompat.getDrawable(context, mBgResId);
            mItemDrawable = ContextCompat.getDrawable(context, mItemResId);
        }
    }
}
