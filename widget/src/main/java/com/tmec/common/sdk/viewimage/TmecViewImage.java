package com.tmec.common.sdk.viewimage;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.tmec.common.sdk.R;
import com.tmec.common.sdk.base.SkinManager;
import com.tmec.common.sdk.base.SkinMode;
import com.tmec.common.sdk.base.TmecImageView;

public class TmecViewImage extends RelativeLayout implements SkinManager.OnSkinModeChangeListener {

    private RelativeLayout mRootView;
    private TextView mTvTitle;
    private TextView mTvMinor;
    private TmecImageView mSquareImg;
    private TmecImageView mIconImg;

    public TmecViewImage(Context context) {
        this(context, null);
    }

    public TmecViewImage(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public TmecViewImage(Context context, AttributeSet attrs, int defStyleAttr) {
        this(context, attrs, defStyleAttr, 0);
    }

    public TmecViewImage(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        initView(context);
        initAttrs(attrs);
    }

    private void initView(Context context) {
        mRootView = (RelativeLayout) LayoutInflater.from(context)
                .inflate(R.layout.tmec_layout_viewimage, this, true);
        mTvTitle = mRootView.findViewById(R.id.tv_title);
        mTvMinor = mRootView.findViewById(R.id.tv_minor_title);
        mSquareImg = mRootView.findViewById(R.id.iv_square);
        mIconImg = mRootView.findViewById(R.id.iv_icon);
    }

    private void initAttrs(AttributeSet attrs) {
        SkinManager.Singleton.getInstance().register(this);
        TypedArray array = null;
        try {
            array = getContext().obtainStyledAttributes(attrs, R.styleable.TmecViewImage);
            mTvTitle.setText(array.getString(R.styleable.TmecViewImage_vi_title));
            mTvMinor.setText(array.getString(R.styleable.TmecViewImage_vi_minor_title));
            mSquareImg.setImageBg(array.getResourceId(R.styleable.TmecViewImage_iv_square_drawable,
                    R.drawable.card_square));
            mSquareImg.setImageNightBg(array.getResourceId(R.styleable.TmecViewImage_iv_square_night_drawable,
                    R.drawable.card_night_square));
            mIconImg.setImageBg(array.getResourceId(R.styleable.TmecViewImage_iv_icon_drawable,
                    R.drawable.icon_card_authorization));
            mIconImg.setImageNightBg(array.getResourceId(R.styleable.TmecViewImage_iv_icon_night_drawable,
                    R.drawable.icon_card_night_authorization));
        } catch (Exception exception) {
            exception.printStackTrace();
        } finally {
            if (array != null) {
                array.recycle();
            }
        }
        updateBg();
    }

    private void updateBg() {
        if (SkinManager.Singleton.getInstance().isNight()) {
            mRootView.setBackgroundResource(R.drawable.card_night_rectangle);
        } else {
            mRootView.setBackgroundResource(R.drawable.card_rectangle);
        }
    }

    /**
     * set titleText.
     *
     * @param titleText titleText
     */
    public void setTitleText(String titleText) {
        if (titleText != null) {
            mTvTitle.setText(titleText);
        }
    }

    /**
     * set minorText.
     *
     * @param minorText minorText
     */
    public void setMinorText(String minorText) {
        if (minorText != null) {
            mTvMinor.setText(minorText);
        }
    }

    /**
     * set square drawable when the theme is night mode
     *
     * @param drawableId drawableId
     */
    public void setNightSquareImg(int drawableId) {
        if (drawableId != 0) {
            mSquareImg.setImageNightBg(drawableId);
        }
    }

    /**
     * set square drawable when the theme is light mode
     *
     * @param drawableId drawableId
     */
    public void setSquareImg(int drawableId) {
        if (drawableId != 0) {
            mSquareImg.setImageBg(drawableId);
        }
    }

    /**
     * set icon drawable when the theme is night mode
     *
     * @param drawableId drawableId
     */
    public void setNightIconImg(int drawableId) {
        if (drawableId != 0) {
            mIconImg.setImageNightBg(drawableId);
        }
    }

    /**
     * set icon drawable when the theme is light mode
     *
     * @param drawableId drawableId
     */
    public void setIconImg(int drawableId) {
        if (drawableId != 0) {
            mIconImg.setImageBg(drawableId);
        }
    }

    @Override
    public void onSkinModeChange(SkinMode skinMode) {
        updateBg();
    }
}
