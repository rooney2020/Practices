package com.tsdl.common.sdk.base;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.Configuration;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.widget.EditText;

import com.tsdl.common.sdk.R;
import com.tsdl.common.sdk.core.SkinManager;

@SuppressLint("AppCompatCustomView")
public class BaseEditText extends EditText {

    private int mTextColorResId;
    private int mTextColorHintResId;

    public BaseEditText(Context context) {
        this(context, null);
    }

    public BaseEditText(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public BaseEditText(Context context, AttributeSet attrs, int defStyleAttr) {
        this(context, attrs, defStyleAttr, 0);
    }

    public BaseEditText(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        initAttrs(attrs);
    }

    @SuppressLint("CustomViewStyleable")
    private void initAttrs(AttributeSet attrs) {
        TypedArray array = null;
        try {
            array = getContext().obtainStyledAttributes(attrs, R.styleable.BaseView);
            mTextColorResId = array.getResourceId(R.styleable.BaseView_android_textColor, 0);
            mTextColorHintResId = array.getResourceId(R.styleable.BaseView_android_textColorHint, 0);
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
        if (mTextColorHintResId != 0) {
            try {
                setHintTextColor(SkinManager.getInstance().getColor(getContext(), mTextColorHintResId));
            } catch (Exception ignored) {

            }
        }
    }
}
