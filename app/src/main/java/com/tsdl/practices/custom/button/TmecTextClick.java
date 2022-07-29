package com.tsdl.practices.custom.button;

import android.content.Context;
import android.util.AttributeSet;

import com.tsdl.practices.R;
import com.tsdl.practices.custom.base.ISkinChangedListener;
import com.tsdl.practices.custom.base.TmecSkinConfigurationManager;
import com.tsdl.practices.custom.textview.TmecTextExplainTitle;

public class TmecTextClick extends TmecTextExplainTitle implements ISkinChangedListener {

    private static final int MAX_HEIGHT = 36;
    private static final int BOTTOM_HEIGHT = 8;

    public TmecTextClick(Context context) {
        this(context,null);
    }

    public TmecTextClick(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public TmecTextClick(Context context, AttributeSet attrs, int defStyleAttr) {
        this(context, attrs, defStyleAttr,0);
    }

    public TmecTextClick(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        TmecSkinConfigurationManager.getInstance().register(this);
        setMaxHeight(MAX_HEIGHT);
        setPadding(0,0,0,BOTTOM_HEIGHT);
        setTextColorMode(TEXT_COLOR_HIGHLIGHT);
        updateImage();
    }

    private void updateImage() {
        if (TmecSkinConfigurationManager.getInstance().isNight()) {
            setBackground(getContext().getResources().getDrawable(R.drawable.btn_text_click_night));
        } else {
            setBackground(getContext().getResources().getDrawable(R.drawable.btn_text_click));
        }
    }

    @Override
    public void onSkinChanged() {
        super.onSkinChanged();
        updateImage();
    }
}
