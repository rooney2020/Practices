package com.tsdl.practices.custom.itemphone;

import android.content.Context;
import android.content.res.TypedArray;
import androidx.annotation.Nullable;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.style.ForegroundColorSpan;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.LinearLayout;

import com.tsdl.practices.R;
import com.tsdl.practices.custom.base.ISkinChangedListener;
import com.tsdl.practices.custom.base.TmecImageView;
import com.tsdl.practices.custom.base.TmecSkinConfigurationManager;
import com.tsdl.practices.custom.textview.TmecTextExplainBody;
import com.tsdl.practices.custom.textview.TmecTextMinorTitle;
import com.tsdl.practices.custom.textview.TmecTextTipsTitle;

public class TmecItemPhone extends LinearLayout implements ISkinChangedListener {

    private TmecTextMinorTitle mName;
    private TmecTextMinorTitle mNumber;

    private TmecTextExplainBody mFirstName;
    private TmecTextTipsTitle mPhoneKind;
    private TmecTextTipsTitle mPhoneTime;
    private TmecImageView mBottomLine;
    private int mLength;
    private int mStartPosition;


    public TmecItemPhone(Context context) {
        this(context, null);
    }

    public TmecItemPhone(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public TmecItemPhone(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        this(context, attrs, defStyleAttr, 0);
    }

    public TmecItemPhone(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        initView();
        initAttrs(attrs);
    }

    private void initView() {
        LayoutInflater.from(getContext()).inflate(R.layout.tmec_item_phone, this, true);
        mFirstName = findViewById(R.id.first_name);
        mName = findViewById(R.id.phone_name);
        mPhoneKind = findViewById(R.id.phone_kind);
        mPhoneTime = findViewById(R.id.phone_time);
        mNumber = findViewById(R.id.phone_number);
        mBottomLine = findViewById(R.id.item_phone_bottom_line);
    }

    private void initAttrs(AttributeSet attrs) {
        TmecSkinConfigurationManager.getInstance().register(this);
        TypedArray array = null;
        try {
            array = getContext().obtainStyledAttributes(attrs, R.styleable.TmecItemPhone);
            setPhoneName(array.getString(R.styleable.TmecItemPhone_tmec_phone_name));
            setPhoneTime(array.getString(R.styleable.TmecItemPhone_tmec_phone_time));
            setPhoneKind(array.getString(R.styleable.TmecItemPhone_tmec_phone_kind));
            setPhoneNumber(array.getString(R.styleable.TmecItemPhone_tmec_phone_number));
            setShowBottomLine(array.getBoolean(R.styleable.TmecItemPhone_tmec_item_phone_show_bottom_line, false));
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (array != null) {
                array.recycle();
            }
        }
        updateLayout();
    }

    /**
     * set show bottom line or not.
     *
     * @param isShow isShow
     */
    public void setShowBottomLine(boolean isShow) {
        mBottomLine.setVisibility(isShow ? VISIBLE : GONE);
    }

    /**
     * @param kind phone kind
     */
    public void setPhoneKind(CharSequence kind) {
        mPhoneKind.setText(kind);
    }

    /**
     * @param time time
     */
    public void setPhoneTime(CharSequence time) {
        mPhoneTime.setText(time);
    }

    /**
     * @param name name
     */
    public void setPhoneName(CharSequence name) {
        mName.setText(name);
        if (name != null && !name.equals("")) {
            mFirstName.setText(((String) name).substring(0, 1));
        }
    }

    /**
     * @param number number
     */
    public void setPhoneNumber(CharSequence number) {
        mNumber.setText(number);
    }

    /**
     * @param startPosition start position of the highlight part
     * @param length high light part length
     */
    public void setNumberHighlightPosition(int startPosition, int length) {
        mStartPosition = startPosition;
        mLength = length;
        SpannableStringBuilder builder = new SpannableStringBuilder(mNumber.getText().toString());
        ForegroundColorSpan span;
        if (TmecSkinConfigurationManager.getInstance().isNight()) {
            span = new ForegroundColorSpan(getResources().getColor(R.color.text_title_highlight_night));
        } else {
            span = new ForegroundColorSpan(getResources().getColor(R.color.text_title_highlight));
        }
        builder.setSpan(span, mStartPosition, mStartPosition + mLength, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        mNumber.setText(builder);
    }


    @Override
    public void onSkinChanged() {
        updateLayout();
        setNumberHighlightPosition(mStartPosition, mLength);
    }

    private void updateLayout() {
        if (TmecSkinConfigurationManager.getInstance().isNight()) {
            mFirstName.setBackgroundResource(R.drawable.bg_icon_phone_contects_night);
        } else {
            mFirstName.setBackgroundResource(R.drawable.bg_icon_phone_contects);
        }
    }
}
