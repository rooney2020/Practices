package com.tmec.common.sdk.textview;

import android.content.Context;
import android.util.AttributeSet;

public class TmecTextMinorTitle extends TmecBaseTextView {

    private static final int TEXT_SIZE = 30;
    private static final int MAX_HEIGHT = 35;

    public TmecTextMinorTitle(Context context) {
        this(context,null);
    }

    public TmecTextMinorTitle(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public TmecTextMinorTitle(Context context, AttributeSet attrs, int defStyleAttr) {
        this(context, attrs, defStyleAttr,0);
    }

    public TmecTextMinorTitle(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    @Override
    protected void initView() {
        setTextSize(TEXT_SIZE);
        setMaxHeight(MAX_HEIGHT);
        setIncludeFontPadding(false);
    }

    @Override
    protected void setWeight(int mode) {

    }
}
