package com.tsdl.practices.custom.popup;

import android.content.Context;

public class TmecSystemDialogBig extends TmecDialogBig {

    private TmecSystemDialogBig(Context context, int dialogType) {
        super(context, dialogType);
    }

    private TmecSystemDialogBig(Context context, int dialogType, String title, String content) {
        super(context, dialogType, title, content);
    }

    public TmecSystemDialogBig(Context context) {
        super(context, DIALOG_TYPE_SYSTEM);
    }

    public TmecSystemDialogBig(Context context, String title, String content) {
        super(context, DIALOG_TYPE_SYSTEM, title, content);
    }
}
