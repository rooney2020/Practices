package com.tsdl.common.sdk.grid;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.Configuration;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

import androidx.annotation.Nullable;

import com.tsdl.common.sdk.R;

public class BaseGridView extends GridView implements AdapterView.OnItemClickListener {

    private OnItemClickListener mOnItemClickListener;

    public BaseGridView(Context context) {
        this(context, null);
    }

    public BaseGridView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public BaseGridView(Context context, AttributeSet attrs, int defStyleAttr) {
        this(context, attrs, defStyleAttr, 0);
    }

    public BaseGridView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        initAttrs(attrs);
        super.setOnItemClickListener(this);
        setSelector(new ColorDrawable(Color.TRANSPARENT));
    }

    @SuppressLint("CustomViewStyleable")
    private void initAttrs(AttributeSet attrs) {
        TypedArray array = null;
        try {
            array = getContext().obtainStyledAttributes(attrs, R.styleable.BaseGridView);
//            mBackgroundResId = array.getResourceId(R.styleable.BaseView_android_background, 0);
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
    }

    @Override
    public void setOnItemClickListener(@Nullable OnItemClickListener listener) {
        mOnItemClickListener = listener;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        for (int i = 0; i < getChildCount(); i++) {
            View layout = getChildAt(i);
            if (layout != null) {
                View viewById = layout.findViewById(R.id.iv_image_bg);
                if (viewById != null) {
                    viewById.setBackground(null);
                }
            }
        }
        View viewById = view.findViewById(R.id.iv_image_bg);
        if (viewById != null) {
            viewById.setBackgroundResource(R.drawable.bg_icon);
        }
        if (mOnItemClickListener != null) {
            mOnItemClickListener.onItemClick(parent, view, position, id);
        }
    }
}
