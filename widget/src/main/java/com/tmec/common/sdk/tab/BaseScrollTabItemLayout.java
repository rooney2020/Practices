package com.tmec.common.sdk.tab;

import android.content.Context;
import android.content.res.Configuration;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.IntDef;
import androidx.annotation.NonNull;

import com.tmec.common.sdk.R;
import com.tmec.common.sdk.base.BaseRelativeLayout;
import com.tsdl.common.util.LogUtils;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.List;

/**
 * author : HaonanWu
 * e-mail : Haonan_Wu@human-horizons.com
 * time   : 2022/7/1
 * desc   : This is HHScrollTabItemLayout
 * version: 1.0
 */
public class BaseScrollTabItemLayout extends BaseRelativeLayout {
    public static final String TAG = "HHScrollTabItemLayout";
    private BaseTabItemLayout tabItemLayout;
    private static final int VERTICAL = 1;
    private static final int HORIZONTAL = 0;
    private int mOrientation = HORIZONTAL;
    private int mTextSize;
    private int mSelectTextSize;
    private boolean mIsChild;
    private final int defaultVerticalSize = R.dimen.dimen_21;
    private final int defaultHorizontalSize = R.dimen.dimen_21;
    private int mWidth;
    private int mHeight;
    private int mPaddingSide;
    private int mHalfValue = 2;
    private int mSelectedItem = 0;
    private BaseVerticalScrollView hhVerticalScrollView;
    private BaseHorizontalScrollView hhHorizontalScrollView;
    private int overMode;
    private static final int ROUND = 0;
    private static final int CIRCLE = 1;
    private int mItemType = TEXT;
    public static final int TEXT = 0;
    public static final int ICON_TEXT = 1;
    private int mItemWidth;
    private int mRedPointPosition;
    private boolean mRedPointShow;

    @IntDef({ROUND, CIRCLE})
    @Retention(RetentionPolicy.SOURCE)
    public @interface Mode {
    }

    public BaseScrollTabItemLayout(Context context) {
        this(context, null);
    }

