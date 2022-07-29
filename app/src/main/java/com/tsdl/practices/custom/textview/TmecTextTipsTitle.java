package com.tsdl.practices.custom.textview;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.TextView;

import com.tsdl.practices.R;
import com.tsdl.practices.custom.base.ISkinChangedListener;
import com.tsdl.practices.custom.base.TmecSkinConfigurationManager;

public class TmecTextTipsTitle extends TmecBaseTextView{

    private static final String TAG = "TmecTextTipsTitle";
    private static final int TEXT_SIZE = 24;
    private static final int MAX_HEIGHT = 29;

    public TmecTextTipsTitle(Context context) {
        this(context, null);
    }

    public TmecTextTipsTitle(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public TmecTextTipsTitle(Context context, AttributeSet attrs, int defStyleAttr) {
        this(context, attrs, defStyleAttr, 0);
    }

    public TmecTextTipsTitle(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
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
