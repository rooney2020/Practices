package com.tmec.common.sdk.popup;

import android.content.Context;

public class TmecSystemDialogMid extends TmecDialogMid {

    private TmecSystemDialogMid(Context context, int dialogType, boolean isProgress) {
        super(context, dialogType, isProgress);
    }

    public TmecSystemDialogMid(Context context, boolean isProgress) {
        super(context, DIALOG_TYPE_SYSTEM, isProgress);
    }
}
