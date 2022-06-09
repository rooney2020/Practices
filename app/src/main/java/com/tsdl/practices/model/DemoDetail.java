package com.tsdl.practices.model;

import android.app.Activity;

public class DemoDetail {
    private final int titleId;
    private final int descriptionId;
    private final Class<? extends android.app.Activity> activityClass;

    public DemoDetail(int titleId, int descriptionId,
                       Class<? extends android.app.Activity> activityClass) {
        super();
        this.titleId = titleId;
        this.descriptionId = descriptionId;
        this.activityClass = activityClass;
    }

    public int getTitleId() {
        return titleId;
    }

    public int getDescriptionId() {
        return descriptionId;
    }

    public Class<? extends Activity> getActivityClass() {
        return activityClass;
    }
}
