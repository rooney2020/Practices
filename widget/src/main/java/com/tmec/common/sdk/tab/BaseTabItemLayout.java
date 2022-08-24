package com.tmec.common.sdk.tab;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Configuration;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PaintFlagsDrawFilter;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Handler;
import android.os.Message;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.util.SparseIntArray;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.view.ViewConfiguration;
import android.widget.Scroller;

import androidx.annotation.IntDef;
import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.core.content.ContextCompat;
import androidx.interpolator.view.animation.FastOutSlowInInterpolator;

import com.tmec.common.sdk.R;
import com.tmec.common.sdk.base.BaseView;
import com.tmec.common.sdk.util.AttributeHelper;
import com.tsdl.common.util.LogUtils;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.ArrayList;
import java.util.List;

public class BaseTabItemLayout extends BaseView implements ITabItem {

    public static final String TAG = "HHTabItemLayout";
    public static final String TEXT_ELLIPSIS = "...";
    /**
     * segment item radius.
     */
    private static final int DEFAULT_RADIUS = 18;

    /**
     * default the whole background's color.
     */
    private static final int DEFAULT_OUTER_COLOR = R.color.hryt_cbox_mark_unchecked_color;

    /**
     * default the item background's color.
     */
    private static final int DEFAULT_ITEM_COLOR = R.color.segmented_item_text_color;

    /**
     * default the whole text color.
     */
    private static final int DEFAULT_TEXT_COLOR = R.color.hryt_assist_content_color;

    /**
     * default the disenable whole text color.
     */
    private static final int DEFAULT_DISABLE_TEXT_COLOR = R.color.hryt_segmented_default_text_color;

    /**
     * default the item text color.
     */
    private static final int DEFAULT_SELECTED_TEXT_COLOR = R.color.hryt_main_content_color;

    /**
     * default the item text size.
     */
    private static final int DEFAULT_SELECTED_TEXT_SIZE = R.dimen.hryt_segmented_selected_text_dimen;

    /**
     * default the whole text size.
     */
    private static final int DEFAULT_TEXT_SIZE = R.dimen.hryt_segmented_normal_text_dimen;

    /**
     * the animation time.
     */
    private static final int ANIMATION_DURATION = 100;

    /**
     * mode is Round.
     */
    private static final int ROUND = 0;

    /**
     * mode is Circle.
     */
    private static final int CIRCLE = 1;

    /**
     * orientation is horizontal.
     */
    private static final int HORIZONTAL = 0;

    /**
     * orientation is vertical.
     */
    private static final int VERTICAL = 1;

    public static final float DISENABLE_ALPHA = 0.36f;
    public static final float ENABLE_ALPHA = 1.0f;

    /**
     * select item text is bold
     */
    private boolean mSelectBold = true;

    /**
     * item text is bold
     */
    private boolean mItemBold = false;

    /**
     * padding Start
     */
    private int mPaddingStart;

    /**
     * padding Side
     */
    private int mPaddingSide;

    private int mHalfValue = 2;

    /**
     * the view is round or circle.
     */
    @IntDef({ROUND, CIRCLE})
    @Retention(RetentionPolicy.SOURCE)
    public @interface Mode {
    }

    /**
     * Item's select size.
     */
    private float mSelectTextSize;

    /**
     * if view item is string.
     */
    private CharSequence[] mAddItems;

    /**
     * The radius.
     */
    private int mRadius;

    /**
     * this is item radius
     */
    private int mItemRadius;

    /**
     * The whole background's color.
     */
    private int mOuterColor;

    /**
     * Item outer left/right or top/bottommargin.
     */
    private int mItemMargin;

    /**
     * Item shadow margin.
     */
    private int mItemShadowMargin;

    /**
     * The item background's color.
     */
    private int mItemColor;

    /**
     * Item's size.
     */
    private float mTextSize;

    /**
     * The unselected font color.
     */
    private int mTextColor;

    /**
     * this disable color
     */
    private int mDisableTextColor;

    /**
     * The selected font color.
     */
    private int mSelectedTextColor;

    /**
     * Selected item position.
     */
    private int mSelectedItem = 0;

    /**
     * The mode(Circle or Round).
     */
    private int mMode = ROUND;

    /**
     * this view width
     */
    private int mWidth;

    /**
     * The orientation(horizontal or vertical).
     */
    private int mOrientation = HORIZONTAL;

    /**
     * the view is can click.
     */
    private boolean mEnabled = true;

    /**
     * the view is can scroll.
     */
    private boolean mScrollEnable = false;
    /**
     * if this is not null show drawable background
     */
    private Drawable mBgDrawable;

    /**
     * if this is not null show drawable background
     */
    private Drawable mItemDrawable;

    /**
     * is show default bg and item bitmap
     */
    private boolean mDefaultBitmap;

    /**
     * if this is not null show checked drawable color
     */
    private Drawable checkDrawable;
    private Drawable redPointDrawable;

    /**
     * tab layout child type
     */
    private boolean mIsChild;

    private int mTextAndImagePadding;

    /**
     * draw divide
     */
    private boolean mDrawDivide = true;

    private boolean mIsOnClickBeforeAnimation = true;

    private int mStart;
    private int mEnd;
    private int mHeight;
    private int mItemWidth;
    private int mMaximumFlingVelocity;
    private float mTouchPosition = 0;
    private int movePosition = -1;
    private RectF mRectF;
    private Paint mPaint;
    private Paint mTextPaint;
    private Scroller mScroller;
    private VelocityTracker mVelocityTracker;
    private List<BaseTabItem> mSegmentedControlItems = new ArrayList<>();
    private OnSegItemClickListener mOnSegItemClickListener;
    private OnSegItemClickFromUserListener mOnSegFromUserItemClickListener;
    private static final int USER_TYPE = 1;
    private static final int NOT_FROM_USER = 2;
    private Handler mHandler;
    private ColorStateList mBgDrawableTintList;
    private ColorStateList mBgDrawableTopTintList;
    private ColorStateList mTopCheckDrawableTintList;
    private ColorStateList mCheckDrawableTintList;
    private SparseIntArray mTargetResourceValues;
    private int checkToLeftOrRight = 5;
    private int checkDrawableToTop = 25;
    private int verticalCheckWidth = 48;
    private int drawLineHeight = 5;
    private int fixTopCheckPosition = 6;
    private int fixLinePosition = 32;
    private int alignLeft = 48;
    private int alpha = 186;
    private int mItemType = TEXT;
    public static final int TEXT = 0;
    public static final int ICON_TEXT = 1;
    private int changItemWidth;
    private int redPointLeft = 72;
    private int mRedPointPosition;
    private boolean mRedPointShow;
    private List<Integer> mPositionList = new ArrayList<>();

