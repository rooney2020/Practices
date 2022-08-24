package com.tsdl.common.sdk.base;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.Configuration;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.util.SparseIntArray;
import android.widget.RelativeLayout;

import androidx.annotation.Nullable;

import com.tsdl.common.sdk.R;
import com.tsdl.common.sdk.core.SkinManager;
import com.tsdl.common.sdk.sound.BaseSoundUtils;
import com.tsdl.common.sdk.util.AttributeHelper;

public class BaseRelativeLayout extends RelativeLayout {

    public int mSoundEffect;
    private SparseIntArray targetResourceValues;
    private int[] targetAttributeArray = new int[]{
            android.R.attr.background
    };
    private int mBackgroundResId;

    public BaseRelativeLayout(Context context) {
        this(context, null);
    }

    public BaseRelativeLayout(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public BaseRelativeLayout(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        this(context, attrs, defStyleAttr, 0);
    }

    public BaseRelativeLayout(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        initAttrs(attrs);
        mSoundEffect = BaseSoundUtils.initAttribute(context, attrs);
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
        super.playSoundEffect(mSoundEffect);
    }

    /**
     * set BasePlaySoundEffect
     *
     * @param soundEffect
     */
    public void setBasePlaySoundEffect(int soundEffect) {
        mSoundEffect = soundEffect;
    }

    @Override
    public void setBackgroundResource(int resId) {
        super.setBackgroundResource(resId);
        targetResourceValues.put(android.R.attr.background, resId);
    }
}
