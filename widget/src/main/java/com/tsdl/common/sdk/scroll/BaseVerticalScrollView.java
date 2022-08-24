package com.tsdl.common.sdk.scroll;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.Configuration;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.util.SparseIntArray;
import android.view.View;
import android.widget.ScrollView;

import androidx.annotation.NonNull;

import com.tsdl.common.sdk.R;
import com.tsdl.common.sdk.util.AttributeHelper;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class BaseVerticalScrollView extends ScrollView {
    private final SparseIntArray targetResourceValues;

    public BaseVerticalScrollView(Context context) {
        this(context, null);
    }

    public BaseVerticalScrollView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public BaseVerticalScrollView(Context context, AttributeSet attrs, int defStyleAttr) {
        this(context, attrs, defStyleAttr, R.style.BaseVerticalScrollbarStyle);
    }

    public BaseVerticalScrollView(Context context, AttributeSet attrs,
                                  int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        int[] targetAttributeArray = new int[]{
                android.R.attr.scrollbarThumbVertical,
                android.R.attr.scrollbarTrackVertical,
                android.R.attr.background
        };
        targetResourceValues = AttributeHelper.readAttributeValues(context, attrs,
                targetAttributeArray, defStyleAttr, defStyleRes);
    }

    @SuppressLint("UseCompatLoadingForDrawables,DiscouragedPrivateApi")
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
            if (scrollBar != null && AttributeHelper.isValid((thumbResourceId))) {
                Method method = scrollBar.getClass().getDeclaredMethod("setVerticalThumbDrawable", Drawable.class);
                method.setAccessible(true);
                method.invoke(scrollBar, getResources().getDrawable(R.drawable.scroll_progress_shape));
            }

            if (scrollBar != null && AttributeHelper.isValid(trackResourceId)) {
                Method method = scrollBar.getClass().getDeclaredMethod("setVerticalTrackDrawable", Drawable.class);
                method.setAccessible(true);
                method.invoke(scrollBar, getResources().getDrawable(R.drawable.scroll_bg_shape));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @Override
    public void setBackgroundResource(int resId) {
        super.setBackgroundResource(resId);
        targetResourceValues.put(android.R.attr.background, resId);
    }
}