    /**
     * HHSegmentedControlView
     *
     * @param context context
     */
    public BaseTabItemLayout(Context context) {
        this(context, null);
    }

    /**
     * HHSegmentedControlView
     *
     * @param context context
     * @param attrs   attrs
     */
    public BaseTabItemLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    /**
     * HHSegmentedControlView
     *
     * @param context      context
     * @param attrs        attrs
     * @param defStyleAttr style
     */
    public BaseTabItemLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        this(context, attrs, defStyleAttr, R.style.DefaultTabItem);
    }

    public BaseTabItemLayout(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        mTargetResourceValues = AttributeHelper.readAttributeValues(context, attrs,
                R.styleable.HHTabItemLayout, defStyleAttr, defStyleRes);
        init(context, attrs);
        if (mHrytSoundEffect == BaseSoundUtils.SOUND_DEFAULT) {
            mHrytSoundEffect = SoundEffectEnum.FX_KEY_NORMAL_SEGMENT.value();
        }
        if (!isSoundEffectsEnabled()) {
            setSoundEffectsEnabled(true);
        }
    }

    private void init(Context context, AttributeSet attrs) {

        if (isInEditMode()) {
            return;
        }

        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.HHTabItemLayout);
        if (ta == null) {
            return;
        }

        mHandler = new H();
        mIsOnClickBeforeAnimation = ta.getBoolean(R.styleable.HHTabItemLayout_hryt_tabItemOnClickBeforeAnimation,
                true);
        mRadius = ta.getDimensionPixelSize(R.styleable.HHTabItemLayout_hryt_tabItemRadius,
                dip2px(getContext(), DEFAULT_RADIUS));

        mOuterColor = ta.getColor(R.styleable.HHTabItemLayout_hryt_tabItemOuterColor,
                ContextCompat.getColor(getContext(), DEFAULT_OUTER_COLOR));

        mItemColor = ta.getColor(R.styleable.HHTabItemLayout_hryt_tabItemItemColor,
                ContextCompat.getColor(getContext(), DEFAULT_ITEM_COLOR));

        mDisableTextColor = ta.getColor(R.styleable.HHTabItemLayout_hryt_tabItemDisableTextColor,
                ContextCompat.getColor(getContext(), DEFAULT_DISABLE_TEXT_COLOR));

        mSelectedTextColor = ta.getColor(R.styleable.HHTabItemLayout_hryt_tabItemSelectedTextColor,
                ContextCompat.getColor(getContext(), DEFAULT_SELECTED_TEXT_COLOR));

        mSelectedItem = ta.getInteger(R.styleable.HHTabItemLayout_hryt_tabItemSelectedItem,
                0);

        mAddItems = ta.getTextArray(R.styleable.HHTabItemLayout_hryt_tabItemAddItems);

        mEnabled = ta.getBoolean(R.styleable.HHTabItemLayout_hryt_tabItemEnabled, true);

        mTextSize = ta.getDimensionPixelSize(R.styleable.HHTabItemLayout_hryt_tabItemTextSize,
                getResources().getDimensionPixelSize(DEFAULT_TEXT_SIZE));

        mTextColor = ta.getColor(R.styleable.HHTabItemLayout_hryt_tabItemTextColor,
                ContextCompat.getColor(getContext(), DEFAULT_TEXT_COLOR));

        mSelectTextSize =
                ta.getDimensionPixelSize(R.styleable.HHTabItemLayout_hryt_tabItemSelectTextSize,
                        getResources().getDimensionPixelSize(DEFAULT_SELECTED_TEXT_SIZE));

        mMode = ta.getInt(R.styleable.HHTabItemLayout_hryt_tabItemMode, ROUND);

        mOrientation = ta.getInt(R.styleable.HHTabItemLayout_hryt_tabItemOrientation, HORIZONTAL);
        mRedPointPosition = ta.getInteger(R.styleable.HHTabItemLayout_hryt_tabItemRedPointPosition, 0);
        mRedPointShow = ta.getBoolean(R.styleable.HHTabItemLayout_hryt_tabItemRedPointShow, false);
        mIsChild = ta.getBoolean(R.styleable.HHTabItemLayout_hryt_tabItemChildEnable, false);
        if (mIsChild) {
            mOrientation = HORIZONTAL;
        }
        mItemMargin = getResources()
                .getDimensionPixelSize(R.dimen.hryt_tabItem_item_margin) / mHalfValue;
        mItemShadowMargin = getResources()
                .getDimensionPixelSize(R.dimen.hryt_tabItem_item_shadow_margin);

        mTextAndImagePadding = ta.getDimensionPixelSize(R.styleable.HHTabItemLayout_hryt_tabItemTextAndImagePadding,
                getResources().getDimensionPixelSize(R.dimen.hryt_tabItem_text_image_padding));

        if (mOrientation == VERTICAL) {
            mPaddingStart =
                    ta.getDimensionPixelSize(R.styleable.HHTabItemLayout_hryt_tabItemMarginTop,
                            getResources().getDimensionPixelSize(R.dimen.hryt_tabItem_vertical_padding));
            mPaddingSide =
                    ta.getDimensionPixelSize(R.styleable.HHTabItemLayout_hryt_tabItemMarginLeft,
                            getResources().getDimensionPixelSize(R.dimen.hryt_tabItem_vertical_top_bottom_padding));
        } else {
            mPaddingStart =
                    ta.getDimensionPixelSize(R.styleable.HHTabItemLayout_hryt_tabItemMarginLeft,
                            getResources().getDimensionPixelSize(R.dimen.hryt_tabItem_horizontal_left_padding));
            mPaddingSide =
                    ta.getDimensionPixelSize(R.styleable.HHTabItemLayout_hryt_tabItemMarginLeft,
                            getResources().getDimensionPixelSize(R.dimen.hryt_tabItem_horizontal_padding));
        }
        LogUtils.logD(TAG, "init: mPaddingStart = " + mPaddingStart + ", mItemMargin = " + mItemMargin);
        mScrollEnable = ta.getBoolean(R.styleable.HHTabItemLayout_hryt_tabItemScrollEnable,
                false);
        mDefaultBitmap = ta.getBoolean(R.styleable.HHTabItemLayout_hryt_tabItemDefaultBitmap,
                true);
        mBgDrawable = ta.getDrawable(R.styleable.HHTabItemLayout_hryt_tabItemBgDrawable);
        if (mBgDrawable == null && mDefaultBitmap) {
            if (!mIsChild && mOrientation == HORIZONTAL) {
                mBgDrawable = ContextCompat.getDrawable(getContext(), R.drawable.img_tabitem_top_bg);
            } else {
                mBgDrawable = null;
            }
        }

        mItemDrawable = ta.getDrawable(R.styleable.HHTabItemLayout_hryt_tabItemItemDrawable);
        checkDrawable = ta.getDrawable(R.styleable.HHTabItemLayout_hryt_checkedItemColor);
        if (!mIsChild && mOrientation == HORIZONTAL) {
            mItemDrawable = ta.getDrawable(R.styleable.HHTabItemLayout_hryt_tabItemTopBgDrawable);
            checkDrawable = ta.getDrawable(R.styleable.HHTabItemLayout_hryt_topCheckDrawable);
        }
        if (mOrientation == VERTICAL) {
            mItemDrawable = ContextCompat.getDrawable(getContext(), R.drawable.img_tabitem_side_checked2);
            checkDrawable = ContextCompat.getDrawable(getContext(), R.drawable.img_tabitem_side_checked);
        } else {
            if (mIsChild) {
                mItemDrawable = ContextCompat.getDrawable(getContext(), R.drawable.img_tabitem_second_cuttingline);
                checkDrawable = ContextCompat.getDrawable(getContext(), R.drawable.img_tabitem_second_checked);

            } else {
                mItemDrawable = ContextCompat.getDrawable(getContext(), R.drawable.img_tabitem_top_checked2);
                checkDrawable = ContextCompat.getDrawable(getContext(), R.drawable.img_tabitem_top_checked1);
            }

        }

        if (mBgDrawable != null) {
            if (mOrientation == VERTICAL) {
                mBgDrawableTintList =
                        ta.getColorStateList(R.styleable.HHTabItemLayout_hryt_tabItemNormalBackgroundTint);
                mBgDrawable.setTintList(mBgDrawableTintList);
            } else {
                mBgDrawableTopTintList =
                        ta.getColorStateList(R.styleable.HHTabItemLayout_hryt_tabItemTopBackgroundTint);
                mBgDrawable.setTintList(mBgDrawableTopTintList);
            }
        }

        if (checkDrawable != null) {
            if (!mIsChild && mOrientation == HORIZONTAL) {
                mTopCheckDrawableTintList = ta.getColorStateList(R.styleable.HHTabItemLayout_hryt_tabItemTopCheckTint);
                checkDrawable.setTintList(mTopCheckDrawableTintList);
            } else {
                mCheckDrawableTintList = ta.getColorStateList(R.styleable.HHTabItemLayout_hryt_tabItemCheckTint);
                checkDrawable.setTintList(mCheckDrawableTintList);
            }
        }


        ta.recycle();

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            setBackground(null);
        } else {
            setBackgroundDrawable(null);
        }

        addItems(mAddItems);

        mScroller = new Scroller(context, new FastOutSlowInInterpolator());
        ViewConfiguration configuration = ViewConfiguration.get(context);
        mMaximumFlingVelocity = configuration.getScaledMaximumFlingVelocity();

        mRectF = new RectF();

        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG | Paint.DITHER_FLAG);
        mPaint.setAntiAlias(true);
