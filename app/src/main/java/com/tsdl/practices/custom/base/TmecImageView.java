package com.tsdl.practices.custom.base;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.widget.ImageView;

import com.tsdl.practices.R;


public class TmecImageView extends ImageView implements ISkinChangedListener {

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
        TmecSkinConfigurationManager.getInstance().register(this);
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
    public void onSkinChanged() {
        updateImage();
    }

    public void updateImage() {
        if (TmecSkinConfigurationManager.getInstance().isNight()) {
            if (mImageNightSrc != 0) {
                setImageResource(mImageNightSrc);
            }
            if (mImageNightBg != 0) {
                setBackgroundResource(mImageNightBg);
            }
        } else {
            if (mImageSrc != 0) {
                setImageResource(mImageSrc);
            }
            if (mImageBg != 0) {
                setBackgroundResource(mImageBg);
            }
        }
    }
}
