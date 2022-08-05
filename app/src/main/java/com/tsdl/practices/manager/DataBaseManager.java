package com.tsdl.practices.manager;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.tsdl.practices.util.Constants;
import com.tsdl.practices.util.LogUtils;

public class DataBaseManager extends SQLiteOpenHelper {

    private static final String TAG = DataBaseManager.class.getSimpleName();

    public static final String CREATE_BILL = "create table Bill ("
            + "id integer primary key autoincrement, "
            + Constants.COLUMN_BILL_TIME + " text, "
            + Constants.COLUMN_AMOUNT + " real, "
            + Constants.COLUMN_BILL_TYPE + " integer, "
            + Constants.COLUMN_DETAIL + " text)";

    private Context mContext;

    public DataBaseManager(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
        mContext = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_BILL);
        LogUtils.logD(TAG, "Create succeeded");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists Bill");
        onCreate(db);
    }
}