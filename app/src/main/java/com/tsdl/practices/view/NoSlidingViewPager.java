package com.tsdl.practices.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.viewpager.widget.ViewPager;

public class NoSlidingViewPager extends ViewPager implements View.OnTouchListener {

    public NoSlidingViewPager(@NonNull Context context) {
        this(context, null);
    }

    public NoSlidingViewPager(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        setOnTouchListener(this);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        return super.onInterceptTouchEvent(ev);
    }


    @Override
    public boolean onTouch(View v, MotionEvent event) {
        return true;
    }
}
