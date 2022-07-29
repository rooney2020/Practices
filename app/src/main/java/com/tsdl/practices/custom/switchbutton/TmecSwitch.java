package com.tsdl.practices.custom.switchbutton;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.Switch;

import com.tsdl.practices.R;
import com.tsdl.practices.custom.base.ISkinChangedListener;
import com.tsdl.practices.custom.base.TmecSkinConfigurationManager;

public class TmecSwitch extends Switch implements ISkinChangedListener {


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
        TmecSkinConfigurationManager.getInstance().register(this);
        updateSwitchDrawables();
    }


    @Override
    public void onSkinChanged() {
        updateSwitchDrawables();
    }

    private void updateSwitchDrawables() {
        if (TmecSkinConfigurationManager.getInstance().isNight()) {
            setBackgroundResource(R.drawable.slt_switch_night_bg);
        } else {
            setBackgroundResource(R.drawable.slt_switch_bg);
        }
    }
}
