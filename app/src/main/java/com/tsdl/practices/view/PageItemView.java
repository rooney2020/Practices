package com.tsdl.practices.view;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.tsdl.practices.R;

public class PageItemView extends ConstraintLayout {

    private View mRootView;
    private ImageView mIvIcon;
    private TextView mTvTitle;

    public PageItemView(Context context) {
        this(context, null, 0, 0);
    }

    public PageItemView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0, 0);
    }

    public PageItemView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        this(context, attrs, defStyleAttr, 0);
    }

    public PageItemView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(context);
    }

    private void init(Context context) {
        mRootView = (ConstraintLayout) LayoutInflater.from(getContext()).inflate(R.layout.item_page, this, true);
        mIvIcon = mRootView.findViewById(R.id.iv_icon);
        mTvTitle = mRootView.findViewById(R.id.tv_title);
    }

    public PageItemView setIcon(@Nullable Drawable icon) {
        mIvIcon.setImageDrawable(icon);
        return this;
    }

    public PageItemView setText(String text) {
        mTvTitle.setText(text);
        return this;
    }

    public PageItemView setTextSize(int size) {
        mTvTitle.setTextSize(size);
        return this;
    }
}
