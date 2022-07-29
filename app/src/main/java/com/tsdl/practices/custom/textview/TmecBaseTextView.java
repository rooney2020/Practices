package com.tsdl.practices.custom.textview;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.TextView;

import com.tsdl.practices.R;
import com.tsdl.practices.custom.base.ISkinChangedListener;
import com.tsdl.practices.custom.base.TmecSkinConfigurationManager;

public abstract class TmecBaseTextView extends TextView implements ISkinChangedListener {

    private static final String TAG = "TmecBaseTextView";
    public static final int TEXT_COLOR_HIGHLIGHT = 0;
    public static final int TEXT_COLOR_NORMAL = 1;
    public static final int TEXT_COLOR_DISABLE = 2;
    public static final int TEXT_COLOR_AUX = 3;
    public static final int TEXT_COLOR_MINOR = 4;
    private int mTextColor;
    private int mTextNightColor;

    public TmecBaseTextView(Context context) {
        this(context, null);
    }

    public TmecBaseTextView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public TmecBaseTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        this(context, attrs, defStyleAttr, 0);
    }

    public TmecBaseTextView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        initView();
        TmecSkinConfigurationManager.getInstance().register(this);
        TypedArray array = getContext().obtainStyledAttributes(attrs, R.styleable.TmecBaseTextView);
        mTextColor = array.getInteger(R.styleable.TmecBaseTextView_tmec_text_color_mode,
                TEXT_COLOR_NORMAL);
        setTextColorMode(mTextColor);
    }

    protected abstract void initView();

    protected abstract void setWeight(int mode);

    public void setTextColorMode(int mode) {
        Log.d(TAG, "setTitleTextColorMode: " + mode);
        setWeight(mode);
        if (mode >= 0) {
            switch (mode) {
                case TEXT_COLOR_HIGHLIGHT:
                    mTextColor = getResources().getColor(R.color.text_title_highlight);
                    mTextNightColor = getResources().getColor(R.color.text_title_highlight_night);
                    break;
                case TEXT_COLOR_NORMAL:
                    mTextColor = getResources().getColor(R.color.text_title_normal);
                    mTextNightColor = getResources().getColor(R.color.text_title_normal_night);
                    break;
                case TEXT_COLOR_DISABLE:
                    mTextColor = getResources().getColor(R.color.text_title_disable);
                    mTextNightColor = getResources().getColor(R.color.text_title_disable_night);
                    break;
                case TEXT_COLOR_AUX:
                    mTextColor = getResources().getColor(R.color.text_title_aux);
                    mTextNightColor = getResources().getColor(R.color.text_title_aux_night);
                    break;
                case TEXT_COLOR_MINOR:
                    mTextColor = getResources().getColor(R.color.text_title_minor);
                    mTextNightColor = getResources().getColor(R.color.text_title_minor_night);
                    break;
            }
            updateTextColor();
        }
    }

    public void updateTextColor() {
        Log.e("TsSkinConfigurationManager", "updateTextColor: " + mTextColor);
        if (TmecSkinConfigurationManager.getInstance().isNight()) {
            setTextColor(mTextNightColor);
        } else {
            setTextColor(mTextColor);
        }
    }

    @Override
    public void onSkinChanged() {
        updateTextColor();
    }
}
