package com.tsdl.practices.custom.base;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.CheckBox;

import com.tsdl.practices.R;
import com.tsdl.practices.R;

public class TmecCheckBox extends CheckBox implements ISkinChangedListener {
    private Context mContext;
    public TmecCheckBox(Context context) {
        this(context,null);
    }

    public TmecCheckBox(Context context, AttributeSet attrs) {
        super(context, attrs);
        mContext = context;
        TmecSkinConfigurationManager.getInstance().register(this);
        setButtonDrawable(0);
        updateCheckBoxStyle();
    }

    @Override
    public void onSkinChanged() {
        updateCheckBoxStyle();
    }

    private void updateCheckBoxStyle() {
        Log.d("TsSkinConfigurationManager", "updateCheckBoxStyle: ");
        if (TmecSkinConfigurationManager.getInstance().isNight()) {
            setBackground(mContext.getResources().getDrawable(R.drawable.slt_cb_night));
        } else {
            setBackground(mContext.getResources().getDrawable(R.drawable.slt_cb_light));
        }
    }
}
