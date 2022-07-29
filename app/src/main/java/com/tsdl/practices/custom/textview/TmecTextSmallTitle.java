package com.tsdl.practices.custom.textview;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.TextView;

import com.tsdl.practices.R;
import com.tsdl.practices.custom.base.ISkinChangedListener;
import com.tsdl.practices.custom.base.TmecSkinConfigurationManager;

public class TmecTextSmallTitle extends TmecBaseTextView {

    private static final String TAG = "TmecTextSmallTitle";
    private static final int TEXT_SIZE = 32;
    private static final int MAX_HEIGHT = 37;

    public TmecTextSmallTitle(Context context) {
        this(context, null);
    }

    public TmecTextSmallTitle(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public TmecTextSmallTitle(Context context, AttributeSet attrs, int defStyleAttr) {
        this(context, attrs, defStyleAttr, 0);
    }

    public TmecTextSmallTitle(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    @Override
    protected void initView() {
        Log.d(TAG, "initView: ");
        setTextSize(TEXT_SIZE);
        setMaxHeight(MAX_HEIGHT);
        setIncludeFontPadding(false);
    }

    @Override
    protected void setWeight(int mode) {

    }

}
