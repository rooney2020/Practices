package com.tmec.common.sdk.base;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.Configuration;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.util.SparseIntArray;
import android.view.View;

import androidx.annotation.Nullable;

import com.tmec.common.sdk.R;
import com.tmec.common.sdk.core.SkinManager;
import com.tmec.common.sdk.tab.BaseSoundUtils;
import com.tmec.common.sdk.util.AttributeHelper;

public class BaseView extends View {

    public int mHrytSoundEffect;
    private SparseIntArray targetResourceValues;
    private int[] targetAttributeArray = new int[]{
            android.R.attr.background
    };
    private int mBackgroundResId;

    public BaseView(Context context) {
        this(context, null);
    }

    public BaseView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public BaseView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        this(context, attrs, defStyleAttr, 0);
    }

    public BaseView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        initAttrs(attrs);
        mHrytSoundEffect = BaseSoundUtils.initAttribute(context, attrs);
        targetResourceValues = AttributeHelper.readAttributeValues(context, attrs, targetAttributeArray,
                defStyleAttr, defStyleRes);
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

    @Override
    public void playSoundEffect(int soundConstant) {
        super.playSoundEffect(mHrytSoundEffect);
    }

    /**
     * set HHPlaySoundEffect
     *
     * @param soundEffect
     */
    public void setHHPlaySoundEffect(int soundEffect) {
        mHrytSoundEffect = soundEffect;
    }

    @Override
    public void setBackgroundResource(int resId) {
        super.setBackgroundResource(resId);
        targetResourceValues.put(android.R.attr.background, resId);
    }
}
