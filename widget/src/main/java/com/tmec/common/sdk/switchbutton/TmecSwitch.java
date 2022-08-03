package com.tmec.common.sdk.switchbutton;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.Switch;

import com.tmec.common.sdk.R;
import com.tmec.common.sdk.base.SkinManager;
import com.tmec.common.sdk.base.SkinMode;

public class TmecSwitch extends Switch implements SkinManager.OnSkinModeChangeListener {


    public TmecSwitch(Context context) {
        this(context, null);
    }

    public TmecSwitch(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public TmecSwitch(Context context, AttributeSet attrs, int defStyleAttr) {
        this(context, attrs, defStyleAttr, R.style.TmecSwitch);
    }

    /**
     * Construction method.
     *
     * @param context      context
     * @param attrs        attrs
     * @param defStyleAttr defStyleAttr
     * @param defStyleRes  defStyleRes
     */
    public TmecSwitch(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        SkinManager.Singleton.getInstance().register(this);
        updateSwitchDrawables();
    }


    @Override
    public void onSkinModeChange(SkinMode skinMode) {
        updateSwitchDrawables();
    }

    private void updateSwitchDrawables() {
        if (SkinManager.Singleton.getInstance().isNight()) {
            setBackgroundResource(R.drawable.slt_switch_night_bg);
        } else {
            setBackgroundResource(R.drawable.slt_switch_bg);
        }
    }
}
