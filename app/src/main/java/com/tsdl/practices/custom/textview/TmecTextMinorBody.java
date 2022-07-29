package com.tsdl.practices.custom.textview;

import android.content.Context;
import android.util.AttributeSet;

public class TmecTextMinorBody extends TmecBaseTextView {

    private static final int TEXT_SIZE = 30;
    private static final int TEXT_PADDING = 2;
    private static final int DEFAULT = 0;
    private static final float LINE_SPACING_ADD = 10f;
    private static final float LINE_SPACING_MULTIPLIER = 1f;

    public TmecTextMinorBody(Context context) {
        this(context,null);
    }

    public TmecTextMinorBody(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public TmecTextMinorBody(Context context, AttributeSet attrs, int defStyleAttr) {
        this(context, attrs, defStyleAttr,0);
    }

    public TmecTextMinorBody(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    @Override
    protected void initView() {
        setTextSize(TEXT_SIZE);
        setPadding(DEFAULT, TEXT_PADDING, DEFAULT, TEXT_PADDING);
        setLineSpacing(LINE_SPACING_ADD, LINE_SPACING_MULTIPLIER);
        setFallbackLineSpacing(false);
    }

    @Override
    protected void setWeight(int mode) {

    }
}
