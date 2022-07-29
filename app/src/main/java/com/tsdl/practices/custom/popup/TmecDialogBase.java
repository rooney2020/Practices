package com.tsdl.practices.custom.popup;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.graphics.PixelFormat;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import com.tsdl.practices.R;
import com.tsdl.practices.custom.toast.TmecToast;

public class TmecDialogBase extends Dialog {

    public static final String TAG = TmecDialogBase.class.getSimpleName();
    public static final int DIALOG_TYPE_SYSTEM = 0;
    public static final int DIALOG_TYPE_WIDGET_ON_LEFT = 1;
    public static final int DIALOG_TYPE_WIDGET_ON_RIGHT = 2;
    private static final int PARAM_WIDTH_SYSTEM = 1790;
    private static final int PARAM_HEIGHT = 720;
    private static final int PARAM_OFFSET_NAV = 130;
    private static final int PARAM_WIDTH_APP = 1486;
    private static final int PARAM_OFFSET_WIDGET = 434;

    private int mDialogType;

    public TmecDialogBase(@NonNull Context context, int dialogType) {
        super(context);
        mDialogType = dialogType;
    }

    @SuppressLint("ResourceType")
    @Override
    public void show() {
        if (!Settings.canDrawOverlays(getContext())) {
            TmecToast.showTextToast(getContext(), R.string.toast_content, Toast.LENGTH_SHORT);
            return;
        }
        super.show();
    }

    protected void initDialogInner() {
        setCanceledOnTouchOutside(false);
        setCancelable(false);

        Window dialogWindow = getWindow();
        dialogWindow.setGravity(Gravity.LEFT | Gravity.BOTTOM);
        dialogWindow.setFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL,
                WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL);
        WindowManager.LayoutParams params = getDialogParams(dialogWindow.getAttributes());
        dialogWindow.setDimAmount(0);
        dialogWindow.setAttributes(params);
        dialogWindow.setBackgroundDrawableResource(android.R.color.transparent);
    }

    public void switchDialogType(int dialogType) {
        if (mDialogType == dialogType) {
            return;
        }
        mDialogType = dialogType;
        initDialogInner();
    }

    /**
     * Set tap out click listener.
     *
     * @param rootView Root View.
     * @param realDialog Real Dialog.
     * @param listener OnClickListener.
     */
    public void setOnTapOutClickListener(View rootView, View realDialog, View.OnClickListener listener) {
        if (rootView == null) {
            Log.d(TAG, "rootView is null!");
            return;
        }
        if (realDialog == null) {
            Log.d(TAG, "realDialog is null!");
            return;
        }
        if (listener == null) {
            Log.d(TAG, "listener is null!");
            return;
        }
        rootView.setOnTouchListener(new View.OnTouchListener() {
            @SuppressLint("ClickableViewAccessibility")
            @Override
            public boolean onTouch(View view, MotionEvent event) {
                if (event.getActionMasked() == MotionEvent.ACTION_UP) {
                    if (event.getX() > realDialog.getX()
                            && event.getX() < realDialog.getX() + realDialog.getWidth()
                            && event.getY() > realDialog.getY()
                            && event.getY() < realDialog.getY() + realDialog.getHeight()) {
                        return false;
                    }
                    listener.onClick(view);
                }
                return false;
            }
        });
    }

    public WindowManager.LayoutParams getDialogParams(WindowManager.LayoutParams params) {
        params.type = WindowManager.LayoutParams.TYPE_SYSTEM_DIALOG;
        // emulator use below type
        //params.type = WindowManager.LayoutParams.TYPE_APPLICATION_OVERLAY;
        params.format = PixelFormat.TRANSLUCENT;
        params.height = PARAM_HEIGHT;
        params.y = 0;
        if (DIALOG_TYPE_SYSTEM == mDialogType) {
            params.width = PARAM_WIDTH_SYSTEM;
            params.x = PARAM_OFFSET_NAV;
        } else {
            params.width = PARAM_WIDTH_APP;
            if (DIALOG_TYPE_WIDGET_ON_LEFT == mDialogType) {
                params.x = PARAM_OFFSET_WIDGET;
            } else {
                params.x = PARAM_OFFSET_NAV;
            }
        }
        return params;
    }
}
