package com.tsdl.practices.custom.button;

import android.content.Context;
import android.content.res.ColorStateList;
import android.util.AttributeSet;

import com.tsdl.practices.R;
import com.tsdl.practices.custom.base.TmecSkinConfigurationManager;

public class TmecButtonNormal extends TmecButton {

    private ColorStateList mTextColor;

    public TmecButtonNormal(Context context) {
        super(context);
    }

    public TmecButtonNormal(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public TmecButtonNormal(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    /**
     * Construction method.
     *
     * @param context      context
     * @param attrs        attrs
     * @param defStyleAttr defStyleAttr
     * @param defStyleRes  defStyleRes
     */
    public TmecButtonNormal(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    @Override
    protected void init() {
        TmecSkinConfigurationManager.getInstance().register(this);
        mTextColor = getResources().getColorStateList(R.color.slt_btn_icon);
        mNightTextColor = getResources().getColorStateList(R.color.slt_btn_normal_night);
        mButtonBg = R.drawable.slt_btn_normal;
        mNightButtonBg = R.drawable.slt_btn_normal_night;
        updateTmecButtonState();
    }

    @Override
    protected void updateTmecButtonState() {
        if (TmecSkinConfigurationManager.getInstance().isNight()) {
            setTextColor(mNightTextColor);
            setBackgroundResource(mNightButtonBg);
        } else {
            setTextColor(mTextColor);
            setBackgroundResource(mButtonBg);
        }
        setPadding(PADDING_HORIZONTAL, 0, PADDING_HORIZONTAL, 0);
    }

}
