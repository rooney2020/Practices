package com.tmec.common.sdk.grouphead;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import androidx.annotation.DrawableRes;

import com.tmec.common.sdk.R;
import com.tmec.common.sdk.base.SkinManager;
import com.tmec.common.sdk.base.SkinMode;

import java.util.ArrayList;
import java.util.List;

public class TmecViewGroupHeader extends LinearLayout implements SkinManager.OnSkinModeChangeListener {

    public static final int DEFAULT_TITLE_SIZE = 28;
    public static final int LAYOUT_HEIGHT_LIGHT = 58;
    public static final int LAYOUT_HEIGHT_NIGHT = 68;

    private RadioGroup mRadioGroup;
    private RadioGroup.LayoutParams mLayoutParams;
    private final List<RadioButton> mRadioButtons = new ArrayList<>();
    private int mItemCount = 0;

    private int mTitleNightColor;
    private int mTitleLightColor;
    private int mSelectedNightColor;
    private int mSelectedLightColor;
    private int mGroupNightColor;
    private int mGroupLightColor;

    private int mTitleSize = DEFAULT_TITLE_SIZE;

    public TmecViewGroupHeader(Context context) {
        super(context);
        init(null);
    }

    public TmecViewGroupHeader(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(attrs);
    }

    public TmecViewGroupHeader(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(attrs);
    }

    private void init(AttributeSet attrs) {
        LayoutInflater.from(getContext()).inflate(R.layout.ts_group_header, this, true);
        SkinManager.Singleton.getInstance().register(this);
        mRadioGroup = findViewById(R.id.radio_group);
        mLayoutParams = new RadioGroup.LayoutParams(mRadioGroup.getLayoutParams());

        mTitleLightColor = getContext().getColor(R.color.group_header_text_checked_color_light);
        mTitleNightColor = getContext().getColor(R.color.group_header_text_checked_color_night);
        mSelectedLightColor = R.drawable.slt_radio_button_light;
        mSelectedNightColor = R.drawable.slt_radio_button_night;
        mGroupNightColor = R.drawable.group_bg_night;
        mGroupLightColor = R.drawable.group_bg_light;

        TypedArray array = null;
        try {
            array = getContext().obtainStyledAttributes(attrs, R.styleable.TmecViewGroupHeader);
            setItemCount(array.getInt(R.styleable.TmecViewGroupHeader_tmec_item_count,
                    getContext().getResources().getInteger(R.integer.tmec_default_item_count)));
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (array != null) {
                array.recycle();
            }
        }
    }

    /**
     * set item count.
     *
     * @param count count
     */
    public void setItemCount(int count) {
        mItemCount = count;
        mRadioGroup.setWeightSum(Float.parseFloat(String.valueOf(count)));
        mRadioGroup.setLayoutParams(mLayoutParams);
        mRadioGroup.setGravity(Gravity.CENTER_VERTICAL);
        mRadioGroup.removeAllViews();
        mRadioButtons.clear();
        int layoutHeight = SkinManager.Singleton.getInstance().isNight()
                ? LAYOUT_HEIGHT_NIGHT : LAYOUT_HEIGHT_LIGHT;
        RadioGroup.LayoutParams params = new RadioGroup.LayoutParams(0, layoutHeight, 1.0f);
        for (int i = 0; i < count; i++) {
            RadioButton radioButton = new RadioButton(getContext());
            radioButton.setGravity(Gravity.CENTER);
            radioButton.setId(i);
            radioButton.setButtonDrawable(android.R.color.transparent);
            radioButton.setLayoutParams(params);
            radioButton.setMaxLines(1);
            mRadioButtons.add(radioButton);
            mRadioGroup.addView(radioButton);
        }
        setTitleSize(mTitleSize);
        updateColor();
    }

    public void setGroupBackGround(@DrawableRes int resource) {
        mRadioGroup.setBackgroundResource(resource);
    }

    /**
     * set checkedListener.
     *
     * @param listener listener
     */
    public void setItemCheckedListener(RadioGroup.OnCheckedChangeListener listener) {
        if (listener != null) {
            mRadioGroup.setOnCheckedChangeListener(listener);
        }
    }

    /**
     * set item background.
     *
     * @param resource resource
     */

    public void setItemBackgroundLight(@DrawableRes int resource) {
        mGroupLightColor = resource;
        updateColor();
    }

    /**
     * set item background.
     *
     * @param resource resource
     */

    public void setItemBackgroundNight(@DrawableRes int resource) {
        mGroupNightColor = resource;
        updateColor();
    }

    /**
     * set item enabled.
     *
     * @param enabled enabled
     */
    public void setItemsEnabled(boolean enabled) {
        for (int i = 0; i < mItemCount; i++) {
            mRadioButtons.get(i).setEnabled(enabled);
        }
    }

    /**
     * set title text.
     *
     * @param titles titles
     */
    public void setTitles(String[] titles) {
        for (int i = 0; i < mItemCount; i++) {
            if (i == titles.length) {
                return;
            }
            mRadioButtons.get(i).setText(titles[i]);
        }
    }

    /**
     * set checked.
     *
     * @param position position
     */
    public void setChecked(int position) {
        if (position < mItemCount && position >= 0) {
            mRadioButtons.get(position).setChecked(true);
        }
    }

    /**
     * set title text color.
     *
     * @param color Title Color
     */
    public void setTitleLightColor(int color) {
        mTitleLightColor = color;
        updateColor();
    }

    /**
     * set title text color.
     *
     * @param color Title Color
     */
    public void setTitleNightColor(int color) {
        mTitleNightColor = color;
        updateColor();
    }

    /**
     * set title text size.
     *
     * @param size Title Size
     */
    public void setTitleSize(int size) {
        mTitleSize = size;
        for (int i = 0; i < mItemCount; i++) {
            mRadioButtons.get(i).setTextSize(size);
        }
    }

    public void updateColor() {
        for (int i = 0; i < mItemCount; i++) {
            if (SkinManager.Singleton.getInstance().isNight()) {
                mRadioButtons.get(i).setTextColor(mTitleNightColor);
                mRadioButtons.get(i).setBackgroundResource(mSelectedNightColor);
                mRadioGroup.setBackgroundResource(mGroupNightColor);
            } else {
                mRadioButtons.get(i).setTextColor(mTitleLightColor);
                mRadioButtons.get(i).setBackgroundResource(mSelectedLightColor);
                mRadioGroup.setBackgroundResource(mGroupLightColor);
            }
        }
    }

    private void updateHeight() {
        int layoutHeight = SkinManager.Singleton.getInstance().isNight()
                ? LAYOUT_HEIGHT_NIGHT : LAYOUT_HEIGHT_LIGHT;
        RadioGroup.LayoutParams params = new RadioGroup.LayoutParams(0, layoutHeight, 1.0f);
        for (int i = 0; i < mItemCount; i++) {
            mRadioButtons.get(i).setLayoutParams(params);
        }
    }

    @Override
    public void onSkinModeChange(SkinMode skinMode) {
        updateColor();
        updateHeight();
    }
}
