package com.tsdl.practices.model;

import android.content.ContentValues;

import com.tsdl.practices.util.Constants;

public class BillType {

    private int mId;
    private String mName;
    private int mIconId;
    private boolean mIsIncome;

    public BillType(String name, int iconId, boolean isIncome) {
        mName = name;
        mIconId = iconId;
        mIsIncome = isIncome;
    }

    public BillType(int id, String name, int iconId, boolean isIncome) {
        mId = id;
        mName = name;
        mIconId = iconId;
        mIsIncome = isIncome;
    }

    public boolean isIncome() {
        return mIsIncome;
    }

    public void setIsIncome(boolean isIncome) {
        this.mIsIncome = isIncome;
    }

    public int getId() {
        return mId;
    }

    public void setId(int id) {
        this.mId = id;
    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        this.mName = name;
    }

    public int getIconId() {
        return mIconId;
    }

    public void setIconId(int iconId) {
        this.mIconId = iconId;
    }

    public ContentValues getCreateSql() {
        ContentValues values = new ContentValues();
        values.put(Constants.COLUMN_NAME, mName);
        values.put(Constants.COLUMN_ICON_ID, mIconId);
        values.put(Constants.COLUMN_IS_INCOME, mIsIncome ? 1 : 0);
        return values;
    }
}
