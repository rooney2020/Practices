package com.tmec.common.sdk.viewtext;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;

import androidx.constraintlayout.widget.ConstraintLayout;

import com.tmec.common.sdk.R;
import com.tmec.common.sdk.base.TmecImageView;
import com.tmec.common.sdk.textview.TmecTextExplainTitle;

public class TmecViewTextBelow extends ConstraintLayout {

    private TmecImageView mSingerImage;
    private TmecTextExplainTitle mSingerName;

    public TmecViewTextBelow(Context context) {
        this(context, null);
    }

    public TmecViewTextBelow(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public TmecViewTextBelow(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
        initData(attrs);
    }

    public void init() {
        View mRootView = LayoutInflater.from(getContext()).inflate(R.layout.tmec_view_text_below, this, true);

        mSingerImage = mRootView.findViewById(R.id.iv_singer);
        mSingerName = mRootView.findViewById(R.id.tv_singer);
    }

    private void initData(AttributeSet attrs) {
        TypedArray array = null;
        try {
            array = getContext().obtainStyledAttributes(attrs, R.styleable.TmecViewTextBelow);
            setSingerImage(array.getResourceId(R.styleable.TmecViewTextBelow_tmec_singer_img, 0));
            setSingerName(array.getString(R.styleable.TmecViewTextBelow_tmec_singer_name));
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (array != null) {
                array.recycle();
            }
        }
    }

    public void setSingerImage(int resId) {
        mSingerImage.setImageSrc(resId);
    }

    public void setSingerName(String text) {
        mSingerName.setText(text);
    }
}
