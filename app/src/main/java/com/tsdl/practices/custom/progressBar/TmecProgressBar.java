package com.tsdl.practices.custom.progressBar;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.ProgressBar;

import com.tsdl.practices.R;
import com.tsdl.practices.custom.base.ISkinChangedListener;
import com.tsdl.practices.custom.base.TmecSkinConfigurationManager;

public class TmecProgressBar extends ProgressBar implements ISkinChangedListener {
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
        TmecSkinConfigurationManager.getInstance().register(this);
        updateProgressBg();
    }

    /**
     * update progressBar background
     */
    private void updateProgressBg() {
        Log.e("TsSkinConfigurationManager", "updateTextColor: ");
        if (TmecSkinConfigurationManager.getInstance().isNight()) {
            setProgressDrawable(mContext.getResources().getDrawable(R.drawable.progressbar_night_bg));
        } else {
            setProgressDrawable(mContext.getResources().getDrawable(R.drawable.progressbar_day_bg));
        }
    }

    @Override
    public void onSkinChanged() {
        updateProgressBg();
    }
}
