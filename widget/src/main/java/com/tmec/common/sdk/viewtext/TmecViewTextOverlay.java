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

public class TmecViewTextOverlay extends ConstraintLayout {

    private TmecImageView mIvImage;
    private TmecTextExplainTitle mTvName;

    public TmecViewTextOverlay(Context context) {
        this(context, null);
    }

    public TmecViewTextOverlay(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public TmecViewTextOverlay(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
        initData(attrs);
    }

    public void init() {
        View rootView = LayoutInflater.from(getContext()).inflate(R.layout.tmec_view_text_overlay, this, true);

        mIvImage = rootView.findViewById(R.id.iv_image);
        mTvName = rootView.findViewById(R.id.tv_name);
    }

    private void initData(AttributeSet attrs) {
        TypedArray array = null;
        try {
            array = getContext().obtainStyledAttributes(attrs, R.styleable.TmecViewTextOverlay);
            setImage(array.getResourceId(R.styleable.TmecViewTextOverlay_tmec_img, 0));
            setName(array.getString(R.styleable.TmecViewTextOverlay_tmec_name));
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (array != null) {
                array.recycle();
            }
        }
    }

    public void setImage(int resId) {
        mIvImage.setImageSrc(resId);
    }

    public void setName(String text) {
        mTvName.setText(text);
    }
}
