package com.tmec.common.sdk.textview;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;

public class TmecTextSmallBody extends TmecBaseTextView {

    private static final String TAG = "TmecTextSmallBody";
    private static final int TEXT_SIZE = 32;
    private static final int TEXT_PADDING_TOP = 2;
    private static final int TEXT_PADDING_BOTTOM = 3;
    private static final int DEFAULT = 0;
    private static final float LINE_SPACING_ADD = 10f;
    private static final float LINE_SPACING_MULTIPLIER = 1f;

    public TmecTextSmallBody(Context context) {
        this(context, null);
    }

    public TmecTextSmallBody(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public TmecTextSmallBody(Context context, AttributeSet attrs, int defStyleAttr) {
        this(context, attrs, defStyleAttr, 0);
    }

    public TmecTextSmallBody(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    @Override
    protected void initView() {
        Log.d(TAG, "initView ");
        setTextSize(TEXT_SIZE);
        setPadding(DEFAULT, TEXT_PADDING_TOP, DEFAULT,  TEXT_PADDING_BOTTOM);
        setLineSpacing(LINE_SPACING_ADD, LINE_SPACING_MULTIPLIER);
        setFallbackLineSpacing(false);
    }

    @Override
    protected void setWeight(int mode) {

    }


}
