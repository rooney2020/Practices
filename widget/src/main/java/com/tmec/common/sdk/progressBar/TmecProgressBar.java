package com.tmec.common.sdk.progressBar;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.ProgressBar;

import com.tmec.common.sdk.R;
import com.tmec.common.sdk.base.SkinManager;
import com.tmec.common.sdk.base.SkinMode;

public class TmecProgressBar extends ProgressBar implements SkinManager.OnSkinModeChangeListener {
    private Context mContext;


    public TmecProgressBar(Context context) {
        this(context, null);
        this.mContext = context;
    }

    public TmecProgressBar(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
        this.mContext = context;
    }

    public TmecProgressBar(Context context, AttributeSet attrs, int defStyleAttr) {
        this(context, attrs, defStyleAttr, 0);
        this.mContext = context;
    }

    /**
     * Construction method.
     *
     * @param context context
     * @param attrs attrs
     * @param defStyleAttr defStyleAttr
     * @param defStyleRes defStyleRes
     */
    public TmecProgressBar(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        this.mContext = context;
        SkinManager.Singleton.getInstance().register(this);
        updateProgressBg();
    }

    /**
     * update progressBar background
     */
    private void updateProgressBg() {
        Log.e("TsSkinConfigurationManager", "updateTextColor: ");
        if (SkinManager.Singleton.getInstance().isNight()) {
            setProgressDrawable(mContext.getResources().getDrawable(R.drawable.progressbar_night_bg));
        } else {
            setProgressDrawable(mContext.getResources().getDrawable(R.drawable.progressbar_day_bg));
        }
    }

    @Override
    public void onSkinModeChange(SkinMode skinMode) {
        updateProgressBg();
    }
}
