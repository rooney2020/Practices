package com.tmec.common.sdk.button;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;

import com.tmec.common.sdk.R;
import com.tmec.common.sdk.textview.TmecTextTitle;


public class TmecButtonText extends LinearLayout {

    private TmecTextTitle mTextView;


    public TmecButtonText(Context context) {
        this(context, null);
    }

    public TmecButtonText(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public TmecButtonText(Context context, AttributeSet attrs, int defStyleAttr) {
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
    public TmecButtonText(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        initView();
        init(attrs);
    }

    private void initView() {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.tmec_btn_text, this, true);
        mTextView = view.findViewById(R.id.btn_text_tv);
    }

    private void init(AttributeSet attrs) {
        TypedArray array = null;
        try {
            array = getContext().obtainStyledAttributes(attrs, R.styleable.TmecButtonText);
            mTextView.setText(array.getString(R.styleable.TmecButtonText_android_text));
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (array != null) {
                array.recycle();
            }
        }
    }

    public void setText(CharSequence text) {
        mTextView.setText(text);
    }


}
