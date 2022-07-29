package com.tsdl.practices.custom.popup;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.EditText;
import android.widget.ImageView;

import com.tsdl.practices.R;
import com.tsdl.practices.custom.base.ISkinChangedListener;
import com.tsdl.practices.custom.base.TmecSkinConfigurationManager;
import com.tsdl.practices.custom.button.TmecButtonCancel;
import com.tsdl.practices.custom.button.TmecButtonHighLight;
import com.tsdl.practices.custom.button.TmecTextClick;
import com.tsdl.practices.custom.textview.TmecTextSmallBody;
import com.tsdl.practices.custom.textview.TmecTextSmallTitle;
import com.tsdl.practices.custom.textview.TmecTextTitle;

public class TmecDialogMid extends TmecDialogBase implements ISkinChangedListener {

    private View mView;
    private View mRealDialog;
    private View mLayoutPassword;
    private ImageView mIvProgress;
    private TmecTextTitle mTvTitle;
    private TmecTextSmallBody mTvContentTwo;
    private TmecTextSmallBody mTvContentOne;
    private TmecTextSmallTitle mTvProgressTitle;
    private EditText mEtPassword;
    private TmecTextClick mTvExplainContent;
    private TmecButtonHighLight mBtnOk;
    private TmecButtonCancel mBtnCancel;

    private boolean mIsProgress;

    private int mBgDay;
    private int mBgNight;
    private int mEtBgDay;
    private int mEtBgNight;
    private int mProgressBgDay;
    private int mProgressBgNight;
    private int mTextColorNight;
    private int mTextColorLight;
    private int mEtHintTextColorDay;
    private int mEtHintTextColorNight;

    public TmecDialogMid(Context context, int dialogType, boolean isProgress) {
        super(context, dialogType);
        initBackground(context);
        initDialog(context);
        initView(isProgress);
    }

    private void initBackground(Context context) {
        mBgDay = R.drawable.tmec_dialog_mid_bg_day;
        mBgNight = R.drawable.tmec_dialog_small_bg_night;
        mEtBgDay = R.drawable.radius_day;
        mEtBgNight = R.drawable.radius_night;
        mProgressBgDay = R.drawable.progress_day;
        mProgressBgNight = R.drawable.progress_night;
        mEtHintTextColorDay = context.getColor(R.color.input_text_color_day);
        mEtHintTextColorNight = context.getColor(R.color.input_text_color_night);
        mTextColorNight = getContext().getColor(R.color.tmec_searchbar_night_text_color);
        mTextColorLight = getContext().getColor(R.color.tmec_searchbar_text_color);
    }

    private void initDialog(Context context) {
        mView = LayoutInflater.from(context).inflate(R.layout.tmec_dialog_mid, null);
        TmecSkinConfigurationManager.getInstance().register(this);
        setContentView(mView);
        initDialogInner();
    }

    private void initView(boolean isProgress) {
        mRealDialog = mView.findViewById(R.id.tmec_dialog_mid);
        mIvProgress = mView.findViewById(R.id.iv_progress);
        mTvTitle = mView.findViewById(R.id.tv_title);
        mTvContentTwo = mView.findViewById(R.id.tv_content_two);
        mTvContentOne = mView.findViewById(R.id.tv_content_one);
        mTvProgressTitle = mView.findViewById(R.id.mid_progress_title);
        mLayoutPassword = mView.findViewById(R.id.layout_password);
        mEtPassword = mView.findViewById(R.id.et_password);
        mTvExplainContent = mView.findViewById(R.id.tv_explain_content);
        mBtnOk = mView.findViewById(R.id.btn_ok);
        mBtnCancel = mView.findViewById(R.id.btn_cancel);

        setIsProgress(isProgress);
        onSkinChanged();
    }

    public void setTitle(String text) {
        mTvTitle.setVisibility(View.VISIBLE);
        mTvTitle.setText(text);
    }

    public void setContentTwo(String text) {
        mTvContentTwo.setVisibility(View.VISIBLE);
        mTvContentTwo.setText(text);
    }

    public void setContentOne(String text) {
        mTvContentOne.setVisibility(View.VISIBLE);
        mTvContentOne.setText(text);
    }

    public void setProgressTitle(String text) {
        mTvProgressTitle.setVisibility(View.VISIBLE);
        mTvProgressTitle.setText(text);
    }

    public void setEditable(boolean editable) {
        if (editable) {
            mLayoutPassword.setVisibility(View.VISIBLE);
        } else {
            mLayoutPassword.setVisibility(View.GONE);
        }
    }

    public void setIsProgress(boolean isProgress) {
        mIsProgress = isProgress;
        if (mIsProgress) {
            mIvProgress.setVisibility(View.VISIBLE);
            mTvProgressTitle.setVisibility(View.VISIBLE);
            Animation anim = AnimationUtils.loadAnimation(mView.getContext(), R.anim.anim_rotate);
            anim.setFillAfter(true);
            mIvProgress.startAnimation(anim);
        } else {
            mIvProgress.clearAnimation();
            mBtnOk.setVisibility(View.VISIBLE);
            mBtnCancel.setVisibility(View.VISIBLE);
        }
    }

    public void setInputType(int type) {
        mEtPassword.setInputType(type);
    }

    public String getInputPassword() {
        return mEtPassword.getText().toString();
    }

    public void setExplainContent(String text) {
        mTvExplainContent.setVisibility(View.VISIBLE);
        mTvExplainContent.setText(text);
    }

    public void setOnTapOutClickListener(View.OnClickListener listener) {
        setOnTapOutClickListener(mView, mRealDialog, listener);
    }

    public void setOnPositiveClickListener(View.OnClickListener listener) {
        mBtnOk.setOnClickListener(listener);
    }

    public void setOnNegativeClickListener(View.OnClickListener listener) {
        mBtnCancel.setOnClickListener(listener);
    }

    public void setOnExplainClickListener(View.OnClickListener listener) {
        mTvExplainContent.setOnClickListener(listener);
    }

    @Override
    public void show() {
        if (mIsProgress) {
            mIvProgress.setVisibility(View.VISIBLE);
            mTvProgressTitle.setVisibility(View.VISIBLE);
            Animation anim = AnimationUtils.loadAnimation(mView.getContext(), R.anim.anim_rotate);
            anim.setFillAfter(true);
            mIvProgress.startAnimation(anim);
        }
        super.show();
    }

    @Override
    public void dismiss() {
        if (!mIsProgress) {
            mIvProgress.clearAnimation();
        }
        super.dismiss();
    }

    @Override
    public void onSkinChanged() {
        if (TmecSkinConfigurationManager.getInstance().isNight()) {
            mRealDialog.setBackgroundResource(mBgNight);
            mIvProgress.setImageResource(mProgressBgNight);
            mLayoutPassword.setBackgroundResource(mEtBgNight);
            mEtPassword.setHintTextColor(mEtHintTextColorNight);
            mEtPassword.setTextColor(mTextColorNight);
        } else {
            mRealDialog.setBackgroundResource(mBgDay);
            mIvProgress.setImageResource(mProgressBgDay);
            mLayoutPassword.setBackgroundResource(mEtBgDay);
            mEtPassword.setHintTextColor(mEtHintTextColorDay);
            mEtPassword.setTextColor(mTextColorLight);
        }
    }
}
