package com.tsdl.practices.custom.button;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.tsdl.practices.R;
import com.tsdl.practices.custom.base.ISkinChangedListener;
import com.tsdl.practices.custom.base.TmecImageView;
import com.tsdl.practices.custom.base.TmecSkinConfigurationManager;

public class TmecButtonHighlightIcon extends LinearLayout implements ISkinChangedListener {

    private TextView mTextView;
    private TmecImageView mIconImg;

    private static final int PADDING_HORIZONTAL = 30; //horizontal padding

    public TmecButtonHighlightIcon(Context context) {
        this(context, null);
    }

    public TmecButtonHighlightIcon(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public TmecButtonHighlightIcon(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        this(context, attrs, defStyleAttr, R.style.TmecButtonIcon);
    }

    public TmecButtonHighlightIcon(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        initChildren();
        init(attrs);
    }

    private void init(AttributeSet attrs) {
        TmecSkinConfigurationManager.getInstance().register(this);
        TypedArray array = null;
        try {
            array = getContext().obtainStyledAttributes(attrs, R.styleable.TmecButtonHighLightIcon);
            setIconImg(array.getResourceId(R.styleable.TmecButtonHighLightIcon_tmec_button_icon_highlight_img,
                    R.drawable.icon_refresh_night_normal));
            setText(array.getString(R.styleable.TmecButtonHighLightIcon_android_text));
            setEnabled(array.getBoolean(R.styleable.TmecButtonHighLightIcon_android_enabled, true));
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (array != null) {
                array.recycle();
            }
        }
        updateButton();
    }

    private void initChildren() {
        mTextView = new TextView(getContext());
        mTextView.setTextSize(getResources()
                .getDimensionPixelSize(R.dimen.tmec_button_icon_default_text_size));
        mTextView.setTextColor(getResources().getColor(R.color.tmec_btn_text_color));
        LayoutParams params = new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);
        params.leftMargin = getResources().getDimensionPixelSize(R.dimen.tmec_button_icon_text_margin_left);
        LayoutParams iconParam = new LayoutParams(getResources()
                .getDimensionPixelSize(R.dimen.tmec_button_icon_img_width),
                getResources().getDimensionPixelSize(R.dimen.tmec_buttom_icon_img_height));
        mIconImg = new TmecImageView(getContext());
        addView(mIconImg, iconParam);
        addView(mTextView, params);
    }

    /**
     * set text.
     *
     * @param text text
     */
    public void setText(CharSequence text) {
        if (mTextView != null) {
            mTextView.setText(text);
        }
    }

    /**
     * set icon image when the theme is light mode.
     *
     * @param drawableId drawableId
     */
    public void setIconImg(int drawableId) {
        if (drawableId != 0) {
            mIconImg.setImageBg(drawableId);
            mIconImg.setImageNightBg(drawableId);
        }
    }

    /**
     * set button enable.
     *
     * @param enabled enabled
     */
    @Override
    public void setEnabled(boolean enabled) {
        super.setEnabled(enabled);
        mTextView.setEnabled(enabled);
        mIconImg.setEnabled(enabled);
    }

    @Override
    public void onSkinChanged() {
        updateButton();
    }

    public void updateButton() {
        if (TmecSkinConfigurationManager.getInstance().isNight()) {
            setBackgroundResource(R.drawable.slt_btn_icon_solid_night_bg);
        } else {
            setBackgroundResource(R.drawable.slt_btn_icon_solid_bg);
        }
        setPadding(PADDING_HORIZONTAL, 0, PADDING_HORIZONTAL, 0);
    }
}
