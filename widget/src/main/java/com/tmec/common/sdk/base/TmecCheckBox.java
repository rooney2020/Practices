package com.tmec.common.sdk.base;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.CheckBox;

import com.tmec.common.sdk.R;

public class TmecCheckBox extends CheckBox implements SkinManager.OnSkinModeChangeListener {
    private Context mContext;
    public TmecCheckBox(Context context) {
        this(context,null);
    }

    public TmecCheckBox(Context context, AttributeSet attrs) {
        super(context, attrs);
        mContext = context;
        SkinManager.Singleton.getInstance().register(this);
        setButtonDrawable(0);
        updateCheckBoxStyle();
    }

    @Override
    public void onSkinModeChange(SkinMode skinMode) {
        updateCheckBoxStyle();
    }

    private void updateCheckBoxStyle() {
        Log.d("TsSkinConfigurationManager", "updateCheckBoxStyle: ");
        if (SkinManager.Singleton.getInstance().isNight()) {
            setBackground(mContext.getResources().getDrawable(R.drawable.slt_cb_night));
        } else {
            setBackground(mContext.getResources().getDrawable(R.drawable.slt_cb_light));
        }
    }
}
