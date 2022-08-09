package com.tsdl.practices.manager;

import android.annotation.SuppressLint;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.tsdl.practices.model.Bill;
import com.tsdl.practices.util.Constants;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@SuppressLint("StaticFieldLeak")
public class DataManager {
    private static volatile DataManager mInstance;
    private Context mContext;
    private SQLiteDatabase mSQLiteDatabase;

    private DataManager(Context context) {
        DataBaseManager mDataBaseManager = new DataBaseManager(context, Constants.DATABASE_NAME, null, 3);
        mSQLiteDatabase = mDataBaseManager.getWritableDatabase();
    }

    public static DataManager getsInstance(Context context) {
        if (mInstance == null) {
            synchronized (DataManager.class) {
                if (mInstance == null) {
                    mInstance = new DataManager(context);
                }
            }
        }
        return mInstance;
    }

    public void createBill() {
//        Bill[] bills = new Bill[]{
//                new Bill("今天", -73.3f),
//                new Bill(0, 35.3f, "凉糕", "2022-08-04 12:24:38"),
//                new Bill(1, 38f, "凉皮鸡架", "2022-08-04 12:06:08"),
//                new Bill("昨天", -27.7f),
//                new Bill(0, 27.7f, "鸭货", "2022-08-03 19:13:19"),
//                new Bill("2日", 9989.7f),
//                new Bill(3, 10000f, "7月份工资", "2022-08-02 21:43:58"),
//                new Bill(5, 10.3f, "其他物品", "2022-08-02 20:21:32")
//        };
//        for (int i = 0; i < bills.length; i++) {
//            if (!bills[i].isDate()) {
//                mSQLiteDatabase.insert(Constants.TABLE_BILL, null, bills[i].getSql());
//            }
//        }
    }

    /**
     * 查询每天总支出/收入
     */
    public Bill[] queryDailyAmount() {
        String[] columns = new String[]{"sum(" + Constants.COLUMN_AMOUNT + ") as " + Constants.COLUMN_AMOUNT,
                "date(" + Constants.COLUMN_BILL_TIME + ") as " + Constants.COLUMN_BILL_TIME};
        String groupBy = "date(" + Constants.COLUMN_BILL_TIME + ")";
        String orderBy = Constants.COLUMN_BILL_TIME + " DESC";

        return query(true, columns, null, null, groupBy, null, orderBy);
    }

    /**
     * 查询某一天 支出/收入详情 的账单列表
     */
    public Bill[] queryDailyBill(String billDate) {
        String selection = "date(" + Constants.COLUMN_BILL_TIME + ") = ?";
        String[] selectionArgs = new String[]{billDate};
        String orderBy = Constants.COLUMN_BILL_TIME + " DESC";

        return query(false, null, selection, selectionArgs, null, null, orderBy);
    }

    /**
     * 查询每天总支出/收入 及 详细账单列表
     */
    public Bill[] queryDailyAmountAndBill() {
        Bill[] amount = queryDailyAmount();
        if (amount == null || amount.length == 0) {
            return new Bill[0];
        }
        List<Bill> data = new ArrayList<>();
        for (int i = 0; i < amount.length; i++) {
            data.add(amount[i]);
            data.addAll(Arrays.asList(queryDailyBill(amount[i].getDate())));
        }
        return toArray(data);
    }

    public Bill[] query(boolean isDate, String[] columns, String selection, String[] selectionArgs, String groupBy,
                        String having, String orderBy) {
        Cursor cursor = mSQLiteDatabase.query(Constants.TABLE_BILL, columns, selection, selectionArgs,
                groupBy, having, orderBy);
        Bill[] bills = new Bill[cursor.getCount()];
        int index = 0;
        if (cursor.moveToFirst()) {
            do {
                Bill bill;
                if (isDate) {
                    bill = new Bill(
                            cursor.getString(cursor.getColumnIndex(Constants.COLUMN_BILL_TIME)),
                            cursor.getFloat(cursor.getColumnIndex(Constants.COLUMN_AMOUNT))
                    );
                } else {
                    bill = new Bill(
                            cursor.getInt(cursor.getColumnIndex(Constants.COLUMN_ID)),
                            cursor.getInt(cursor.getColumnIndex(Constants.COLUMN_BILL_TYPE)),
                            cursor.getFloat(cursor.getColumnIndex(Constants.COLUMN_AMOUNT)),
                            cursor.getString(cursor.getColumnIndex(Constants.COLUMN_DETAIL)),
                            cursor.getString(cursor.getColumnIndex(Constants.COLUMN_BILL_TIME))
                    );
                }
                bills[index++] = bill;
            } while (cursor.moveToNext());
            cursor.close();
        }
        return bills;
    }

    public static Bill[] toArray(List<Bill> bills) {
        if (bills == null) {
            return null;
        }
        Bill[] results = new Bill[bills.size()];
        for (int i = 0; i < bills.size(); i++) {
            results[i] = bills.get(i);
        }
        return results;
    }
}

