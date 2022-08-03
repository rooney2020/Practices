package com.tmec.common.sdk.button;

import android.content.Context;
import android.util.AttributeSet;

import com.tmec.common.sdk.R;
import com.tmec.common.sdk.base.SkinManager;

public class TmecButtonCancel extends TmecButton {

    public TmecButtonCancel(Context context) {
        super(context);
    }

    public TmecButtonCancel(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public TmecButtonCancel(Context context, AttributeSet attrs, int defStyleAttr) {
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
    public TmecButtonCancel(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    @Override
    protected void init() {
        SkinManager.Singleton.getInstance().register(this);
        mTextColor = getResources().getColor(R.color.tmec_btn_text_color);
        mNightTextColor = getResources().getColorStateList(R.color.slt_btn_normal_night);
        mButtonBg = R.drawable.slt_btn_cancel;
        mNightButtonBg = R.drawable.slt_btn_cancel_night;
        updateTmecButtonState();
    }
}
