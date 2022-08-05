package com.tsdl.practices.model;

import com.tsdl.practices.R;

public class Bill {
    private boolean isDate;
    private String mDate;
    private int mType;
    private String mDetail;
    private float mAmount;
    private float mTotalAmount;

    public Bill(int type, float amount, String detail) {
        isDate = false;
        mType = type;
        mAmount = amount;
        mDetail = detail;
    }

    public Bill(String date, float totalAmount) {
        isDate = true;
        mDate = date;
        mTotalAmount = totalAmount;
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

    public boolean isOutcome() {
        switch (mType) {
            case 3:
            case 4:
                return false;
            case 0:
            case 1:
            case 2:
            default:
                return true;
        }
    }

    public String getTypeText() {
        switch (mType) {
            case 0:
                return "零食";
            case 1:
                return "买菜";
            case 2:
                return "水果";
            case 3:
                return "工资";
            case 4:
                return "投资";
            default:
                return "其他";
        }
    }

    public int getTypeIcon() {
        switch (mType) {
            case 0:
                return R.drawable.icon_type_snack;
            case 1:
                return R.drawable.icon_type_vegetable;
            case 2:
                return R.drawable.icon_type_fruit;
            default:
                return R.drawable.btn_account;
        }
    }
}
