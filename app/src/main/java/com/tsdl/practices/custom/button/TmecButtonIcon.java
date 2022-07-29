package com.tsdl.practices.custom.button;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.tsdl.practices.R;
import com.tsdl.practices.custom.base.ISkinChangedListener;
import com.tsdl.practices.custom.base.TmecImageView;
import com.tsdl.practices.custom.base.TmecSkinConfigurationManager;

public class TmecButtonIcon extends LinearLayout implements ISkinChangedListener {

    private TextView mTextView;
    private TmecImageView mIconImg;
    private String mText;
    private int mIconId;
    private int mNightIconId;
    private boolean mEnabled;

    private static final int PADDING_HORIZONTAL = 30; //horizontal padding

    public TmecButtonIcon(Context context) {
        this(context, null);
    }

    public TmecButtonIcon(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public TmecButtonIcon(Context context, AttributeSet attrs, int defStyleAttr) {
        this(context, attrs, defStyleAttr, R.style.TmecButtonIcon);
    }

    /**
     * Construction method.
     *
     * @param context      context
     * @param attrs        attrs
     * @param defStyleAttr defStyleAttr
     * @param defStyleRes  defStyleRes
     */
    public TmecButtonIcon(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        initChildren();
        init(attrs);
    }

    private void initChildren() {
        mTextView = new TextView(getContext());
        mTextView.setTextSize(getResources()
                .getDimensionPixelSize(R.dimen.tmec_button_icon_default_text_size));
        LayoutParams params = new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);
        params.leftMargin = getResources().getDimensionPixelSize(R.dimen.tmec_button_icon_text_margin_left);
        LayoutParams iconParam = new LayoutParams(getResources()
                .getDimensionPixelSize(R.dimen.tmec_button_icon_img_width),
                getResources().getDimensionPixelSize(R.dimen.tmec_buttom_icon_img_height));
        mIconImg = new TmecImageView(getContext());
        mIconImg.setLayoutParams(params);
        addView(mIconImg, iconParam);
        addView(mTextView, params);
    }

    private void init(AttributeSet attrs) {
        TmecSkinConfigurationManager.getInstance().register(this);
        TypedArray array = null;
        try {
            array = getContext().obtainStyledAttributes(attrs, R.styleable.TmecButtonIcon);
            mIconId = array.getResourceId(R.styleable.TmecButtonIcon_tmec_button_icon_img,
                    R.drawable.slt_btn_icon_img);
            mNightIconId = array.getResourceId(R.styleable.TmecButtonIcon_tmec_button_icon_night_img,
                    R.drawable.slt_btn_icon_img_night);
            mText = array.getString(R.styleable.TmecButtonIcon_android_text);
            mEnabled = array.getBoolean(R.styleable.TmecButtonIcon_android_enabled, true);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (array != null) {
                array.recycle();
            }
        }
        mTextView.setText(mText);
        mIconImg.setImageBg(mIconId);
        mIconImg.setImageNightBg(mNightIconId);
        setEnabled(mEnabled);
        updateButton();
    }

    /**
     * set text.
     *
     * @param text text
     */
    public void setText(CharSequence text) {
        mText = (String) text;
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
            mIconId = drawableId;
            mIconImg.setImageBg(drawableId);
        }
    }

    /**
     * set icon image when the theme is night mode.
     *
     * @param drawableId drawableId
     */
    public void setIconNightImg(int drawableId) {
        if (drawableId != 0) {
            mNightIconId = drawableId;
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

    private void updateButton() {
        if (TmecSkinConfigurationManager.getInstance().isNight()) {
            setBackgroundResource(R.drawable.slt_btn_icon_bg_night);
            mTextView.setTextColor(getResources().getColorStateList(R.color.slt_btn_normal_night));
        } else {
            setBackgroundResource(R.drawable.slt_btn_icon_bg);
            mTextView.setTextColor(getResources().getColorStateList(R.color.slt_btn_icon));
        }
        setPadding(PADDING_HORIZONTAL, 0, PADDING_HORIZONTAL, 0);
    }
}
