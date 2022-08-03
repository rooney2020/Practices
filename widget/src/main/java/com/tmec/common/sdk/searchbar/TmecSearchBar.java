package com.tmec.common.sdk.searchbar;

import android.content.Context;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Gravity;
import android.view.MotionEvent;
import android.widget.EditText;

import com.tmec.common.sdk.R;
import com.tmec.common.sdk.base.SkinManager;
import com.tmec.common.sdk.base.SkinMode;

public class TmecSearchBar extends EditText implements SkinManager.OnSkinModeChangeListener {
    private static final String TAG = "TmecSearchBar";
    private static final int DEFAULT = 0;
    private static final int TEXT_SIZE = 32;
    private static final int PADDING_LEFT = 50;
    private static final int PADDING_RIGHT = 34;
    private static final int MIN_LENGTH = 1;
    private static final int DISTANCE = 100;
    private static final int HIGHT = 88;
    private int mTextColor;
    private int mNightTextColor;
    private int mHintTextColor;
    private int mNightHintTextColor;
    private int mSearchBarBg;
    private int mNightSearchBarBg;
    private Drawable mDeleteIcon;
    private Drawable mNightDeleteIcon;

    public TmecSearchBar(Context context) {
        this(context, null);
    }

    public TmecSearchBar(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public TmecSearchBar(Context context, AttributeSet attrs, int defStyleAttr) {
        this(context, attrs, defStyleAttr, 0);
    }

    public TmecSearchBar(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        SkinManager.Singleton.getInstance().register(this);
        initView();
    }

    private void initView() {
        setPaddingRelative(PADDING_LEFT, DEFAULT, PADDING_RIGHT, DEFAULT);
        setGravity(Gravity.CENTER_VERTICAL);
        setHint(getResources().getString(R.string.searchbar_hint));
        setTextSize(TEXT_SIZE);
        setHeight(HIGHT);
        mNightHintTextColor = getResources().getColor(R.color.tmec_searchbar_night_hint_text_color);
        mNightTextColor = getResources().getColor(R.color.tmec_searchbar_night_text_color);
        mNightSearchBarBg = R.drawable.searchbar_bg_night;
        mNightDeleteIcon = getResources().getDrawable(R.drawable.icon_del_night_sel);
        mTextColor = getResources().getColor(R.color.tmec_searchbar_text_color);
        mHintTextColor = getResources().getColor(R.color.tmec_searchbar_hint_text_color);
        mSearchBarBg = R.drawable.searchbar_bg_day;
        mDeleteIcon = getResources().getDrawable(R.drawable.icon_del_day_sel);
        setFocusable(true);
        setFocusableInTouchMode(true);
        updateView();
        addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                setDrawable();
            }
        });
        setDrawable();
    }

    private void setDrawable() {
        if (length() < MIN_LENGTH) {
            setCompoundDrawablesRelativeWithIntrinsicBounds(null, null,
                    null, null);
        } else {
            if (SkinManager.Singleton.getInstance().isNight()) {
                setCompoundDrawablesRelativeWithIntrinsicBounds(null, null,
                        mNightDeleteIcon, null);
            } else {
                setCompoundDrawablesRelativeWithIntrinsicBounds(null, null,
                        mDeleteIcon, null);
            }

        }
    }


    private void updateView() {
        if (SkinManager.Singleton.getInstance().isNight()) {
            setTextColor(mNightTextColor);
            setHintTextColor(mNightHintTextColor);
            setBackgroundResource(mNightSearchBarBg);
        } else {
            setTextColor(mTextColor);
            setHintTextColor(mHintTextColor);
            setBackgroundResource(mSearchBarBg);
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_UP) {
            int eventX = (int) event.getRawX();
            int eventY = (int) event.getRawY();
            Log.d(TAG, "eventX = " + eventX + "; eventY = " + eventY);
            Rect rect = new Rect();
            getGlobalVisibleRect(rect);
            rect.left = rect.right - DISTANCE;
            if (rect.contains(eventX, eventY)) {
                setText("");
            }
        }
        return super.onTouchEvent(event);
    }

    @Override
    public void onSkinModeChange(SkinMode skinMode) {
        updateView();
    }
}