//        mPaint.setColor(mOuterColor);
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_OVER));

        mTextPaint = new Paint(Paint.ANTI_ALIAS_FLAG | Paint.DITHER_FLAG);
        mTextPaint.setAntiAlias(true);
        mTextPaint.setColor(mTextColor);
        mTextPaint.setTextSize(mTextSize);
    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        if (checkCount() || getMeasuredWidth() == 0) {
            return;
        }

        int widthMode = View.MeasureSpec.getMode(widthMeasureSpec);
        int widthSize = View.MeasureSpec.getSize(widthMeasureSpec);
        int heightMode = View.MeasureSpec.getMode(heightMeasureSpec);
        int heightSize = View.MeasureSpec.getSize(heightMeasureSpec);
        if (widthMode == View.MeasureSpec.EXACTLY) {
            mWidth = widthSize;
        } else {
            mWidth = getWidth();
        }
        if (heightMode == View.MeasureSpec.EXACTLY) {
            mHeight = heightSize;
        } else {
            mHeight = getHeight();
        }

        if (mOrientation == VERTICAL) {
            int itemHeight;
            if (getItemWidth() == 0) {
                if (mItemType == ICON_TEXT) {
                    mWidth = getResources().getDimensionPixelSize(R.dimen.hryt_tabItem_default_width);
                } else {
                    mWidth = getResources().getDimensionPixelSize(R.dimen.dimen_152);
                }
            } else {
                mWidth = getItemWidth();
            }
            if (mItemType == ICON_TEXT) {
                itemHeight = getResources().getDimensionPixelSize(R.dimen.hryt_tabItem_vertical_default_height);
            } else {
                itemHeight = getResources().getDimensionPixelSize(R.dimen.dimen_82);
            }
            mHeight = itemHeight * getCount() + mPaddingStart;
            mItemWidth = (mHeight - mHalfValue * mPaddingStart) / getCount();
            mStart = mPaddingStart + mItemWidth * mSelectedItem;
            mEnd = mHeight - mPaddingStart - mItemWidth;
        } else {
            int itemWidth;
            if (getItemWidth() == 0) {
                if (mIsChild) {
                    itemWidth = getResources().getDimensionPixelSize(R.dimen.dimen_140);
                } else {
                    if (mItemType == ICON_TEXT) {
                        itemWidth = getResources().getDimensionPixelSize(R.dimen.hryt_tabItem_default_width);
                    } else {
                        itemWidth = getResources().getDimensionPixelSize(R.dimen.dimen_152);
                    }
                }
            } else {
                itemWidth = getItemWidth();
            }
            if (mOrientation == HORIZONTAL && mIsChild) {
                mItemMargin = 24;
            }
            mHeight = getResources().getDimensionPixelSize(R.dimen.hryt_tabItem_default_height);
            mWidth = itemWidth * getCount() + mPaddingSide + (mItemMargin * (getCount() - 1));
            mItemWidth = (mWidth - mHalfValue * mPaddingStart) / getCount();
            mStart = mPaddingStart + mItemWidth * mSelectedItem + mItemMargin * mSelectedItem;
            mEnd = mWidth - mPaddingStart - mItemWidth;
        }
        setMeasuredDimension(mWidth, mHeight);
    }

    public void setItemType(int type) {
        this.mItemType = type;
        requestLayout();
        invalidate();
    }

    public void setItemWidth(int width) {
        this.changItemWidth = width;
        requestLayout();
    }

    public void setRedPointPosition(int position) {
        this.mRedPointPosition = position;
        mPositionList.add(mRedPointPosition);
        invalidate();
    }

    public void setRedPointShow(boolean show) {
        this.mRedPointShow = show;
        invalidate();
    }

    public void setRedPointPositionList(List<Integer> positionList) {
        this.mPositionList = positionList;
        for (int i = 0; i < mPositionList.size(); i++) {
            if (mSelectedItem == mPositionList.get(i)) {
                mPositionList.remove(i);
            }
        }
        invalidate();
    }

    public int getItemWidth() {
        return this.changItemWidth;
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (checkCount()) {
            return;
        }

        drawBackground(canvas);
        drawItem(canvas);
        drawCheckItem(canvas);
        drawText(canvas);
        if (mPositionList != null) {
            drawRedPoint(canvas, mPositionList);
        }
    }

    private void drawRedPoint(Canvas canvas, int position) {
        if (mRedPointShow) {
            if (mIsChild) {
                if (position != mSelectedItem) {
                    redPointDrawable = ContextCompat.getDrawable(getContext(), R.drawable.ic_img_tabitem_second_news);
                    if (redPointDrawable != null) {
                        int left, right, top, bottom;
                        left = (int) (getPointByPosition(position)
                                + mTextPaint.measureText(getName(position))
                                + redPointLeft);
                        top = (int) -mTextPaint.ascent();
                        right = (int) (getPointByPosition(position)
                                + mTextPaint.measureText(getName(position))
                                + redPointDrawable.getIntrinsicWidth()
                                + redPointLeft);
                        bottom = (int) (-mTextPaint.ascent() + redPointDrawable.getIntrinsicHeight());
                        redPointDrawable.setBounds(left, top, right, bottom);
                        redPointDrawable.draw(canvas);
                    }
                }
            }
        }
    }

    private void drawRedPoint(Canvas canvas, List<Integer> positionList) {
        if (!positionList.isEmpty()) {
            for (int i = 0; i < positionList.size(); i++) {
                drawRedPoint(canvas, positionList.get(i));
            }
        }
    }

    /**
     * drawBackground
     *
     * @param canvas   canvas
     * @param iBgColor bgColor
     */
    private void drawBackground(Canvas canvas, int iBgColor) {
        float radius;
        if (mOrientation == VERTICAL) {
            radius = mMode == ROUND ? mRadius : mWidth / 2;
        } else {
            radius = mMode == ROUND ? mRadius : mHeight / 2;
        }
        mPaint.setXfermode(null);
        mPaint.setColor(iBgColor);
        mRectF.set(0, 0, getWidth(), getHeight());
        canvas.drawRoundRect(mRectF, radius, radius, mPaint);
    }

    /**
     * draw view item background.
     *
     * @param canvas
     */
    private void drawBackground(Canvas canvas) {
        if (mBgDrawable == null) {
//            drawBackground(canvas, mOuterColor);
        } else {
            if (!mIsChild && mOrientation == HORIZONTAL) {
                mBgDrawable.setBounds(0, 0, getWidth(), getHeight());
                mBgDrawable.draw(canvas);
            }
        }
    }

    /**
     * draw view background item text and imahe.
     *
     * @param canvas
     */
    private void drawText(Canvas canvas) {
        mTextPaint.setXfermode(null);
        for (int i = 0; i < getCount(); i++) {
            boolean isTooLong = false;
            int limitWidth = mItemWidth - (int) mTextPaint.measureText(TEXT_ELLIPSIS);
            String simpleName = getClipText(getName(i), limitWidth, mTextPaint) + TEXT_ELLIPSIS;
            int start = mPaddingStart + i * mItemWidth + i * mItemMargin;
            if (getType(i) == BaseTabItem.ITEMSTRING) {
                if (getItemTextSize(i) == 0) {
                    if (i == mSelectedItem) {
                        mTextPaint.setTextSize(mSelectTextSize);
                    } else {
                        mTextPaint.setTextSize(mTextSize);
                    }
                } else {
                    mTextPaint.setTextSize(getItemTextSize(i));
                }
                float y;
                float x;
                if (mOrientation == VERTICAL) {
                    if (mTextPaint.measureText(getName(i)) > mItemWidth) {
                        isTooLong = true;
                    }
                    y = start + mItemWidth / 2 - (mTextPaint.ascent() + mTextPaint.descent()) / 2;
                    x = alignLeft;
                } else {
                    if (mTextPaint.measureText(getName(i)) > mItemWidth) {
                        limitWidth = mItemWidth - (int) mTextPaint.measureText(TEXT_ELLIPSIS) - 32 * mHalfValue;
                        simpleName = getClipText(getName(i), limitWidth, mTextPaint) + TEXT_ELLIPSIS;
                        isTooLong = true;
                        x = start + mPaddingStart + 32;
                    } else {
                        x = start + mItemWidth / 2 - mTextPaint.measureText(getName(i)) / 2;
                    }
                    y = getHeight() / 2 - (mTextPaint.ascent() + mTextPaint.descent()) / 2;
                }
                if (getItemEnable(i)) {
                    if (i == mSelectedItem) {
                        mTextPaint.setColor(mSelectedTextColor);
                        if (mOrientation == HORIZONTAL && !mIsChild) {
                            mTextPaint.setColor(getContext().getColor(R.color.hryt_common_black));
                        }
                    } else {
                        mTextPaint.setColor(mTextColor);
                    }
                } else {
                    mTextPaint.setColor(mDisableTextColor);
                }
                if (i == mSelectedItem) {
                    mTextPaint.setFakeBoldText(mSelectBold);
                } else {
                    mTextPaint.setFakeBoldText(mItemBold);
                }
                if (isTooLong) {
                    canvas.drawText(simpleName, x, y, mTextPaint);
                } else {
                    canvas.drawText(getName(i), x, y, mTextPaint);
                }
            } else if (getType(i) == BaseTabItem.ITEMIMG) {
                int width;
                int height;

                if (getItemTextSize(i) == 0) {
                    if (i == mSelectedItem) {
                        mTextPaint.setTextSize(mSelectTextSize);
                    } else {
                        mTextPaint.setTextSize(mTextSize);
                    }
                } else {
                    if (i == mSelectedItem) {
                        mTextPaint.setTextSize(mSelectTextSize);
                    } else {
                        mTextPaint.setTextSize(getItemTextSize(i));
                    }
                }
                Drawable drawable;
                if (getItemEnable(i) || getDisableDrawable(i) == null) {
                    if (mSelectedItem == i) {
                        drawable = getItemDrawable(i);
                    } else {
                        drawable = getBackDrawable(i);
                    }
                } else {
                    drawable = getDisableDrawable(i);
                }
                float w;
                if (mTextPaint.measureText(getName(i)) > mItemWidth - drawable.getIntrinsicWidth() + alignLeft) {
                    isTooLong = true;
                    limitWidth = mItemWidth - (int) mTextPaint.measureText(TEXT_ELLIPSIS)
                            - (mOrientation == VERTICAL ? 0 : alignLeft + drawable.getIntrinsicWidth());
                    simpleName = getClipText(getName(i), limitWidth, mTextPaint) + TEXT_ELLIPSIS;
                    w = mItemWidth - alignLeft;
                } else {
                    w = drawable.getIntrinsicWidth() + mTextPaint.measureText(getName(i));
                }
                if (mOrientation == VERTICAL) {
                    width = alignLeft;
                    height = start + mItemWidth / 2;
                } else {
                    width = (int) (start + (mItemWidth - w) / 2);
                    height = getHeight() / 2;
                }
                int itemDrawableWidth = drawable.getIntrinsicWidth() / 2;
                int itemDrawableHeight = drawable.getIntrinsicHeight() / 2;
                drawable.setBounds(width,
                        height - itemDrawableHeight, width + drawable.getIntrinsicWidth(),
                        height + itemDrawableHeight);
                drawable.draw(canvas);

                float y;
                float x;
                if (mOrientation == VERTICAL) {
                    y = start + mItemWidth / 2 - (mTextPaint.ascent() + mTextPaint.descent()) / 2;
                    x = drawable.getIntrinsicWidth() + width + mTextAndImagePadding;
                } else {
                    x = drawable.getIntrinsicWidth() + width + mTextAndImagePadding;
                    y = getHeight() / 2 - (mTextPaint.ascent() + mTextPaint.descent()) / 2;
                }
                if (getItemEnable(i)) {
                    if (i == mSelectedItem) {
                        mTextPaint.setColor(mSelectedTextColor);
                        if (mOrientation == HORIZONTAL && !mIsChild) {
                            mTextPaint.setColor(getContext().getColor(R.color.hryt_common_black));
                        }
                    } else {
                        mTextPaint.setColor(mTextColor);
                    }
                } else {
                    mTextPaint.setColor(mDisableTextColor);
                }
                if (i == mSelectedItem) {
                    mTextPaint.setFakeBoldText(mSelectBold);
                } else {
                    mTextPaint.setFakeBoldText(mItemBold);
                }
                if (isTooLong) {
                    canvas.drawText(simpleName, x, y, mTextPaint);
                } else {
                    canvas.drawText(getName(i), x, y, mTextPaint);
                }

            }
        }
    }

    private String getClipText(String origin, int limitWidth, Paint textPaint) {
        StringBuilder stringBuilder = new StringBuilder();
        if (origin == null) {
            return stringBuilder.toString();
        }
        for (int i = 0; i < origin.length(); i++) {
            if (textPaint.measureText(stringBuilder.toString() + origin.charAt(i)) > limitWidth) {
                return stringBuilder.toString();
            }
            stringBuilder.append(origin.charAt(i));
        }
        return stringBuilder.toString();
    }


    /**
     * draw view item by size.
     *
     * @param canvas
     */
    private void drawItem(Canvas canvas) {
        if (mItemDrawable == null) {
            float radius;
//            mPaint.setColor(mItemColor);
            if (mOrientation == VERTICAL) {
                radius = mMode == ROUND ? mItemRadius : mWidth / mHalfValue - mPaddingSide;
                mRectF.set(mPaddingSide, mStart + mItemMargin,
                        getWidth() - mPaddingSide, mStart + mItemWidth - mItemMargin);
            } else {
                radius = mMode == ROUND ? mItemRadius : mHeight / mHalfValue - mPaddingSide;
                mRectF.set(mStart + mItemMargin, mPaddingSide,
                        mStart + mItemWidth - mItemMargin, getHeight() - mPaddingSide);
            }
            canvas.drawRoundRect(mRectF, radius, radius, mPaint);
        } else {
            if (mIsChild) {
                mItemDrawable.setBounds(fixLinePosition, mPaddingSide + getHeight() - drawLineHeight,
                        getWidth() - fixLinePosition + (getCount() - 1) * mItemMargin, getHeight() + mPaddingSide);
                mItemDrawable.setAlpha(alpha);
            } else {
                if (mOrientation == VERTICAL) {
                    mItemDrawable.setBounds(0, mStart + mItemMargin,
                            getWidth(), mStart + mItemWidth - mItemMargin);
                } else {
                    mItemDrawable.setBounds(mStart + mItemMargin - mItemShadowMargin, mPaddingSide,
                            mStart + mItemWidth + mItemMargin + mItemShadowMargin, getHeight() - mPaddingSide);
                }
            }
            canvas.setDrawFilter(new PaintFlagsDrawFilter(0,
                    Paint.ANTI_ALIAS_FLAG | Paint.FILTER_BITMAP_FLAG));
            mItemDrawable.draw(canvas);
        }
    }

    /**
     * draw check item by size.
     *
     * @param canvas
     */
    private void drawCheckItem(Canvas canvas) {
        if (mIsChild) {
            checkDrawable = ContextCompat.getDrawable(getContext(), R.drawable.img_tabitem_second_checked);
            checkDrawable.setBounds(mStart + mItemMargin + checkToLeftOrRight,
                    getHeight() - mPaddingSide - checkDrawableToTop,
                    mStart + mItemWidth + mItemMargin - checkToLeftOrRight,
                    getHeight() - mPaddingSide);
        } else {
            if (mOrientation == VERTICAL) {
                checkDrawable.setBounds(0, mStart + mItemMargin,
                        verticalCheckWidth, mStart + mItemWidth - mItemMargin);
            } else {
                if (mItemType == ICON_TEXT) {
                    checkDrawable.setBounds(mStart + mItemMargin, mPaddingSide + fixTopCheckPosition,
                            mStart + mItemWidth - mItemMargin, getHeight() - mPaddingSide - fixTopCheckPosition);
                } else {
                    checkDrawable.setBounds(mStart + mItemMargin, mPaddingSide + fixTopCheckPosition,
                            mStart + mItemWidth + mItemMargin, getHeight() - mPaddingSide - fixTopCheckPosition);
                }
            }
        }
        canvas.setDrawFilter(new PaintFlagsDrawFilter(0,
                Paint.ANTI_ALIAS_FLAG | Paint.FILTER_BITMAP_FLAG));
        checkDrawable.draw(canvas);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (getCount() == 0 || !mEnabled || !mScroller.isFinished()) {
            return false;
        }
        if (mVelocityTracker == null) {
            mVelocityTracker = VelocityTracker.obtain();
        }
        mVelocityTracker.addMovement(event);

        int action = event.getActionMasked();
        if (action == MotionEvent.ACTION_DOWN || action == MotionEvent.ACTION_MOVE) {
            return true;
        } else if (action == MotionEvent.ACTION_UP) {
            BaseSoundEffect.getInstance().setViewSoundEffect(BaseTabItemLayout.this.getContext(),
                    mHrytSoundEffect);
            return dealTouchActionDown(event);
        }
        return super.onTouchEvent(event);
    }

    /**
     * deal touch action vertical down event
     *
     * @param event MotionEvent
     * @return deal result
     */
    private boolean dealTouchActionDown(MotionEvent event) {
        if (null != getParent()) {
            getParent().requestDisallowInterceptTouchEvent(true);
        }
        if (mOrientation == VERTICAL) {
            mTouchPosition = event.getY();
        } else {
            mTouchPosition = event.getX();
        }
        if (mTouchPosition > mPaddingStart + getCount() * mItemWidth) {
            return false;
        }
        movePosition = (int) ((mTouchPosition - mPaddingStart) / mItemWidth);
        LogUtils.logD(TAG, "dealTouchActionDown: movePosition = " + movePosition);
        if (!getItemEnable(movePosition) && mIsOnClickBeforeAnimation) {
            startScroll(getPointByPosition(mSelectedItem));
            return false;
        }
        movePosition = -1;
        final float x = event.getX();
        if (isItemInside(event.getX(), event.getY())) {
            if (!mScrollEnable) {
                return false;
            }
            return true;
        } else if (isItemOutside(event.getX(), event.getY())) {
            movePosition = (int) ((mTouchPosition - mPaddingStart) / mItemWidth);
            if (mIsOnClickBeforeAnimation) {
                startScroll(positionStart(mTouchPosition));
            }
            if (!mScrollEnable) {
                onStateChange(movePosition);
                return false;
            }
            return true;
        }
        return false;
    }

    /**
     * by item position get start coordinates.
     *
     * @param item item number
     * @return int item start position
     */
    private int getPointByPosition(int item) {
        return item * mItemWidth + mPaddingStart;
    }

    /**
     * if item select change set listener.
     *
     * @param selectedItem selected Item
     */
    private void onStateChange(int selectedItem) {
        mHandler.sendEmptyMessage(USER_TYPE);
    }

    /**
     * start scroll view item.
     *
     * @param dp int
     */
    private void startScroll(int dp) {
        startScroll(dp, ANIMATION_DURATION);
    }

    /**
     * start scroll view item.
     *
     * @param dp       x or y int
     * @param duration time
     */
    private void startScroll(int dp, int duration) {
        if (mOrientation == VERTICAL) {
            mScroller.startScroll(0, mStart, 0, dp - mStart, duration);
        } else {
            mScroller.startScroll(mStart, 0, dp - mStart, 0, duration);
        }

    }

    /**
     * get x or y item start x or y coordinates.
     *
     * @param dp x or y position
     * @return start position
     */
    private int positionStart(float dp) {
        return mPaddingStart + (int) ((dp - mPaddingStart) / mItemWidth) * mItemWidth;
    }

    /**
     * x y is item inside.
     *
     * @param x x position
     * @param y y position
     * @return item inside or not inside
     */
    private boolean isItemInside(float x, float y) {
        if (mOrientation == VERTICAL) {
            return y >= mStart && y <= mStart + mItemWidth && x > mItemMargin
                    && x < getWidth() - mItemMargin;
        } else {
            return x >= mStart && x <= mStart + mItemWidth && y > mItemMargin
                    && y < mHeight - mItemMargin;
        }
    }

    /**
     * x y is outside item.
     *
     * @param x x position
     * @param y y position
     * @return the item outside or not outside
     */
    private boolean isItemOutside(float x, float y) {
        if (mOrientation == VERTICAL) {
            return !isItemInside(x, y) && x > mItemMargin && x < getWidth() - mItemMargin
                    && y < mEnd + mItemWidth;
        } else {
            return !isItemInside(x, y) && y > mItemMargin && y < mHeight - mItemMargin
                    && x < mEnd + mItemWidth;
        }
    }

    @Override
    public int getCount() {
        return mSegmentedControlItems.size();
    }

    @Override
    public BaseTabItem getItem(int position) {
        int item = position < getCount() ? position : getCount() - 1;
        if (item < 0) {
            item = 0;
        }
        return mSegmentedControlItems.get(item);
    }

    @Override
    public int getType(int position) {
        return getItem(position).getType();
    }

    @Override
    public String getName(int position) {
        return getItem(position).getName();
    }

    @Override
    public Drawable getItemDrawable(int position) {
        return getItem(position).getItemDrawable(getContext());
    }

    @Override
    public Drawable getBackDrawable(int position) {
        return getItem(position).getBackDrawable(getContext());
    }

    @Override
    public Drawable getDisableDrawable(int position) {

        return getItem(position).getDisableDrawable(getContext());
    }

    @Override
    public boolean getItemEnable(int position) {
        return getItem(position).isEnable();
    }

    @Override
    public int getItemTextSize(int position) {
        return (int) getItem(position).getTextSize();
    }

    @Override
    public int getItemSelectTextSize(int position) {
        return (int) getItem(position).getSelectTextSize();
    }

    /**
     * chect item count is zero.
     *
     * @return boolean
     */
    private boolean checkCount() {
        return getCount() == 0;
    }

    /**
     * set select text is bold
     *
     * @param selectBold boolean is bold
     */
    public void setSelctTextBold(boolean selectBold) {
        this.mSelectBold = selectBold;
        invalidate();
    }

    /**
     * set select text is bold
     *
     * @param selectBold boolean is bold
     */
    public void setSelectTextBold(boolean selectBold) {
        this.mSelectBold = selectBold;
        invalidate();
    }

    /**
     * set text is bold
     *
     * @param itemBold boolean is bold
     */
    public void setTextBold(boolean itemBold) {
        this.mItemBold = itemBold;
        invalidate();
    }

    /**
     * set view mode is round or circle.
     *
     * @param mode int
     */
    public void setMode(@Mode int mode) {
        mMode = mode;
        invalidate();
    }

    /**
     * set item background's color.
     *
     * @param color int
     */
    public void setItemColor(int color) {
        this.mItemColor = color;
        invalidate();
    }

    /**
     * set whole background's color.
     *
     * @param color int
     */
    public void setOuterColor(int color) {
        this.mOuterColor = color;
        invalidate();
    }

    /**
     * set item text color.
     *
     * @param color int
     */
    public void setTextColor(int color) {
        this.mTextColor = color;
        invalidate();
    }

    /**
     * set select item text color.
     *
     * @param color int
     */
    public void setSelectedTextColor(int color) {
        this.mSelectedTextColor = color;
        invalidate();
    }

    /**
     * setBgDrawable
     *
     * @param drawable bgDrawable
     */
    public void setBgDrawable(Drawable drawable) {
        this.mBgDrawable = drawable;
        invalidate();
    }

    /**
     * setItemDrawable
     *
     * @param drawable itemDrawable
     */
    public void setItemDrawable(Drawable drawable) {
        this.mItemDrawable = drawable;
        invalidate();
    }

    /**
     * set view is can enable.
     *
     * @param enabled boolean
     */
    @Override
    public void setEnabled(boolean enabled) {
        this.mEnabled = enabled;
        for (BaseTabItem item : mSegmentedControlItems) {
            item.setEnable(enabled);
        }
        setAlpha(enabled ? ENABLE_ALPHA : DISENABLE_ALPHA);
        postInvalidate();
    }

    /**
     * view is can enable.
     *
     * @return boolean
     */
    @Override
    public boolean isEnabled() {
        return mEnabled;
    }

    /**
     * set item is can click
     *
     * @param position
     * @param enable
     */
    public void setItemEnabled(int position, boolean enable) {
        BaseTabItem item = getItem(position);
        item.setEnable(enable);
        postInvalidate();
    }

    /**
     * setOnClickBeforeAnimation
     *
     * @param beforeAnimation beforeAnimation
     */
    public void setOnClickBeforeAnimation(boolean beforeAnimation) {
        this.mIsOnClickBeforeAnimation = beforeAnimation;
        postInvalidate();
    }

    /**
     * getOnClickBeforeAnimation
     *
     * @return clickSegmented
     */
    public boolean getOnClickBeforeAnimation() {
        return mIsOnClickBeforeAnimation;
    }

    /**
     * set select item.
     *
     * @param position
     */
    public void setSelectedItem(int position) {
        if (position >= getCount()) {
            return;
        }
        setSelectedItem(position, false);
    }

    /**
     * set select item.
     *
     * @param position int
     * @param fromUser boolean is from user set
     */
    public void setSelectedItem(int position, boolean fromUser) {
        if (mItemWidth == 0) {
            mSelectedItem = position < getCount() ? position : getCount() - 1;
            postInvalidate();
        } else {
            mTouchPosition = getPointByPosition(position);
            startScroll((int) mTouchPosition);
            mHandler.sendEmptyMessage(NOT_FROM_USER);
        }
    }

    /**
     * get select item size.
     *
     * @return int
     */
    public int getSelectedItem() {
        return mSelectedItem;
    }

    /**
     * add view item string and image.
     *
     * @param list HHSegmentedControlItem
     */
    public void addItems(List<BaseTabItem> list) {
        if (list == null) {
            return;
        }
        mSegmentedControlItems.addAll(list);
        requestLayout();
        invalidate();
    }

    /**
     * add view item string.
     *
     * @param items CharSequence[]
     */
    public void addItems(CharSequence[] items) {
        if (items == null || items.length == 0) {
            return;
        }
        List<BaseTabItem> list = new ArrayList<>();
        for (CharSequence item : items) {
            list.add(new BaseTabItem(item.toString(), BaseTabItem.ITEMSTRING));
        }
        addItems(list);
    }

    /**
     * update view item string and image.
     *
     * @param list HHSegmentedControlItem
     */
    public void updateItems(List<BaseTabItem> list, int selectedItem) {
        if (list == null) {
            return;
        }
        mSegmentedControlItems.clear();
        mSegmentedControlItems.addAll(list);
        setSelectedItem(selectedItem);
        requestLayout();
        invalidate();
    }

    /**
     * update view item string
     *
     * @param items CharSequence[]
     */
    public void updateItems(CharSequence[] items, int selectedItem) {
        if (items == null || items.length == 0) {
            return;
        }
        List<BaseTabItem> list = new ArrayList<>();
        for (CharSequence item : items) {
            list.add(new BaseTabItem(item.toString(), BaseTabItem.ITEMSTRING));
        }
        updateItems(list, selectedItem);
    }

    /**
     * add view item.
     *
     * @param item HHSegmentedControlItem
     */
    public void addItem(BaseTabItem item) {
        if (item == null) {
            return;
        }
        mSegmentedControlItems.add(item);
        requestLayout();
        invalidate();
    }

    /**
     * set item click listener.
     *
     * @param listener OnSegItemClickListener
     */
    public void setOnSegItemClickListener(OnSegItemClickListener listener) {
        this.mOnSegItemClickListener = listener;
    }

    /**
     * set Is Child Type
     *
     * @param childType child type
     */
    public void setIsChildType(Boolean childType) {
        mIsChild = childType;
        if (mIsChild && mOrientation == HORIZONTAL) {
            int checkBgDrawableResId = mTargetResourceValues.get(R.attr.hryt_tabItemVerticalItemDrawable);
            if (AttributeHelper.isValid(checkBgDrawableResId)) {
                mItemDrawable = getResources().getDrawable(checkBgDrawableResId, getContext().getTheme());
            }
        }
        invalidate();
    }

    /**
     * item click listener.
     */
    public interface OnSegItemClickListener {
        void onItemClick(BaseTabItem item, int position);
    }

    /**
     * set item click from user listener.
     *
     * @param listener OnSegItemFromUserClickListener
     */
    public void setOnSegItemClickListener(OnSegItemClickFromUserListener listener) {
        this.mOnSegFromUserItemClickListener = listener;
    }

    /**
     * set text and image padding
     *
     * @param padding
     */
    public void setTextAndImagePadding(int padding) {
        this.mTextAndImagePadding = padding;
        invalidate();
    }

    /**
     * item click listener from user.
     */
    public interface OnSegItemClickFromUserListener {
        void onItemClick(BaseTabItem item, boolean fromUser, int position);
    }

    /**
     * item click listener.
     *
     * @param position int
     */
    private void onItemClick(int position, boolean mFromUser) {
        if (null != mOnSegItemClickListener) {
            mOnSegItemClickListener.onItemClick(getItem(position), position);
        }
        if (mOnSegFromUserItemClickListener != null) {
            mOnSegFromUserItemClickListener.onItemClick(getItem(position), mFromUser, position);
        }
    }

    /**
     * state of preservation.
     *
     * @return
     */
    @Override
    public Parcelable onSaveInstanceState() {
        Parcelable parcelable = super.onSaveInstanceState();
        PullToLoadState pullToLoadState = new PullToLoadState(parcelable);
        pullToLoadState.selectedItem = mSelectedItem;
        return pullToLoadState;
    }

    /**
     * Read save state.
     *
     * @param state
     */
    @Override
    public void onRestoreInstanceState(Parcelable state) {
        if (!(state instanceof PullToLoadState)) {
            return;
        }
        PullToLoadState pullToLoadState = ((PullToLoadState) state);
        super.onRestoreInstanceState(pullToLoadState.getSuperState());
        mSelectedItem = pullToLoadState.selectedItem;
        invalidate();
    }

    /**
     * Save interface state.
     */
    public static class PullToLoadState extends View.BaseSavedState {

        public int selectedItem;

        public static final Creator CREATOR = new Creator<PullToLoadState>() {

            @Override
            public PullToLoadState createFromParcel(Parcel source) {
                return new PullToLoadState(source);
            }

            @Override
            public PullToLoadState[] newArray(int size) {
                return new PullToLoadState[size];
            }
        };

        PullToLoadState(Parcel superState) {
            super(superState);
            selectedItem = superState.readInt();
        }

        PullToLoadState(Parcelable source) {
            super(source);
        }

        @Override
        public void writeToParcel(Parcel out, int flags) {
            super.writeToParcel(out, flags);
            out.writeInt(selectedItem);
        }
    }

    /**
     * set item text size
     *
     * @param position int position
     * @param textSize int text size
     */
    public void setItemTextSize(int position, int textSize) {
        BaseTabItem item = getItem(position);
        item.setTextSize(dip2px(getContext(), textSize));
        invalidate();
    }

    public void setItemTextSize(int textSize) {
        this.mTextSize = textSize;
        invalidate();
    }

    /**
     * set select item text size
     *
     * @param position int position
     * @param textSize int text size
     */
    public void setItemSelectTextSize(int position, int textSize) {
        BaseTabItem item = getItem(position);
        item.setSelectTextSize(dip2px(getContext(), textSize));
        invalidate();
    }

    public void setItemSelectTextSize(int textSize) {
        this.mSelectTextSize = textSize;
        invalidate();
    }

    /**
     * dip to px
     *
     * @param ctx     the context object
     * @param dpValue dip value
     * @return px value
     */
    private int dip2px(Context ctx, float dpValue) {
        final float scale = ctx.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

//    @Override
//    public void onFontScaleChanged(float oldFontScale, @NonNull Configuration newConfig) {
//        mSegmentedControlItems.forEach(new Consumer<HHTabItem>() {
//            @Override
//            public void accept(HHTabItem hhSegmentedControlItem) {
//                float textSize = hhSegmentedControlItem.getTextSize();
//                float newTextSize = textSize * newConfig.fontScale / oldFontScale;
//                hhSegmentedControlItem.setTextSize(newTextSize);
//
//                float selectTextSize = hhSegmentedControlItem.getSelectTextSize();
//                float newSelectTextSize = selectTextSize * newConfig.fontScale / oldFontScale;
//                hhSegmentedControlItem.setSelectTextSize(newSelectTextSize);
//            }
//        });
//
//        mTextSize = mTextSize * newConfig.fontScale / oldFontScale;
//        mSelectTextSize = mSelectTextSize * newConfig.fontScale / oldFontScale;
//        invalidate();
//    }

    private void updateDrawable(Drawable drawable, ColorStateList tintList) {
        if (drawable != null && tintList != null) {
            LogUtils.logD(TAG, "updateDrawable: ");
            drawable.setTint(tintList.getColorForState(drawable.getState(), 0));
            invalidateDrawable(drawable);
        }
    }

    @Override
    public void onConfigurationChanged(@NonNull Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        LogUtils.logD(TAG, "onOverlayChanged: ");
        int bgTintResId;
        int checkBgDrawableResId;
        int checkDrawableResId;
        int topBgDrawableResId;
        int checkTintResId;
        if (!mIsChild && mOrientation == HORIZONTAL) {
            topBgDrawableResId = mTargetResourceValues.get(R.attr.hryt_tabItemTopBgDrawable);
            if (AttributeHelper.isValid(topBgDrawableResId)) {
                mBgDrawable = getResources().getDrawable(topBgDrawableResId, getContext().getTheme());
            }
            checkDrawableResId = mTargetResourceValues.get(R.attr.hryt_topCheckDrawable);
            if (AttributeHelper.isValid(checkDrawableResId)) {
                checkDrawable = getResources().getDrawable(checkDrawableResId, getContext().getTheme());
            }
        } else {
            bgTintResId = mTargetResourceValues.get(R.attr.hryt_tabItemNormalBackgroundTint);
            if (AttributeHelper.isValid(bgTintResId)) {
                mBgDrawableTintList = getResources().getColorStateList(bgTintResId, getContext().getTheme());
            }
            updateDrawable(mBgDrawable, mBgDrawableTintList);
            checkTintResId = mTargetResourceValues.get(R.attr.hryt_tabItemCheckTint);
            if (AttributeHelper.isValid(checkTintResId)) {
                mCheckDrawableTintList = getResources().getColorStateList(checkTintResId, getContext().getTheme());
            }
            updateDrawable(checkDrawable, mCheckDrawableTintList);

            checkBgDrawableResId = mTargetResourceValues.get(R.attr.hryt_tabItemVerticalItemDrawable);
            if (AttributeHelper.isValid(checkBgDrawableResId)) {
                mItemDrawable = getResources().getDrawable(checkBgDrawableResId, getContext().getTheme());
            }
        }

        mTextColor = getContext().getColor(R.color.hryt_assist_content_color);
        mSelectedTextColor = getContext().getColor(R.color.hryt_main_content_color);
        invalidate();
    }

    private class H extends Handler {
        @Override
        public void handleMessage(Message msg) {
            boolean fromUser = msg.what == USER_TYPE;
            if (mScroller.computeScrollOffset()) {
                if (mOrientation == VERTICAL) {
                    mStart = mScroller.getCurrY();
                } else {
                    mStart = mScroller.getCurrX();
                }
                postInvalidate();
                // CHECKSTYLE.OFF: MagicNumber
                mHandler.sendEmptyMessageDelayed(msg.what, 16);
                // CHECKSTYLE.ON: MagicNumber
            }
            if (mScroller.isFinished() || (mScroller.getFinalY() == mScroller.getCurrY()
                    && mScroller.getFinalX() == mScroller.getCurrX())) {
                int movePosition = (int) ((mTouchPosition - mPaddingStart) / mItemWidth);
                if (mSelectedItem != movePosition) {
                    mSelectedItem = movePosition;
                    if (mIsChild && mOrientation == HORIZONTAL) {
                        for (int i = 0; i < mPositionList.size(); i++) {
                            if (mPositionList.get(i) == movePosition) {
                                mPositionList.remove(i);
                            }
                        }
                    }
                    onItemClick(mSelectedItem, fromUser);
                }
            }
        }
    }
}