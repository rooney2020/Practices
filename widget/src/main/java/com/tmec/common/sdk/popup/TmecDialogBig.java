package com.tmec.common.sdk.popup;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;

import com.tmec.common.sdk.R;
import com.tmec.common.sdk.base.SkinManager;
import com.tmec.common.sdk.base.SkinMode;
import com.tmec.common.sdk.button.TmecButtonCancel;
import com.tmec.common.sdk.button.TmecButtonHighLight;
import com.tmec.common.sdk.textview.TmecTextSmallBody;
import com.tmec.common.sdk.textview.TmecTextTitle;

public class TmecDialogBig extends TmecDialogBase implements SkinManager.OnSkinModeChangeListener {

    private View mView;
    private View mRealDialog;
    private TmecTextTitle mTvTitle;
    private TmecTextSmallBody mTvMainContent;
    private TmecButtonHighLight mBtnOk;
    private TmecButtonCancel mBtnCancel;

    private int mBgDay;
    private int mBgNight;
    private int mMaskBgDay;
    private int mMaskBgNight;

    public TmecDialogBig(Context context, int dialogType) {
        super(context, dialogType);
        initBackground();
        initDialog(context);
        initView();
    }

    public TmecDialogBig(Context context, int dialogType, String title, String content) {
        this(context, dialogType);
        setTitle(title);
        setMainContent(content);
    }

    private void initBackground() {
        mBgDay = R.drawable.tmec_dialog_big_bg_day;
        mBgNight = R.drawable.tmec_dialog_big_bg_night;
        mMaskBgDay = R.drawable.bg_mask_light;
        mMaskBgNight = R.drawable.bg_mask_night;
    }

    private void initDialog(Context context) {
        mView = LayoutInflater.from(context).inflate(R.layout.tmec_dialog_big, null);
        SkinManager.Singleton.getInstance().register(this);
        setContentView(mView);
        initDialogInner();
    }

    private void initView() {
        mRealDialog = mView.findViewById(R.id.tmec_dialog_big);
        mTvTitle = mView.findViewById(R.id.tv_title);
        mTvMainContent = mView.findViewById(R.id.tv_main_content);
        mBtnOk = mView.findViewById(R.id.btn_ok);
        mBtnCancel = mView.findViewById(R.id.btn_cancel);
        updateBg();
    }

    public void setTitle(String text) {
        mTvTitle.setVisibility(View.VISIBLE);
        mTvTitle.setText(text);
    }

    public void setMainContent(String text) {
        mTvMainContent.setVisibility(View.VISIBLE);
        mTvMainContent.setText(text);
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

    private void updateBg() {
        if (SkinManager.Singleton.getInstance().isNight()) {
            mRealDialog.setBackgroundResource(mBgNight);
            mView.setBackgroundResource(mMaskBgNight);
        } else {
            mRealDialog.setBackgroundResource(mBgDay);
            mView.setBackgroundResource(mMaskBgDay);
        }
    }

    @Override
    public void onSkinModeChange(SkinMode skinMode) {
        updateBg();
    }
}
