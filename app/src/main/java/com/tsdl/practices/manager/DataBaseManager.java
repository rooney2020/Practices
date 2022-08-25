package com.tsdl.practices.manager;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.tsdl.common.util.LogUtils;
import com.tsdl.common.util.Constants;

public class DataBaseManager extends SQLiteOpenHelper {

    private static final String TAG = DataBaseManager.class.getSimpleName();

    public static final String CREATE_BILL = "create table " + Constants.TABLE_BILL + " ("
            + "id integer primary key autoincrement, "
            + Constants.COLUMN_BILL_TIME + " text, "
            + Constants.COLUMN_AMOUNT + " real, "
            + Constants.COLUMN_BILL_TYPE + " integer, "
            + Constants.COLUMN_DETAIL + " text)";

    public static final String CREATE_BILL_TYPE = "create table " + Constants.TABLE_BILL_TYPE + " ("
            + "id integer primary key autoincrement, "
            + Constants.COLUMN_NAME + " text, "
            + Constants.COLUMN_ICON_ID + " integer, "
            + Constants.COLUMN_IS_INCOME + " integer)";

    private Context mContext;

    public DataBaseManager(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
        mContext = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_BILL);
        db.execSQL(CREATE_BILL_TYPE);
        LogUtils.logD(TAG, "Create succeeded");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        DataManager.mIsInit = true;
        db.execSQL("drop table if exists " + Constants.TABLE_BILL);
        db.execSQL("drop table if exists " + Constants.TABLE_BILL_TYPE);
        onCreate(db);
    }
}