package com.tsdl.practices.popup;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.RequiresApi;

import com.tsdl.practices.R;
import com.tsdl.practices.enums.SkinMode;
import com.tsdl.practices.manager.SkinManager;

public class CustomDialog extends DialogBase implements SkinManager.OnSkinModeChangeListener {

    private View mView;
    private View mRealDialog;
    private TextView mTvTitle;
    private TextView mTvMainContent;
    private Button mBtnOk;
    private Button mBtnCancel;

    private int mBgDay;
    private int mBgNight;

    @RequiresApi(api = Build.VERSION_CODES.O)
    public CustomDialog(Context context, int dialogType) {
        super(context, dialogType);
        initBackground();
        initDialog(context);
        initView();
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public CustomDialog(Context context, int dialogType, String title, String content) {
        this(context, dialogType);
        setTitle(title);
        setMainContent(content);
    }

    private void initBackground() {
        mBgDay = R.drawable.tmec_dialog_big_bg_day;
        mBgNight = R.drawable.tmec_dialog_big_bg_night;
    }

    @SuppressLint("InflateParams")
    @RequiresApi(api = Build.VERSION_CODES.O)
    private void initDialog(Context context) {
        mView = LayoutInflater.from(context).inflate(R.layout.custom_dialog, null);
        setContentView(mView);
        initDialogInner();
    }

    private void initView() {
        mRealDialog = mView.findViewById(R.id.tmec_dialog_big);
        mTvTitle = mView.findViewById(R.id.tv_title);
        mTvMainContent = mView.findViewById(R.id.tv_main_content);
        mBtnOk = mView.findViewById(R.id.btn_ok);
        mBtnCancel = mView.findViewById(R.id.btn_cancel);
        SkinManager.Singleton.getInstance().register(this);
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

    public void setOnTapOutClickListener(View view, View.OnClickListener listener) {
        setOnTapOutClickListener(view, mRealDialog, listener);
    }

    public void setOnPositiveClickListener(View.OnClickListener listener) {
        mBtnOk.setOnClickListener(listener);
    }

    public void setOnNegativeClickListener(View.OnClickListener listener) {
        mBtnCancel.setOnClickListener(listener);
    }

    @Override
    public void onSkinModeChange(SkinMode skinMode) {
        if (skinMode == SkinMode.NIGHT) {
            mRealDialog.setBackgroundResource(mBgNight);
        } else if (skinMode == SkinMode.LIGHT) {
            mRealDialog.setBackgroundResource(mBgDay);
        }
    }
}
