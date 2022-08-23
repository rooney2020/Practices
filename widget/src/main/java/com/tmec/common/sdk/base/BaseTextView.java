package com.tmec.common.sdk.base;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.Configuration;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.tmec.common.sdk.R;
import com.tmec.common.sdk.core.SkinManager;

@SuppressLint("AppCompatCustomView")
public class BaseTextView extends TextView {

    private int mTextColorResId;

    public BaseTextView(Context context) {
        this(context, null);
    }

    public BaseTextView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public BaseTextView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        this(context, attrs, defStyleAttr, 0);
    }

    public BaseTextView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        initAttrs(attrs);
    }

    @SuppressLint("CustomViewStyleable")
    private void initAttrs(AttributeSet attrs) {
        TypedArray array = null;
        try {
            array = getContext().obtainStyledAttributes(attrs, R.styleable.BaseView);
            mTextColorResId = array.getResourceId(R.styleable.BaseView_android_textColor, 0);
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
        if (mTextColorResId != 0) {
            try {
                setTextColor(SkinManager.getInstance().getColor(getContext(), mTextColorResId));
            } catch (Exception ignored) {

            }
        }
    }
}
