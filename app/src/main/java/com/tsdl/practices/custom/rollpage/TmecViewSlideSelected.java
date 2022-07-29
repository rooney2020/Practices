package com.tsdl.practices.custom.rollpage;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.util.Log;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.tsdl.practices.R;
import com.tsdl.practices.custom.base.ISkinChangedListener;
import com.tsdl.practices.custom.base.TmecSkinConfigurationManager;

import java.util.ArrayList;
import java.util.List;

public class TmecViewSlideSelected extends LinearLayout {
    private List<Circle> mCircleList = new ArrayList();
    private static final int CIRCLE_END = 10;
    private static final int SHOW_CIRCLE_NUMBER = 6;
    private static final int SHOW_CURRENT_CIRCLE_WIDTH = 22;
    private static final int SHOW_CIRCLE_WIDTH_AND_HEIGHT = 8;
    private int mRecordValue = 0;

    public TmecViewSlideSelected(Context context) {
        this(context,null);
    }

    public TmecViewSlideSelected(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public TmecViewSlideSelected(Context context, AttributeSet attrs, int defStyleAttr) {
        this(context, attrs, defStyleAttr,0);
    }

    public TmecViewSlideSelected(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        initAttributeSet(attrs);
    }

    private void initAttributeSet(AttributeSet attrs) {
        if (attrs != null) {
            TypedArray typedArray = null;
            try {
                typedArray = getContext().obtainStyledAttributes(attrs, R.styleable.TmecViewSlideSelected);
                showCircleNumber(typedArray.getInt(R.styleable.TmecViewSlideSelected_tmec_show_circle_number, SHOW_CIRCLE_NUMBER));
                showCurrentCircle(typedArray.getInt(R.styleable.TmecViewSlideSelected_tmec_show_current_circle, mRecordValue));
            } finally {
                if (typedArray != null) {
                    typedArray.recycle();
                }
            }
        }
    }

    /**
     * show circle number.
     *
     * @param count count
     */
    public void showCircleNumber(int count) {
        clearViewAndList();
        RadioGroup radioGroup = new RadioGroup(getContext());
        LayoutParams layoutParams = new LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT, SHOW_CIRCLE_WIDTH_AND_HEIGHT);
        radioGroup.setOrientation(HORIZONTAL);
        radioGroup.setLayoutParams(layoutParams);
        for (int i = 0; i < count; i++) {
            Circle circle = new Circle(getContext());
            LayoutParams params = new LayoutParams(
                    ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            if (i != 0) {
                params.leftMargin = CIRCLE_END;
            }
            circle.setLayoutParams(params);
            radioGroup.addView(circle);
            mCircleList.add(circle);
        }
        this.addView(radioGroup);
    }

    /**
     * show current circle.
     *
     * @param current current
     */
    public void showCurrentCircle(int current) {
        if (mCircleList.size() > current && current >= 0) {
            Circle currentCircle = mCircleList.get(current);
            currentCircle.setSelected(true);
            LayoutParams currentLayoutParams = (LayoutParams) currentCircle.getLayoutParams();
            currentLayoutParams.width = SHOW_CURRENT_CIRCLE_WIDTH;
            currentLayoutParams.height = SHOW_CIRCLE_WIDTH_AND_HEIGHT;
            if (current != 0) {
                currentLayoutParams.leftMargin = CIRCLE_END;
            }
            currentCircle.setLayoutParams(currentLayoutParams);
            Circle recordCircle = mCircleList.get(mRecordValue);
            if (mRecordValue != current && recordCircle.isSelected()) {
                LayoutParams recordLayoutParams = (LayoutParams) recordCircle.getLayoutParams();
                recordLayoutParams.width = SHOW_CIRCLE_WIDTH_AND_HEIGHT;
                recordLayoutParams.height = SHOW_CIRCLE_WIDTH_AND_HEIGHT;
                if (mRecordValue != 0) {
                    recordLayoutParams.leftMargin = CIRCLE_END;
                }
                recordCircle.setLayoutParams(recordLayoutParams);
                mCircleList.get(mRecordValue).setSelected(false);
            }
            mRecordValue = current;
        } else {
            mCircleList.get(mRecordValue).setSelected(true);
        }
    }

    /**
     * get current selected circle.
     *
     * @return selected
     */
    public int getCurrentSelected() {
        return mRecordValue;
    }

    private void clearViewAndList() {
        if (mCircleList.size() > 0) {
            mCircleList.clear();
            removeAllViews();
        }
    }

     static class Circle extends RadioButton implements ISkinChangedListener {

        public Circle(Context context) {
            this(context,null);
        }

        public Circle(Context context, AttributeSet attrs) {
            this(context, attrs,0);
        }

        public Circle(Context context, AttributeSet attrs, int defStyleAttr) {
            this(context, attrs, defStyleAttr,0);
        }

        public Circle(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
            super(context, attrs, defStyleAttr, defStyleRes);
            TmecSkinConfigurationManager.getInstance().register(this);
            setButtonDrawable(0);
            circleInit();
        }

        public void circleInit() {
            setSelected(false);
            updateCircleImage();
        }

        private void updateCircleImage() {
            if (TmecSkinConfigurationManager.getInstance().isNight()) {
                setBackground(getContext().getResources().getDrawable(R.drawable.tmec_circle_night_bg));
            } else {
                setBackground(getContext().getResources().getDrawable(R.drawable.tmec_circle_light_bg));
            }
        }

        @Override
        public void onSkinChanged() {
            updateCircleImage();
        }
    }
}
