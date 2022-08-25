package com.tsdl.common.entity;

import android.content.ContentValues;

import com.tsdl.common.util.Constants;

public class Bill {

    private int mId;
    private boolean isDate;
    private String mDate;
    private String mBillTime;
    private int mType;
    private String mDetail;
    private float mAmount;
    private float mTotalAmount;

    public Bill(int type, float amount, String detail, String billTime) {
        isDate = false;
        mType = type;
        mAmount = amount;
        mDetail = detail;
        mBillTime = billTime;
    }

    public Bill(int id, int type, float amount, String detail, String billTime) {
        isDate = false;
        mId = id;
        mType = type;
        mAmount = amount;
        mDetail = detail;
        mBillTime = billTime;
    }

    public Bill(String date, float totalAmount) {
        isDate = true;
        mDate = date;
        mTotalAmount = totalAmount;
    }

    public int getId() {
        return mId;
    }

    public void setId(int id) {
        this.mId = id;
    }

    public String getBillTime() {
        return mBillTime;
    }

    public void setBillTime(String billTime) {
        this.mBillTime = billTime;
    }

    public boolean isDate() {
        return isDate;
    }

    public void setDate(boolean date) {
        isDate = date;
    }

    public String getDate() {
        return mDate;
    }

    public void setDate(String date) {
        this.mDate = date;
    }

    public float getTotalAmount() {
        return mTotalAmount;
    }

    public void setTotalAmount(float totalAmount) {
        this.mTotalAmount = totalAmount;
    }

    public int getType() {
        return mType;
    }

    public void setType(int type) {
        this.mType = type;
    }

    public String getDetail() {
        return mDetail;
    }

    public void setDetail(String detail) {
        this.mDetail = detail;
    }

    public float getAmount() {
        return mAmount;
    }

    public void setAmount(float amount) {
        this.mAmount = amount;
    }

    public ContentValues getCreateSql() {
        ContentValues values = new ContentValues();
        if (mId != 0) {
            values.put(Constants.COLUMN_ID, mId);
        }
        values.put(Constants.COLUMN_AMOUNT, mAmount);
        values.put(Constants.COLUMN_BILL_TYPE, mType);
        values.put(Constants.COLUMN_DETAIL, mDetail);
        values.put(Constants.COLUMN_BILL_TIME, mBillTime);
        return values;
    }
}
