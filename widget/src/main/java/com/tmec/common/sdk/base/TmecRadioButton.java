package com.tmec.common.sdk.base;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.RadioButton;

import com.tmec.common.sdk.R;

public class TmecRadioButton extends RadioButton implements SkinManager.OnSkinModeChangeListener{
    private Context mContext;

    public TmecRadioButton(Context context) {
        this(context,null);
    }

    public TmecRadioButton(Context context, AttributeSet attrs) {
        super(context, attrs);
        mContext = context;
        SkinManager.Singleton.getInstance().register(this);
        setButtonDrawable(0);
        updateRadioStyle();
    }

    @Override
    public void onSkinModeChange(SkinMode skinMode) {
        updateRadioStyle();
    }

    private void updateRadioStyle() {
        Log.d("TsSkinConfigurationManager", "updateRadioStyle: ");
        if (SkinManager.Singleton.getInstance().isNight()) {
            setBackground(mContext.getResources().getDrawable(R.drawable.slt_rad_night));
        } else {
            setBackground(mContext.getResources().getDrawable(R.drawable.slt_rad_light));
        }
    }
}
