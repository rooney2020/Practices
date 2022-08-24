package com.tmec.common.sdk.tab;

import android.content.Context;
import android.content.res.Configuration;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.util.Log;
import android.util.SparseIntArray;
import android.view.View;
import android.widget.HorizontalScrollView;

import androidx.annotation.NonNull;

import com.tmec.common.sdk.R;
import com.tmec.common.sdk.util.AttributeHelper;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * @author : Lizz
 * e-mail : Zhaozhao_li@human-horizons.com
 * time   : 2019/9/17 15:56
 * desc   : This is a HHHorizontalScrollView
 * version: 1.0
 */
public class BaseHorizontalScrollView extends HorizontalScrollView {
    private SparseIntArray targetResourceValues;
    private int[] targetAttributeArray = new int[]{
        android.R.attr.scrollbarThumbHorizontal,
        android.R.attr.scrollbarTrackHorizontal,
        android.R.attr.background
    };

    public BaseHorizontalScrollView(Context context) {
        this(context, null);
    }

    public BaseHorizontalScrollView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public BaseHorizontalScrollView(Context context, AttributeSet attrs, int defStyleAttr) {
        this(context, attrs, defStyleAttr, R.style.HHHorizontalScrollbarStyle);
    }

    public BaseHorizontalScrollView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        targetResourceValues = AttributeHelper.readAttributeValues(context, attrs,
            targetAttributeArray, defStyleAttr, defStyleRes);
    }

    @Override
    public void onConfigurationChanged(@NonNull Configuration newConfig) {
        Log.d("scroll", "onOverlayChanged");
        if (targetResourceValues == null || targetResourceValues.size() == 0) {
            return;
        }
        int thumbResourceId = targetResourceValues.get(android.R.attr.scrollbarThumbHorizontal);
        int trackResourceId = targetResourceValues.get(android.R.attr.scrollbarTrackHorizontal);
        try {
            Field mScrollCacheField = View.class.getDeclaredField("mScrollCache");
            mScrollCacheField.setAccessible(true);

            Object mScrollCache = mScrollCacheField.get(this);
            Field scrollBarField = mScrollCache.getClass().getDeclaredField("scrollBar");
            scrollBarField.setAccessible(true);

            Object scrollBar = scrollBarField.get(mScrollCache);
            if (AttributeHelper.isValid((thumbResourceId))) {
                Method method = scrollBar.getClass().getDeclaredMethod("setHorizontalThumbDrawable", Drawable.class);
                method.setAccessible(true);
                method.invoke(scrollBar, getResources().getDrawable(R.drawable.scroll_progress_shape));
            }

            if (AttributeHelper.isValid(trackResourceId)) {
                Method method = scrollBar.getClass().getDeclaredMethod("setHorizontalTrackDrawable", Drawable.class);
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
