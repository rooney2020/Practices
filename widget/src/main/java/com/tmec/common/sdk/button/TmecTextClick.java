package com.tmec.common.sdk.button;

import android.content.Context;
import android.util.AttributeSet;

import com.tmec.common.sdk.R;
import com.tmec.common.sdk.base.SkinManager;
import com.tmec.common.sdk.base.SkinMode;
import com.tmec.common.sdk.textview.TmecTextExplainTitle;

public class TmecTextClick extends TmecTextExplainTitle implements SkinManager.OnSkinModeChangeListener {

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
        SkinManager.Singleton.getInstance().register(this);
        setMaxHeight(MAX_HEIGHT);
        setPadding(0,0,0,BOTTOM_HEIGHT);
        setTextColorMode(TEXT_COLOR_HIGHLIGHT);
        updateImage();
    }

    private void updateImage() {
        if (SkinManager.Singleton.getInstance().isNight()) {
            setBackground(getContext().getResources().getDrawable(R.drawable.btn_text_click_night));
        } else {
            setBackground(getContext().getResources().getDrawable(R.drawable.btn_text_click));
        }
    }

    @Override
    public void onSkinModeChange(SkinMode skinMode) {
        super.onSkinModeChange(skinMode);
        updateImage();
    }
}
