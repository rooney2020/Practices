package com.tmec.common.sdk.seekbar;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.SeekBar;

import androidx.constraintlayout.widget.ConstraintLayout;

import com.tmec.common.sdk.R;
import com.tmec.common.sdk.base.SkinManager;
import com.tmec.common.sdk.base.SkinMode;
import com.tmec.common.sdk.textview.TmecTextTipsTitle;

public class TmecSeekBarMedia extends ConstraintLayout implements SkinManager.OnSkinModeChangeListener {
    private SeekBar mMediaSeekBar;
    private TmecTextTipsTitle mStartValue;
    private TmecTextTipsTitle mEndValue;

    public TmecSeekBarMedia(Context context) {
        this(context,null);
    }

    public TmecSeekBarMedia(Context context, AttributeSet attrs) {
        super(context, attrs);
        SkinManager.Singleton.getInstance().register(this);
        setContentView();
        init(attrs);
        updateSeekBarImage();
    }

    private void setContentView() {
        LayoutInflater.from(getContext()).inflate(R.layout.tmec_seek_bar_media,
                this, true);
        mMediaSeekBar = findViewById(R.id.sb_media);
        mStartValue = findViewById(R.id.tv_seekbar_start);
        mEndValue = findViewById(R.id.tv_seekbar_end);
    }

    private void init(AttributeSet attributeSet) {
        if (attributeSet != null) {
            TypedArray typedArray = null;
            try {
                typedArray = getContext().obtainStyledAttributes(attributeSet, R.styleable.TmecSeekBarMedia);
                setMax(typedArray.getInt(R.styleable.TmecSeekBarMedia_android_max, 100));
                setProgress(typedArray.getInt(R.styleable.TmecSeekBarMedia_android_progress, 0));
                setSeekBarStartValue(typedArray.getString(R.styleable.TmecSeekBarMedia_tmec_seekbar_start_value));
                setSeekBarEndValue(typedArray.getString(R.styleable.TmecSeekBarMedia_tmec_seekbar_end_value));
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
        if (mMediaSeekBar != null) {
            mMediaSeekBar.setMax(max);
        }
    }

    /**
     * get seekbar max length.
     *
     * @return length
     */
    public int getMax() {
        return mMediaSeekBar != null ? mMediaSeekBar.getMax() : 0;
    }

    /**
     * set seekbar current length.
     *
     * @param progress length
     */
    public void setProgress(int progress) {
        if (mMediaSeekBar != null) {
            mMediaSeekBar.setProgress(progress);
        }
    }

    /**
     * get seekbar current length.
     *
     * @return length
     */
    public int getProgress() {
        return mMediaSeekBar != null ? mMediaSeekBar.getProgress() : 0;
    }

    /**
     * set seekbar start value.
     *
     * @param value text
     */
    public void setSeekBarStartValue(CharSequence value) {
        if (mStartValue != null) {
            mStartValue.setText(value);
        }
    }

    /**
     * set seekbar end value.
     *
     * @param value text
     */
    public void setSeekBarEndValue(CharSequence value) {
        if (mEndValue != null) {
            mEndValue.setText(value);
        }
    }

    /**
     * set seekbar listener.
     *
     * @param listener listener
     */
    public void setOnSeekBarChangeListener(SeekBar.OnSeekBarChangeListener listener) {
        if (mMediaSeekBar != null && listener != null) {
            mMediaSeekBar.setOnSeekBarChangeListener(listener);
        }
    }

    private void updateSeekBarImage() {
        if (SkinManager.Singleton.getInstance().isNight()) {
            mMediaSeekBar.setThumb(getContext().getResources().getDrawable(R.drawable.tmec_seekbar_media_night_thumb));
            mMediaSeekBar.setProgressDrawable(getContext().getResources().getDrawable(R.drawable.seekbar_day_media_night_bg));
        } else {
            mMediaSeekBar.setThumb(getContext().getResources().getDrawable(R.drawable.tmec_seekbar_media_thumb));
            mMediaSeekBar.setProgressDrawable(getContext().getResources().getDrawable(R.drawable.seekbar_day_media_bg));
        }
    }

    @Override
    public void onSkinModeChange(SkinMode skinMode) {
        updateSeekBarImage();
    }
}
