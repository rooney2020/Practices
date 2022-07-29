package com.tsdl.practices.base;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.graphics.PixelFormat;
import android.provider.Settings;
import android.util.Log;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.tsdl.practices.R;

public class DialogBase extends Dialog {

    public static final String TAG = DialogBase.class.getSimpleName();
    public static final int DIALOG_TYPE_SYSTEM = 0;
    public static final int DIALOG_TYPE_WIDGET_ON_LEFT = 1;
    public static final int DIALOG_TYPE_WIDGET_ON_RIGHT = 2;
    private static final int PARAM_OFFSET_NAV = 130;
    private static final int PARAM_WIDTH_APP = 1486;
    private static final int PARAM_OFFSET_WIDGET = 434;

    private int mDialogType;

    public DialogBase(@NonNull Context context, int dialogType) {
        super(context);
        mDialogType = dialogType;
    }

    @SuppressLint("ResourceType")
    @Override
    public void show() {
        if (!Settings.canDrawOverlays(getContext())) {
            Toast.makeText(getContext(), R.string.toast_content, Toast.LENGTH_SHORT).show();
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
     * @param rootView   Root View.
     * @param realDialog Real Dialog.
     * @param listener   OnClickListener.
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
        // params.type = WindowManager.LayoutParams.TYPE_SYSTEM_DIALOG;
        // emulator use below type
        params.type = WindowManager.LayoutParams.TYPE_APPLICATION_OVERLAY;
        params.format = PixelFormat.TRANSLUCENT;
//        params.height = WindowManager.LayoutParams.MATCH_PARENT;
        params.height = 800;
        params.y = 200;
        params.y = 0;
        if (DIALOG_TYPE_SYSTEM == mDialogType) {
            params.width = WindowManager.LayoutParams.MATCH_PARENT;
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
