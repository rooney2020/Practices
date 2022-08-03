package com.tmec.common.sdk.settingitem;

import android.content.Context;
import android.content.res.TypedArray;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.widget.CompoundButton;
import android.widget.RelativeLayout;

import androidx.annotation.Nullable;

import com.tmec.common.sdk.R;
import com.tmec.common.sdk.base.SkinManager;
import com.tmec.common.sdk.base.SkinMode;
import com.tmec.common.sdk.base.TmecImageView;
import com.tmec.common.sdk.switchbutton.TmecSwitch;
import com.tmec.common.sdk.textview.TmecTextExplainTitle;
import com.tmec.common.sdk.textview.TmecTextSmallTitle;

public class TmecListItemSetting extends RelativeLayout implements SkinManager.OnSkinModeChangeListener {

    private Context mContext;
    private TmecTextSmallTitle mTextView;
    private TmecTextExplainTitle mTvAnnotation;
    private TmecImageView mIvJump;
    private TmecSwitch mTsSwitch;
    private TmecTextSmallTitle mTvRight;
    private RelativeLayout mRlLayout;
    private TmecImageView mIvBottomImage;
    private boolean mSelectedStyle;

    public TmecListItemSetting(Context context) {
        this(context, null);
    }

    public TmecListItemSetting(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public TmecListItemSetting(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        this(context, attrs, defStyleAttr, 0);
    }

    /**
     * Construct the View.
     *
     * @param context      context
     * @param attrs        attrs
     * @param defStyleAttr defStyleAttr
     * @param defStyleRes  defStyleRes
     */
    public TmecListItemSetting(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        mContext = context;
        SkinManager.Singleton.getInstance().register(this);
        setContentView();
        init(attrs);
    }

    private void setContentView() {
        LayoutInflater.from(getContext()).inflate(R.layout.v_layout_list_item_setting,
                this, true);
        mTextView = findViewById(R.id.tv_setting_name);
        mTvAnnotation = findViewById(R.id.tv_setting_annotation);
        mIvJump = findViewById(R.id.iv_jump);
        mTsSwitch = findViewById(R.id.ts_switch);
        mTvRight = findViewById(R.id.tv_setting_right_name);
        mRlLayout = findViewById(R.id.rl_setting_layout);
        mIvBottomImage = findViewById(R.id.view_bottom_line);
    }

    /**
     * Init the base Info.
     *
     * @param attributeSet attributeSet
     */
    private void init(AttributeSet attributeSet) {
        if (attributeSet != null) {
            TypedArray ta = null;
            try {
                ta = getContext().obtainStyledAttributes(attributeSet, R.styleable.TsListItemSetting);
                setShowBottomLine(ta.getBoolean(R.styleable.TsListItemSetting_tmec_show_bottom_line,
                        false));
                setSwitchChecked(ta.getBoolean(R.styleable.TsListItemSetting_tmec_setting_switch_check,
                        false));
                setSwitchDisplay(ta.getBoolean(R.styleable.TsListItemSetting_tmec_setting_switch_display,
                        false));
                setSettingTextLeftValue(ta.getString(R.styleable.TsListItemSetting_tmec_text_left_value));
                setSettingAnnotationValue(ta.getString(R.styleable.TsListItemSetting_tmec_annotation_value));
                setSettingTextRightValue(ta.getString(R.styleable.TsListItemSetting_tmec_text_right_value));
            } finally {
                if (ta != null) {
                    ta.recycle();
                }
            }
        }
    }

    /**
     * Set annotation value.
     *
     * @param value text
     */
    public void setSettingAnnotationValue(CharSequence value) {
        mTvAnnotation.setText(value);
        setDiffHeight(!TextUtils.isEmpty(value));
        mTvAnnotation.setVisibility(!TextUtils.isEmpty(value) ? VISIBLE : GONE);
    }

    /**
     * Set left value.
     *
     * @param value text
     */
    public void setSettingTextLeftValue(CharSequence value) {
        mTextView.setText(value);
    }

    /**
     * Set right value.
     *
     * @param value text
     */
    public void setSettingTextRightValue(CharSequence value) {
        mTvRight.setText(value);
        mTvRight.setVisibility(!TextUtils.isEmpty(value) ? VISIBLE : GONE);
    }

    /**
     * Set switch status.
     *
     * @param haveSwitch status
     */
    public void setSwitchDisplay(boolean haveSwitch) {
        mSelectedStyle = haveSwitch;
        updateItemImage();
        mIvJump.setVisibility(haveSwitch ? GONE : VISIBLE);
        mTsSwitch.setVisibility(haveSwitch ? VISIBLE : GONE);
    }

    /**
     * Set switch checked status.
     *
     * @param checked status
     */
    public void setSwitchChecked(boolean checked) {
        mTsSwitch.setChecked(checked);
    }

    /**
     * Get switch checked status.
     *
     * @return checked
     */
    public boolean getSwitchChecked() {
        return mTsSwitch.isChecked();
    }

    /**
     * Set the switch change callback.
     *
     * @param onCheckedChangeListener callback
     */
    public void setSwitchChangeListener(CompoundButton.OnCheckedChangeListener
                                                onCheckedChangeListener) {
        if (onCheckedChangeListener != null) {
            mTsSwitch.setOnCheckedChangeListener(onCheckedChangeListener);
        }
    }

    /**
     * Set the Jump OnClick callback.
     *
     * @param onClickListener callback
     */
    public void setJumpOnClickListener(OnClickListener onClickListener) {
        if (onClickListener != null) {
            mIvJump.setOnClickListener(onClickListener);
        }
    }

    /**
     * Set show bottom line.
     *
     * @param showBottomLine status
     */
    public void setShowBottomLine(boolean showBottomLine) {
        mIvBottomImage.setVisibility(showBottomLine ? VISIBLE : GONE);
    }

    private void setDiffHeight(boolean diff) {
        if (diff) {
            RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(
                    mContext.getResources().getDimensionPixelSize(R.dimen.setting_item_default_width),
                    mContext.getResources().getDimensionPixelSize(R.dimen.setting_item_double2_height));
            mRlLayout.setLayoutParams(layoutParams);
        } else {
            RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(
                    mContext.getResources().getDimensionPixelSize(R.dimen.setting_item_default_width),
                    mContext.getResources().getDimensionPixelSize(R.dimen.setting_item_single_height));
            mRlLayout.setLayoutParams(layoutParams);
        }
    }

    private void updateItemImage() {
        Log.e("TsSkinConfigurationManager", "updateItemImage: ");
        if (!mSelectedStyle) {
            if (SkinManager.Singleton.getInstance().isNight()) {
                mRlLayout.setBackground(mContext.getResources().getDrawable(R.drawable.setting_bg_night));
            } else {
                mRlLayout.setBackground(mContext.getResources().getDrawable(R.drawable.setting_bg_light));
            }
        } else {
            mRlLayout.setBackground(null);
        }
    }

    @Override
    public void onSkinModeChange(SkinMode skinMode) {
        updateItemImage();
    }

}
