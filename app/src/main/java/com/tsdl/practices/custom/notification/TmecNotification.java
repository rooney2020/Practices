package com.tsdl.practices.custom.notification;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.support.constraint.ConstraintLayout;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.tsdl.practices.R;
import com.tsdl.practices.custom.base.ISkinChangedListener;
import com.tsdl.practices.custom.base.TmecImageView;
import com.tsdl.practices.custom.base.TmecSkinConfigurationManager;
import com.tsdl.practices.custom.button.TmecButtonText;
import com.tsdl.practices.custom.textview.TmecTextSmallBody;
import com.tsdl.practices.custom.textview.TmecTextTitle;

public class TmecNotification extends ConstraintLayout implements ISkinChangedListener {

    public static final int NOTIFICATION_IMAGE = 0;
    public static final int NOTIFICATION_THREE = 2;
    public static final int LAYOUT_MIN_HEIGHT = 624;
    public static final int MAX_LINES_2 = 2;

    private int mMode;
    private int mLightBg;
    private int mNightBg;
    private int mLightImage;
    private int mNightImage;
    private View mRootView;
    private View mLayoutTop;
    private View mLayoutBottom;
    private TmecTextTitle mTvTopTitle;
    private TmecTextSmallBody mTvTopContent;
    private TmecTextTitle mTvBottomTitle;
    private TmecButtonText mBtnTextOne;
    private TmecButtonText mBtnTextTwo;
    private TmecButtonText mBtnTextThree;
    private TmecImageView mIvIcon;
    private TmecImageView mIvTopImage;

    public TmecNotification(Context context) {
        this(context, null);
    }

    public TmecNotification(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public TmecNotification(Context context, AttributeSet attrs, int defStyleAttr) {
        this(context, attrs, defStyleAttr, 0);
    }

    /**
     * Construction method.
     *
     * @param context      context
     * @param attrs        attrs
     * @param defStyleAttr defStyleAttr
     * @param defStyleRes  defStyleRes
     */
    public TmecNotification(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init();
        initData(attrs);
    }

    public void init() {
        mRootView = LayoutInflater.from(getContext()).inflate(R.layout.tmec_notification, this, true);
        TmecSkinConfigurationManager.getInstance().register(this);

        mLayoutTop = mRootView.findViewById(R.id.layout_top);
        mLayoutBottom = mRootView.findViewById(R.id.layout_bottom);
        mTvTopTitle = mRootView.findViewById(R.id.tv_top_title);
        mTvBottomTitle = mRootView.findViewById(R.id.tv_bottom_title);
        mTvTopContent = mRootView.findViewById(R.id.tv_top_content);
        mBtnTextOne = mRootView.findViewById(R.id.tv_bottom_content_one);
        mBtnTextTwo = mRootView.findViewById(R.id.tv_bottom_content_two);
        mBtnTextThree = mRootView.findViewById(R.id.tv_bottom_content_three);
        mIvIcon = mRootView.findViewById(R.id.iv_icon);
        mIvTopImage = mRootView.findViewById(R.id.iv_top_img);
        mBtnTextTwo = mRootView.findViewById(R.id.tv_bottom_content_two);
    }

    private void initData(AttributeSet attrs) {
        mLightBg = R.drawable.notification_light_bg;
        mNightBg = R.drawable.notification_night_bg;
        TypedArray array = null;
        try {
            array = getContext().obtainStyledAttributes(attrs, R.styleable.TmecNotification);
            mMode = array.getInteger(R.styleable.TmecNotification_tmec_notification_mode,
                    NOTIFICATION_IMAGE);
            setIcon(array.getResourceId(R.styleable.TmecNotification_tmec_icon, 0));
            setIconNight(array.getResourceId(R.styleable.TmecNotification_tmec_icon_night, 0));
            setImage(array.getResourceId(R.styleable.TmecNotification_tmec_image, 0));
            setImageNight(array.getResourceId(R.styleable.TmecNotification_tmec_image_night, 0));
            setTopTitleText(array.getString(R.styleable.TmecNotification_tmec_top_title));
            setTopContentText(array.getString(R.styleable.TmecNotification_tmec_top_content));
            setBottomTitleText(array.getString(R.styleable.TmecNotification_tmec_bottom_title));
            setBottomContentOneText(array.getString(R.styleable.TmecNotification_tmec_bottom_content_one));
            setBottomContentTwoText(array.getString(R.styleable.TmecNotification_tmec_bottom_content_two));
            setBottomContentThreeText(array.getString(R.styleable.TmecNotification_tmec_bottom_content_three));

            updateNotificationView();
            updateBg();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (array != null) {
                array.recycle();
            }
        }
    }

    private void updateNotificationView() {
        switch (mMode) {
            case NOTIFICATION_IMAGE:
                ViewGroup.LayoutParams layoutParams = mLayoutTop.getLayoutParams();
                layoutParams.height = LAYOUT_MIN_HEIGHT;
                mLayoutTop.setMinimumHeight(LAYOUT_MIN_HEIGHT);
                mLayoutBottom.setVisibility(View.GONE);
                mTvTopContent.setMaxLines(MAX_LINES_2);
                mIvTopImage.setVisibility(View.VISIBLE);
                break;
            case NOTIFICATION_THREE:
                mBtnTextTwo.setVisibility(View.VISIBLE);
                break;
            default:
                break;
        }
    }

    public void setIcon(int resId) {
        mIvIcon.setImageSrc(resId);
    }

    public void setIconNight(int resId) {
        mIvIcon.setImageNightSrc(resId);
    }

    public void setImage(int resId) {
        mLightImage = resId;
        mIvTopImage.setImageSrc(resId);
    }

    public void setImageNight(int resId) {
        mNightImage = resId;
        mIvTopImage.setImageNightSrc(resId);
    }

    public void setTopTitleText(String text) {
        mTvTopTitle.setText(text);
    }

    public void setTopContentText(String text) {
        mTvTopContent.setText(text);
    }

    public void setBottomTitleText(String text) {
        mTvBottomTitle.setText(text);
    }

    public void setBottomContentOneText(String text) {
        mBtnTextOne.setText(text);
    }

    public void setBottomContentTwoText(String text) {
        mBtnTextTwo.setText(text);
    }

    public void setBottomContentThreeText(String text) {
        mBtnTextThree.setText(text);
    }

    public void setBottomContentOneClickListener(OnClickListener listener) {
        mBtnTextOne.setOnClickListener(listener);
    }

    public void setBottomContentTwoClickListener(OnClickListener listener) {
        mBtnTextTwo.setOnClickListener(listener);
    }

    public void setBottomContentThreeClickListener(OnClickListener listener) {
        mBtnTextThree.setOnClickListener(listener);
    }

    private void updateBg() {
        if (TmecSkinConfigurationManager.getInstance().isNight()) {
            mRootView.setBackgroundResource(mNightBg);
            if (NOTIFICATION_IMAGE != mMode) {
                mLayoutTop.setBackgroundResource(mNightImage);
            }
        } else {
            mRootView.setBackgroundResource(mLightBg);
            if (NOTIFICATION_IMAGE != mMode) {
                mLayoutTop.setBackgroundResource(mLightImage);
            }
        }
    }

    @Override
    public void onSkinChanged() {
        updateBg();
    }
}
