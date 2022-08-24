package com.tmec.common.sdk.tab;

import android.content.Context;
import android.content.res.Configuration;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.util.SparseIntArray;
import android.view.View;
import android.widget.ScrollView;

import androidx.annotation.NonNull;

import com.tmec.common.sdk.R;
import com.tmec.common.sdk.util.AttributeHelper;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * @author : Lizz
 * e-mail : Zhaozhao_li@human-horizons.com
 * time   : 2019/8/30 13:52
 * desc   : This is a HHVerticalScrollView
 * version: 1.0
 */
public class BaseVerticalScrollView extends ScrollView {
    private SparseIntArray targetResourceValues;
    private int[] targetAttributeArray = new int[]{
        android.R.attr.scrollbarThumbVertical,
        android.R.attr.scrollbarTrackVertical,
        android.R.attr.background
    };

    public BaseVerticalScrollView(Context context) {
        this(context, null);
    }

    public BaseVerticalScrollView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public BaseVerticalScrollView(Context context, AttributeSet attrs, int defStyleAttr) {
        this(context, attrs, defStyleAttr, R.style.HHVerticalScrollbarStyle);
    }

    public BaseVerticalScrollView(Context context, AttributeSet attrs,
                                  int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        targetResourceValues = AttributeHelper.readAttributeValues(context, attrs,
            targetAttributeArray, defStyleAttr, defStyleRes);
    }

    @Override
    public void onConfigurationChanged(@NonNull Configuration newConfig) {
        if (targetResourceValues == null || targetResourceValues.size() == 0) {
            return;
        }
        int thumbResourceId = targetResourceValues.get(android.R.attr.scrollbarThumbVertical);
        int trackResourceId = targetResourceValues.get(android.R.attr.scrollbarTrackVertical);
        try {
            Field mScrollCacheField = View.class.getDeclaredField("mScrollCache");
            mScrollCacheField.setAccessible(true);

            Object mScrollCache = mScrollCacheField.get(this);
            Field scrollBarField = mScrollCache.getClass().getDeclaredField("scrollBar");
            scrollBarField.setAccessible(true);

            Object scrollBar = scrollBarField.get(mScrollCache);
            if (AttributeHelper.isValid((thumbResourceId))) {
                Method method = scrollBar.getClass().getDeclaredMethod("setVerticalThumbDrawable", Drawable.class);
                method.setAccessible(true);
                method.invoke(scrollBar, getResources().getDrawable(R.drawable.scroll_progress_shape));
            }

            if (AttributeHelper.isValid(trackResourceId)) {
                Method method = scrollBar.getClass().getDeclaredMethod("setVerticalTrackDrawable", Drawable.class);
                method.setAccessible(true);
                method.invoke(scrollBar, getResources().getDrawable(R.drawable.scroll_bg_shape));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @Override
    public void setBackgroundResource(int resid) {
        super.setBackgroundResource(resid);
        targetResourceValues.put(android.R.attr.background, resid);
    }
}
