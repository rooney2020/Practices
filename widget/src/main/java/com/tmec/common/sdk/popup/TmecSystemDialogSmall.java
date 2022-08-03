package com.tmec.common.sdk.popup;

import android.content.Context;

public class TmecSystemDialogSmall extends TmecDialogSmall {

    private TmecSystemDialogSmall(Context context, int dialogType) {
        super(context, dialogType);
    }

    public TmecSystemDialogSmall(Context context) {
        super(context, DIALOG_TYPE_SYSTEM);
    }
}
