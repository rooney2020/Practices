package com.tmec.common.sdk.button;

import android.content.Context;
import android.content.res.ColorStateList;
import android.util.AttributeSet;
import android.widget.Button;

import com.tmec.common.sdk.R;
import com.tmec.common.sdk.base.SkinManager;
import com.tmec.common.sdk.base.SkinMode;

public abstract class TmecButton extends Button implements SkinManager.OnSkinModeChangeListener {

    protected int mTextColor;
    protected ColorStateList mNightTextColor;
    protected int mButtonBg;
    protected int mNightButtonBg;
    protected static final int PADDING_HORIZONTAL = 30;

    public TmecButton(Context context) {
        this(context, null);
    }

    public TmecButton(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public TmecButton(Context context, AttributeSet attrs, int defStyleAttr) {
        this(context, attrs, defStyleAttr, R.style.TmecButton);
    }

    /**
     * Construction method.
     *
     * @param context      context
     * @param attrs        attrs
     * @param defStyleAttr defStyleAttr
     * @param defStyleRes  defStyleRes
     */
    public TmecButton(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init();
    }

    protected abstract void init();


    @Override
    public void onSkinModeChange(SkinMode skinMode) {
        updateTmecButtonState();
    }

    protected void updateTmecButtonState() {
        if (SkinManager.Singleton.getInstance().isNight()) {
            setTextColor(mNightTextColor);
            setBackgroundResource(mNightButtonBg);
        } else {
            setTextColor(mTextColor);
            setBackgroundResource(mButtonBg);
        }
        setPadding(PADDING_HORIZONTAL, 0, PADDING_HORIZONTAL, 0);
    }
}
