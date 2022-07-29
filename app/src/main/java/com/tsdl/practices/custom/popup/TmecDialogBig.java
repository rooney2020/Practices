package com.tsdl.practices.custom.popup;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;

import com.tsdl.practices.R;
import com.tsdl.practices.custom.base.ISkinChangedListener;
import com.tsdl.practices.custom.base.TmecSkinConfigurationManager;
import com.tsdl.practices.custom.button.TmecButtonCancel;
import com.tsdl.practices.custom.button.TmecButtonHighLight;
import com.tsdl.practices.custom.textview.TmecTextSmallBody;
import com.tsdl.practices.custom.textview.TmecTextTitle;

public class TmecDialogBig extends TmecDialogBase implements ISkinChangedListener {

    private View mView;
    private View mRealDialog;
    private TmecTextTitle mTvTitle;
    private TmecTextSmallBody mTvMainContent;
    private TmecButtonHighLight mBtnOk;
    private TmecButtonCancel mBtnCancel;

    private int mBgDay;
    private int mBgNight;

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
    }

    private void initDialog(Context context) {
        mView = LayoutInflater.from(context).inflate(R.layout.tmec_dialog_big, null);
        TmecSkinConfigurationManager.getInstance().register(this);
        setContentView(mView);
        initDialogInner();
    }

    private void initView() {
        mRealDialog = mView.findViewById(R.id.tmec_dialog_big);
        mTvTitle = mView.findViewById(R.id.tv_title);
        mTvMainContent = mView.findViewById(R.id.tv_main_content);
        mBtnOk = mView.findViewById(R.id.btn_ok);
        mBtnCancel = mView.findViewById(R.id.btn_cancel);
        onSkinChanged();
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

    @Override
    public void onSkinChanged() {
        if (TmecSkinConfigurationManager.getInstance().isNight()) {
            mRealDialog.setBackgroundResource(mBgNight);
        } else {
            mRealDialog.setBackgroundResource(mBgDay);
        }
    }
}
