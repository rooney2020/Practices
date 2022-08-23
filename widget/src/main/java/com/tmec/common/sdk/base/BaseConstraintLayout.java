package com.tmec.common.sdk.base;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.Configuration;
import android.content.res.TypedArray;
import android.util.AttributeSet;

import androidx.constraintlayout.widget.ConstraintLayout;

import com.tmec.common.sdk.R;
import com.tmec.common.sdk.core.SkinManager;

public class BaseConstraintLayout extends ConstraintLayout {

    private int mBackgroundResId;

    public BaseConstraintLayout(Context context) {
        this(context, null);
    }

    public BaseConstraintLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public BaseConstraintLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initAttrs(attrs);
    }

    @SuppressLint("CustomViewStyleable")
    private void initAttrs(AttributeSet attrs) {
        TypedArray array = null;
        try {
            array = getContext().obtainStyledAttributes(attrs, R.styleable.BaseView);
            mBackgroundResId = array.getResourceId(R.styleable.BaseView_android_background, 0);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (array != null) {
                array.recycle();
            }
        }
    }

    @Override
    protected void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        if (mBackgroundResId != 0) {
            try {
                setBackground(SkinManager.getInstance().getDrawable(getContext(), mBackgroundResId));
            } catch (Exception ignored) {

            }
        }
    }
}
