package com.tmec.common.sdk.base;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.widget.ImageView;

import com.tmec.common.sdk.R;


public class TmecImageView extends ImageView implements SkinManager.OnSkinModeChangeListener {

    private int mImageSrc;
    private int mImageNightSrc;
    private int mImageBg;
    private int mImageNightBg;

    public TmecImageView(Context context) {
        this(context, null);
    }

    public TmecImageView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public TmecImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        this(context, attrs, defStyleAttr, 0);
    }

    /**
     * Construction method.
     *
     * @param context context
     * @param attrs attrs
     * @param defStyleAttr defStyleAttr
     * @param defStyleRes defStyleRes
     */
    public TmecImageView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        SkinManager.Singleton.getInstance().register(this);
        TypedArray array = getContext().obtainStyledAttributes(attrs, R.styleable.TmecImageView);
        mImageSrc = array.getResourceId(R.styleable.TmecImageView_android_src,
                0);
        mImageNightSrc = array.getResourceId(R.styleable.TmecImageView_tmec_image_night_src,
                0);
        mImageBg = array.getResourceId(R.styleable.TmecImageView_android_background,
                0);
        mImageNightBg = array.getResourceId(R.styleable.TmecImageView_tmec_image_night_bg,
                0);
        updateImage();
    }

    public void setImageSrc(int src) {
        mImageSrc = src;
        updateImage();
    }

    public void setImageNightSrc(int src) {
        mImageNightSrc = src;
        updateImage();
    }

    public void setImageBg(int bg) {
        mImageBg = bg;
        updateImage();
    }

    public void setImageNightBg(int bg) {
        mImageNightBg = bg;
        updateImage();
    }

    @Override
    public void onSkinModeChange(SkinMode skinMode) {
        updateImage();
    }

    public void updateImage() {
        if (SkinManager.Singleton.getInstance().isNight()) {
            if (mImageNightSrc != 0) {
                setImageResource(mImageNightSrc);
            } else if (mImageSrc != 0) {
                setImageResource(mImageSrc);
            }
            if (mImageNightBg != 0) {
                setBackgroundResource(mImageNightBg);
            } else if (mImageBg != 0) {
                setBackgroundResource(mImageBg);
            }
        } else {
            if (mImageSrc != 0) {
                setImageResource(mImageSrc);
            } else if (mImageNightSrc != 0) {
                setImageResource(mImageNightSrc);
            }
            if (mImageBg != 0) {
                setBackgroundResource(mImageBg);
            } else if (mImageNightBg != 0) {
                setBackgroundResource(mImageNightBg);
            }
        }
    }
}
