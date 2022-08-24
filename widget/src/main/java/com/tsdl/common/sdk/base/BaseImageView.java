package com.tsdl.common.sdk.base;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.Configuration;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.widget.ImageView;

import androidx.annotation.Nullable;

import com.tsdl.common.sdk.R;
import com.tsdl.common.sdk.core.SkinManager;

@SuppressLint("AppCompatCustomView")
public class BaseImageView extends ImageView {

    private int mSrcResId;
    private int mBackgroundResId;

    public BaseImageView(Context context) {
        this(context, null);
    }

    public BaseImageView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public BaseImageView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        this(context, attrs, defStyleAttr, 0);
    }

    public BaseImageView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        initAttrs(attrs);
    }

    @SuppressLint("CustomViewStyleable")
    private void initAttrs(AttributeSet attrs) {
        TypedArray array = null;
        try {
            array = getContext().obtainStyledAttributes(attrs, R.styleable.BaseView);
            mSrcResId = array.getResourceId(R.styleable.BaseView_android_src, 0);
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
    public void setImageResource(int resId) {
        mSrcResId = resId;
        updateImage();
    }

    @SuppressLint("UseCompatLoadingForDrawables")
    private void updateImage() {
        if (mSrcResId != 0) {
            try {
                setImageDrawable(SkinManager.getInstance().getDrawable(getContext(), mSrcResId));
            } catch (Exception ignored) {

            }
        }
        if (mBackgroundResId != 0) {
            try {
                setBackground(SkinManager.getInstance().getDrawable(getContext(), mBackgroundResId));
            } catch (Exception ignored) {

            }
        }
    }

    @Override
    protected void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        updateImage();
    }
}
