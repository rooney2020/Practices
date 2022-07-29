package com.tsdl.practices.custom.seekbar;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.LinearLayout;
import android.widget.SeekBar;

import com.tsdl.practices.R;
import com.tsdl.practices.custom.base.ISkinChangedListener;
import com.tsdl.practices.custom.base.TmecSkinConfigurationManager;
import com.tsdl.practices.custom.textview.TmecTextMinorTitle;

public class TmecSeekBarVoice extends LinearLayout implements ISkinChangedListener {
    private TmecTextMinorTitle mSeekBarLeftName;
    private SeekBar mSeekBar;
    private TmecTextMinorTitle mSeekBarRightValue;

    public TmecSeekBarVoice(Context context) {
        this(context,null);
    }

    public TmecSeekBarVoice(Context context, AttributeSet attrs) {
        super(context, attrs);
        TmecSkinConfigurationManager.getInstance().register(this);
        setContentView();
        init(attrs);
        updateSeekBarImage();
    }


    private void setContentView() {
        LayoutInflater.from(getContext()).inflate(R.layout.tmec_seek_bar_voice,
                this, true);
        mSeekBarLeftName = findViewById(R.id.tv_seekbar_name);
        mSeekBar = findViewById(R.id.sb_voice);
        mSeekBarRightValue = findViewById(R.id.tv_seekbar_value);
    }

    private void init(AttributeSet attributeSet) {
        if (attributeSet != null) {
            TypedArray typedArray = null;
            try {
                typedArray = getContext().obtainStyledAttributes(attributeSet, R.styleable.TmecSeekBarVoice);
                setMax(typedArray.getInt(R.styleable.TmecSeekBarVoice_android_max, 100));
                setProgress(typedArray.getInt(R.styleable.TmecSeekBarVoice_android_progress, 0));
                setSeekBarLeftName(typedArray.getString(R.styleable.TmecSeekBarVoice_tmec_seekbar_left_name));
                setSeekBarRightValue(typedArray.getString(R.styleable.TmecSeekBarVoice_tmec_seekbar_right_value));
            } finally {
                if (typedArray != null) {
                    typedArray.recycle();
                }
            }
        }
    }

    /**
     * set seekbar max length.
     *
     * @param max length
     */
    public void setMax(int max) {
        if (mSeekBar != null) {
            mSeekBar.setMax(max);
        }
    }

    /**
     * get seekbar max length.
     *
     * @return length
     */
    public int getMax() {
        return mSeekBar != null ? mSeekBar.getMax() : 0;
    }

    /**
     * get seekbar current length.
     *
     * @return length
     */
    public int getProgress() {
        return mSeekBar != null ? mSeekBar.getProgress() : 0;
    }

    /**
     * set seekbar current length.
     *
     * @param progress length
     */
    public void setProgress(int progress) {
        if (mSeekBar != null) {
            mSeekBar.setProgress(progress);
        }
    }

    /**
     * set seekbar left show text.
     *
     * @param name text
     */
    public void setSeekBarLeftName(CharSequence name) {
        if (mSeekBarLeftName != null) {
            mSeekBarLeftName.setText(name);
        }
    }

    /**
     * set seekbar right show text.
     *
     * @param value text
     */
    public void setSeekBarRightValue(CharSequence value) {
        if (mSeekBarRightValue != null) {
            mSeekBarRightValue.setText(value);
        }
    }

    /**
     * set seekbar listener.
     *
     * @param listener listener
     */
    public void setOnSeekBarChangeListener(SeekBar.OnSeekBarChangeListener listener) {
        if (mSeekBar != null && listener != null) {
            mSeekBar.setOnSeekBarChangeListener(listener);
        }
    }

    private void updateSeekBarImage() {
        if (TmecSkinConfigurationManager.getInstance().isNight()) {
            mSeekBar.setThumb(getContext().getResources().getDrawable(R.drawable.tmec_seekbar_night_thumb));
            mSeekBar.setProgressDrawable(getContext().getResources().getDrawable(R.drawable.seekbar_day_night_bg));
        } else {
            mSeekBar.setThumb(getContext().getResources().getDrawable(R.drawable.tmec_seekbar_thumb));
            mSeekBar.setProgressDrawable(getContext().getResources().getDrawable(R.drawable.seekbar_day_bg));
        }
    }

    @Override
    public void onSkinChanged() {
        updateSeekBarImage();
    }
}