    public BaseScrollTabItemLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public BaseScrollTabItemLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        this(context, attrs, defStyleAttr, 0);
    }

    public BaseScrollTabItemLayout(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(attrs);
    }

    private void init(AttributeSet attrs) {
        TypedArray a = getContext().obtainStyledAttributes(attrs, R.styleable.HHScrollTabItemLayout);
        mOrientation = a.getInt(R.styleable.HHScrollTabItemLayout_hryt_scrollTabItemOrientation, HORIZONTAL);
        mIsChild = a.getBoolean(R.styleable.HHScrollTabItemLayout_hryt_scrollTabItemChildEnable, false);
        overMode = a.getInt(R.styleable.HHScrollTabItemLayout_hryt_scrollTabItemOverScrollMode, mHalfValue);
        mItemType = a.getInt(R.styleable.HHScrollTabItemLayout_hryt_scrollTabItemItemType, TEXT);
        mItemWidth = a.getDimensionPixelSize(R.styleable.HHScrollTabItemLayout_hryt_scrollTabItemItemWidth, 0);
        mRedPointPosition = a.getInteger(R.styleable.HHScrollTabItemLayout_hryt_scrollTabItemRedPointPosition, 0);
        mRedPointShow = a.getBoolean(R.styleable.HHScrollTabItemLayout_hryt_scrollTabItemRedPointShow, false);
        if (mOrientation == VERTICAL) {
            LayoutInflater.from(getContext()).inflate(R.layout.layout_vertical_tabitem, this, true);
            hhVerticalScrollView = findViewById(R.id.tab_vertical_layout);
            tabItemLayout = findViewById(R.id.tab_vertical_item_layout);
            hhVerticalScrollView.setOverScrollMode(overMode);
            hhVerticalScrollView.setVerticalScrollBarEnabled(false);
        } else {
            LayoutInflater.from(getContext()).inflate(R.layout.layout_horizontal_tabitem, this, true);
            hhHorizontalScrollView = findViewById(R.id.tab_horizontal_layout);
            tabItemLayout = findViewById(R.id.tab_horizontal_item_layout);
            hhHorizontalScrollView.setOverScrollMode(overMode);
            hhHorizontalScrollView.setHorizontalScrollBarEnabled(false);
        }
        if (mItemWidth != 0) {
            tabItemLayout.setItemWidth(mItemWidth);
        }
        tabItemLayout.setIsChildType(mIsChild);
        tabItemLayout.setItemType(mItemType);
        tabItemLayout.setRedPointPosition(mRedPointPosition);
        tabItemLayout.setRedPointShow(mRedPointShow);
        if (mOrientation == VERTICAL) {
            mTextSize = a.getDimensionPixelSize(R.styleable.HHScrollTabItemLayout_hryt_scrollTabItemTextSize,
                    getResources().getDimensionPixelSize(defaultVerticalSize));
            mSelectTextSize =
                    a.getDimensionPixelSize(R.styleable.HHScrollTabItemLayout_hryt_scrollTabItemSelectTextSize,
                            getResources().getDimensionPixelSize(defaultVerticalSize));
        } else {
            mTextSize = a.getDimensionPixelSize(R.styleable.HHScrollTabItemLayout_hryt_scrollTabItemTextSize,
                    getResources().getDimensionPixelSize(defaultHorizontalSize));
            mSelectTextSize =
                    a.getDimensionPixelSize(R.styleable.HHScrollTabItemLayout_hryt_scrollTabItemSelectTextSize,
                            getResources().getDimensionPixelSize(defaultHorizontalSize));
        }
        tabItemLayout.setItemTextSize(mTextSize);
        tabItemLayout.setItemSelectTextSize(mSelectTextSize);

        if (mOrientation == VERTICAL) {
            mPaddingSide =
                    a.getDimensionPixelSize(R.styleable.HHScrollTabItemLayout_hryt_scrollTabItemMarginLeft,
                            getResources().getDimensionPixelSize(R.dimen.hryt_tabItem_vertical_top_bottom_padding));
        } else {
            mPaddingSide =
                    a.getDimensionPixelSize(R.styleable.HHScrollTabItemLayout_hryt_scrollTabItemMarginLeft,
                            getResources().getDimensionPixelSize(R.dimen.hryt_tabItem_horizontal_padding));
        }
    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        if (getMeasuredWidth() == 0 || getCount() == 0) {
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
            if (getItemWidth() == 0) {
                if (mItemType == ICON_TEXT) {
                    mWidth = getResources().getDimensionPixelSize(R.dimen.hryt_tabItem_default_width);
                } else {
                    mWidth = getResources().getDimensionPixelSize(R.dimen.dimen_152);
                }
            } else {
                mWidth = getItemWidth();
            }
            hhVerticalScrollView.setBackgroundResource(R.drawable.img_tabitem_side_bg);
        } else {
            mHeight = getResources().getDimensionPixelSize(R.dimen.hryt_tabItem_default_height);
        }
        setMeasuredDimension(mWidth, mHeight);
    }

    public void addItems(List<BaseTabItem> list) {
        if (tabItemLayout != null) {
            tabItemLayout.addItems(list);
        }
    }

    public void addItem(BaseTabItem item) {
        if (tabItemLayout != null) {
            tabItemLayout.addItem(item);
        }
    }

    public void setRedPointPosition(int position) {
        if (tabItemLayout != null) {
            tabItemLayout.setRedPointPosition(position);
        }
    }

    public void setRedPointShow(boolean show) {
        if (tabItemLayout != null) {
            tabItemLayout.setRedPointShow(show);
        }
    }

    public void setRedPointPositionList(List<Integer> positionList) {
        if (tabItemLayout != null) {
            tabItemLayout.setRedPointPositionList(positionList);
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (tabItemLayout != null) {
            return tabItemLayout.onTouchEvent(event);
        }
        return false;
    }

    public void setOnSegItemClickListener(BaseTabItemLayout.OnSegItemClickFromUserListener listener) {
        if (tabItemLayout != null) {
            tabItemLayout.setOnSegItemClickListener(listener);
        }
    }

    public void setOnSegItemClickListener(BaseTabItemLayout.OnSegItemClickListener listener) {
        if (tabItemLayout != null) {
            tabItemLayout.setOnSegItemClickListener(listener);
        }
    }

    public void addItems(CharSequence[] items) {
        if (tabItemLayout != null) {
            tabItemLayout.addItems(items);
        }
    }

    public void setItemWidth(int width) {
        if (tabItemLayout != null) {
            mItemWidth = width;
            tabItemLayout.setItemWidth(width);
        }
    }

    public int getItemWidth() {
        return mItemWidth;
    }

    public int getCount() {
        if (tabItemLayout != null) {
            return tabItemLayout.getCount();
        }
        return 0;
    }

    public BaseTabItem getItem(int position) {
        if (tabItemLayout != null) {
            return tabItemLayout.getItem(position);
        }
        return null;
    }

    public int getType(int position) {
        if (tabItemLayout != null) {
            return tabItemLayout.getType(position);
        }
        return 0;
    }

    public String getName(int position) {
        if (tabItemLayout != null) {
            return tabItemLayout.getName(position);
        }
        return null;
    }

    public Drawable getItemDrawable(int position) {
        if (tabItemLayout != null) {
            return tabItemLayout.getItemDrawable(position);
        }
        return null;
    }

    public Drawable getBackDrawable(int position) {
        if (tabItemLayout != null) {
            return tabItemLayout.getBackDrawable(position);
        }
        return null;
    }

    public Drawable getDisableDrawable(int position) {
        if (tabItemLayout != null) {
            return tabItemLayout.getDisableDrawable(position);
        }
        return null;
    }

    public boolean getItemEnable(int position) {
        if (tabItemLayout != null) {
            return tabItemLayout.getItemEnable(position);
        }
        return false;
    }

    public int getItemTextSize(int position) {
        if (tabItemLayout != null) {
            return tabItemLayout.getItemTextSize(position);
        }
        return 0;
    }

    public int getItemSelectTextSize(int position) {
        if (tabItemLayout != null) {
            return tabItemLayout.getItemSelectTextSize(position);
        }
        return 0;
    }

    public void setSelectTextBold(boolean selectBold) {
        if (tabItemLayout != null) {
            tabItemLayout.setSelectTextBold(selectBold);
        }
    }

    public void setTextBold(boolean itemBold) {
        if (tabItemLayout != null) {
            tabItemLayout.setTextBold(itemBold);
        }
    }

    public void setMode(@Mode int mode) {
        if (tabItemLayout != null) {
            tabItemLayout.setMode(mode);
        }
    }

    public void setItemColor(int color) {
        if (tabItemLayout != null) {
            tabItemLayout.setItemColor(color);
        }
    }

    public void setOuterColor(int color) {
        if (tabItemLayout != null) {
            tabItemLayout.setOuterColor(color);
        }
    }

    public void setTextColor(int color) {
        if (tabItemLayout != null) {
            tabItemLayout.setTextColor(color);
        }
    }

    public void setSelectedTextColor(int color) {
        if (tabItemLayout != null) {
            tabItemLayout.setSelectedTextColor(color);
        }
    }

    public void setBgDrawable(Drawable drawable) {
        if (tabItemLayout != null) {
            tabItemLayout.setBgDrawable(drawable);
        }
    }

    public void setItemDrawable(Drawable drawable) {
        if (tabItemLayout != null) {
            tabItemLayout.setItemDrawable(drawable);
        }
    }


    public void setEnabled(boolean enabled) {
        if (tabItemLayout != null) {
            tabItemLayout.setEnabled(enabled);
        }
    }

    public boolean isEnabled() {
        if (tabItemLayout != null) {
            return tabItemLayout.isEnabled();
        }
        return false;
    }

    public void setItemEnabled(int position, boolean enable) {
        if (tabItemLayout != null) {
            tabItemLayout.setItemEnabled(position, enable);
        }
    }

    public void setOnClickBeforeAnimation(boolean beforeAnimation) {
        if (tabItemLayout != null) {
            tabItemLayout.setOnClickBeforeAnimation(beforeAnimation);
        }
    }

    public boolean getOnClickBeforeAnimation() {
        if (tabItemLayout != null) {
            return tabItemLayout.getOnClickBeforeAnimation();
        }
        return false;
    }

    public void setSelectedItem(int position) {
        if (tabItemLayout != null) {
            tabItemLayout.setSelectedItem(position);
        }
    }

    /**
     * set select item.
     *
     * @param position int
     * @param fromUser boolean is from user set
     */
    public void setSelectedItem(int position, boolean fromUser) {
        if (tabItemLayout != null) {
            tabItemLayout.setSelectedItem(position, fromUser);
        }
    }

    /**
     * get select item size.
     *
     * @return int
     */
    public int getSelectedItem() {
        if (tabItemLayout != null) {
            return tabItemLayout.getSelectedItem();
        }
        return 0;
    }

    public void updateItems(List<BaseTabItem> list, int selectedItem) {
        if (tabItemLayout != null) {
            tabItemLayout.updateItems(list, selectedItem);
        }
    }

    public void updateItems(CharSequence[] items, int selectedItem) {
        if (tabItemLayout != null) {
            tabItemLayout.updateItems(items, selectedItem);
        }
    }


    public void setIsChildType(Boolean childType) {
        if (tabItemLayout != null) {
            tabItemLayout.setIsChildType(childType);
        }
    }

    public void setTextAndImagePadding(int padding) {
        if (tabItemLayout != null) {
            tabItemLayout.setTextAndImagePadding(padding);
        }
    }

    @Override
    public Parcelable onSaveInstanceState() {
        Parcelable parcelable = super.onSaveInstanceState();
        BaseTabItemLayout.PullToLoadState pullToLoadState = new BaseTabItemLayout.PullToLoadState(parcelable);
        pullToLoadState.selectedItem = mSelectedItem;
        return pullToLoadState;
    }

    @Override
    public void onRestoreInstanceState(Parcelable state) {
        if (!(state instanceof BaseTabItemLayout.PullToLoadState)) {
            return;
        }
        BaseTabItemLayout.PullToLoadState pullToLoadState = ((BaseTabItemLayout.PullToLoadState) state);
        super.onRestoreInstanceState(pullToLoadState.getSuperState());
        mSelectedItem = pullToLoadState.selectedItem;
        invalidate();
    }

    public void setItemTextSize(int position, int textSize) {
        if (tabItemLayout != null) {
            tabItemLayout.setItemTextSize(position, textSize);
        }
    }

    public void setItemTextSize(int textSize) {
        if (tabItemLayout != null) {
            tabItemLayout.setItemTextSize(textSize);
        }
    }

    public void setItemSelectTextSize(int position, int textSize) {
        if (tabItemLayout != null) {
            tabItemLayout.setItemSelectTextSize(position, textSize);
        }
    }

    public void setItemSelectTextSize(int textSize) {
        if (tabItemLayout != null) {
            tabItemLayout.setItemSelectTextSize(textSize);
        }
    }

    @Override
    public void onConfigurationChanged(@NonNull Configuration newConfig) {
        LogUtils.logD(TAG, "onOverlayChanged: ");
        if (mOrientation == VERTICAL) {
            hhVerticalScrollView.setBackground(getContext().getDrawable(R.drawable.img_tabitem_side_bg));
        }
        if (tabItemLayout != null) {
            tabItemLayout.onConfigurationChanged(newConfig);
        }
    }
}
