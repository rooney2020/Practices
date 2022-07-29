package com.tsdl.practices.custom.textview;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.TextView;

import com.tsdl.practices.R;
import com.tsdl.practices.custom.base.ISkinChangedListener;
import com.tsdl.practices.custom.base.TmecSkinConfigurationManager;

public class TmecTextTitle extends TmecBaseTextView {

    private static final String TAG = "TmecTextTitle";
    private static final int MAX_HEIGHT = 41;
    private static final int TEXT_SIZE = 36;

    public TmecTextTitle(Context context) {
        this(context, null);
    }

    public TmecTextTitle(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public TmecTextTitle(Context context, AttributeSet attrs, int defStyleAttr) {
        this(context, attrs, defStyleAttr, R.style.TmecTextTitleWeight);
    }

    public TmecTextTitle(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    @Override
    protected void initView() {
        Log.d(TAG, "initView ");
        setTextSize(TEXT_SIZE);
        setMaxHeight(MAX_HEIGHT);
        setIncludeFontPadding(false);
    }

    @Override
    protected void setWeight(int mode) {
        if (mode == TEXT_COLOR_DISABLE
                || mode == TEXT_COLOR_AUX
                || mode == TEXT_COLOR_MINOR) {
            setTypeface(null,Typeface.NORMAL);
        }
    }
}
