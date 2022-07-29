package com.tsdl.practices.model;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;

public class DemoDetail {
    private final int titleId;
    private final int descriptionId;
    private final Callback mCallback;

    public DemoDetail(int titleId, int descriptionId, Callback callback) {
        super();
        this.titleId = titleId;
        this.descriptionId = descriptionId;
        mCallback = callback;
    }

    public DemoDetail(int titleId, int descriptionId, Context context, Class<? extends Activity> activityClass) {
        super();
        this.titleId = titleId;
        this.descriptionId = descriptionId;
        if (context != null && activityClass != null) {
            mCallback = () -> context.startActivity(new Intent(context, activityClass));
        } else {
            mCallback = null;
        }
    }

    public int getTitleId() {
        return titleId;
    }

    public int getDescriptionId() {
        return descriptionId;
    }

    public Callback getCallback() {
        return mCallback;
    }

    public interface Callback {
        void execute();
    }
}
