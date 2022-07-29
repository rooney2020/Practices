package com.tsdl.practices.custom.base;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.RadioButton;

import com.tsdl.practices.R;

public class TmecRadioButton extends RadioButton implements ISkinChangedListener{
    private Context mContext;

    public TmecRadioButton(Context context) {
        this(context,null);
    }

    public TmecRadioButton(Context context, AttributeSet attrs) {
        super(context, attrs);
        mContext = context;
        TmecSkinConfigurationManager.getInstance().register(this);
        setButtonDrawable(0);
        updateRadioStyle();
    }

    @Override
    public void onSkinChanged() {
        updateRadioStyle();
    }

    private void updateRadioStyle() {
        Log.d("TsSkinConfigurationManager", "updateRadioStyle: ");
        if (TmecSkinConfigurationManager.getInstance().isNight()) {
            setBackground(mContext.getResources().getDrawable(R.drawable.slt_rad_night));
        } else {
            setBackground(mContext.getResources().getDrawable(R.drawable.slt_rad_light));
        }
    }
}
