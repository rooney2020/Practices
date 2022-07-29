package com.tsdl.practices.custom.base;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.tsdl.practices.R;

public class TmecTextTips extends TextView implements ISkinChangedListener {

    private static final int TEXT_SIZE = 28;
    private static final int PADDING_TOP = 28;
    private static final int PADDING_HORIZONTAL = 36;
    private static final int PADDING_BOTTOM = 40;

    public static final int TIPS_TOP_MODE = 0;
    public static final int TIPS_BOTTOM_MODE = 1;

    private int mTipsMode;
    private int mTextColor;
    private int mNightTextColor;


    public TmecTextTips(Context context) {
        this(context, null);
    }

    public TmecTextTips(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public TmecTextTips(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        this(context, attrs, defStyleAttr, 0);
    }

    public TmecTextTips(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        TmecSkinConfigurationManager.getInstance().register(this);
        TypedArray array = null;
        try {
            array = getContext().obtainStyledAttributes(attrs, R.styleable.TmecTextTips);
            mTipsMode = array.getInteger(R.styleable.TmecTextTips_tips_mode, TIPS_TOP_MODE);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (array != null) {
                array.recycle();
            }
        }
        setTextSize(TEXT_SIZE);
        mNightTextColor = getResources().getColor(R.color.text_title_normal_night);
        mTextColor = getResources().getColor(R.color.text_title_normal);
        updateTextTips();
    }

    @Override
    public void onSkinChanged() {
        updateTextTips();
    }

    /**
     * set top or bottom mode.
     *
     * @param mode TIPS_TOP_MODE TIPS_BOTTOM_MODE
     */
    public void setTipsMode(int mode) {
        mTipsMode = mode;
        updateTextTips();
    }

    private void updateTextTips() {
        if (TmecSkinConfigurationManager.getInstance().isNight()) {
            setTextColor(mNightTextColor);
            if (mTipsMode == TIPS_TOP_MODE) {
                setBackgroundResource(R.drawable.bg_chatbox_top_night);
            } else if (mTipsMode == TIPS_BOTTOM_MODE) {
                setBackgroundResource(R.drawable.bg_chatbox_bottom_night);
            }
        } else {
            setTextColor(mTextColor);
            if (mTipsMode == TIPS_TOP_MODE) {
                setBackgroundResource(R.drawable.bg_chatbox_top);
            } else if (mTipsMode == TIPS_BOTTOM_MODE) {
                setBackgroundResource(R.drawable.bg_chatbox_bottom);
            }
        }
        setPadding(PADDING_HORIZONTAL, PADDING_TOP, PADDING_HORIZONTAL, PADDING_BOTTOM);
    }
}
