package com.tsdl.practices.manager;

import static android.content.Context.MODE_PRIVATE;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.tsdl.practices.R;
import com.tsdl.practices.model.Bill;
import com.tsdl.practices.model.BillType;
import com.tsdl.practices.util.Constants;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@SuppressLint("StaticFieldLeak")
public class DataManager {
    private static volatile DataManager mInstance;
    private static BillType[] mBillTypes;
    public static boolean mIsInit;
    private final SQLiteDatabase mSQLiteDatabase;

    private DataManager(Context context) {
        DataBaseManager mDataBaseManager = new DataBaseManager(context, Constants.DATABASE_NAME, null, 1);
        mSQLiteDatabase = mDataBaseManager.getWritableDatabase();

        if (mIsInit || isInit(context)) {
            mIsInit = false;
            setInit(context, false);
            insertBillType(context);
            insertBill();
        }
        queryAllBillType();
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

    /**
     * 查询每天总支出/收入
     */
    public synchronized Bill[] queryDailyAmount() {
        String[] columns = new String[]{"sum(" + Constants.COLUMN_AMOUNT + ") as " + Constants.COLUMN_AMOUNT,
                "date(" + Constants.COLUMN_BILL_TIME + ") as " + Constants.COLUMN_BILL_TIME};
        String groupBy = "date(" + Constants.COLUMN_BILL_TIME + ")";
        String orderBy = Constants.COLUMN_BILL_TIME + " DESC";

        return queryBill(true, columns, null, null, groupBy, null, orderBy);
    }

    /**
     * 查询某一天 支出/收入详情 的账单列表
     */
    public synchronized Bill[] queryDailyBill(String billDate) {
        String selection = "date(" + Constants.COLUMN_BILL_TIME + ") = ?";
        String[] selectionArgs = new String[]{billDate};
        String orderBy = Constants.COLUMN_BILL_TIME + " DESC";

        return queryBill(false, null, selection, selectionArgs, null, null, orderBy);
    }

    /**
     * 查询每天总支出/收入 及 详细账单列表
     */
    public synchronized Bill[] queryDailyAmountAndBill() {
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

    public Bill[] queryBill(boolean isDate, String[] columns, String selection, String[] selectionArgs, String groupBy,
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

    public BillType[] queryBillType(String[] columns, String selection, String[] selectionArgs, String groupBy,
                                    String having, String orderBy) {
        Cursor cursor = mSQLiteDatabase.query(Constants.TABLE_BILL_TYPE, columns, selection, selectionArgs,
                groupBy, having, orderBy);
        BillType[] billTypes = new BillType[cursor.getCount()];
        int index = 0;
        if (cursor.moveToFirst()) {
            do {
                BillType bill = new BillType(
                        cursor.getInt(cursor.getColumnIndex(Constants.COLUMN_ID)),
                        cursor.getString(cursor.getColumnIndex(Constants.COLUMN_NAME)),
                        cursor.getInt(cursor.getColumnIndex(Constants.COLUMN_ICON_ID)),
                        cursor.getInt(cursor.getColumnIndex(Constants.COLUMN_IS_INCOME)) == 1
                );
                billTypes[index++] = bill;
            } while (cursor.moveToNext());
            cursor.close();
        }
        return billTypes;
    }

    public static BillType getBillTypeById(int typeId) {
        BillType billType = null;
        for (BillType type : mBillTypes) {
            if (type.getId() == typeId) {
                billType = type;
            }
        }
        return billType;
    }

    public synchronized BillType[] queryAllBillType() {
        mBillTypes = queryBillType(null, null, null, null, null, null);
        return mBillTypes;
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

    public synchronized void insertBillType(Context context) {
        BillType[] bills = new BillType[]{
                new BillType(context.getString(R.string.type_eat), R.drawable.bt_eat, false),
                new BillType(context.getString(R.string.type_traffic), R.drawable.bt_traffic, false),
                new BillType(context.getString(R.string.type_amusement), R.drawable.bt_amusement, false),
                new BillType(context.getString(R.string.type_medical), R.drawable.bt_medical, false),
                new BillType(context.getString(R.string.type_education), R.drawable.bt_education, false),
                new BillType(context.getString(R.string.type_contact), R.drawable.bt_money, false),
                new BillType(context.getString(R.string.type_other), R.drawable.bt_other, false),
                new BillType(context.getString(R.string.type_snack), R.drawable.bt_snack, false),
                new BillType(context.getString(R.string.type_supermarket), R.drawable.bt_shopping, false),
                new BillType(context.getString(R.string.type_vegetable), R.drawable.bt_vegetable, false),
                new BillType(context.getString(R.string.type_dinner), R.drawable.bt_eat, false),
                new BillType(context.getString(R.string.type_fruit), R.drawable.bt_fruit, false),
                new BillType(context.getString(R.string.type_car), R.drawable.bt_car, false),
                new BillType(context.getString(R.string.type_telephone_bill), R.drawable.bt_money, false),
                new BillType(context.getString(R.string.type_parent), R.drawable.bt_household, false),

                new BillType(context.getString(R.string.type_salary), R.drawable.bt_salary, true),
                new BillType(context.getString(R.string.type_red_packet), R.drawable.bt_hongbao, true),
                new BillType(context.getString(R.string.type_reimbursement), R.drawable.bt_reimbursement, true),
                new BillType(context.getString(R.string.type_part_time_job), R.drawable.bt_part_time_job, true),
                new BillType(context.getString(R.string.type_investment_income), R.drawable.bt_investment_income, true),
                new BillType(context.getString(R.string.type_other_income), R.drawable.bt_other_income, true),
                new BillType(context.getString(R.string.type_social_security), R.drawable.bt_shebao, true),
                new BillType(context.getString(R.string.type_public_reserve_funds), R.drawable.bt_public_reserve_funds, true),
        };
        for (BillType bill : bills) {
            mSQLiteDatabase.insert(Constants.TABLE_BILL_TYPE, null, bill.getCreateSql());
        }
    }

    public synchronized void insertBill() {
        Bill[] bills = new Bill[]{
                new Bill("今天", -73.30f),
                new Bill(8, -35.3f, "凉糕", "2022-08-04 12:24:38"),
                new Bill(1, -38f, "凉皮鸡架", "2022-08-04 12:06:08"),
                new Bill("昨天", -27.70f),
                new Bill(8, -27.7f, "鸭货", "2022-08-03 19:13:19"),
                new Bill("2日", 9989.70f),
                new Bill(16, 10000f, "7月份工资", "2022-08-02 21:43:58"),
                new Bill(7, -10.3f, "其他物品", "2022-08-02 20:21:32")
        };
        for (Bill bill : bills) {
            if (!bill.isDate()) {
                mSQLiteDatabase.insert(Constants.TABLE_BILL, null, bill.getCreateSql());
            }
        }
    }

    @SuppressLint("CommitPrefEdits")
    public static void setInit(Context context, boolean isInit) {
        SharedPreferences.Editor editor = context.getSharedPreferences(Constants.SP_INIT, MODE_PRIVATE).edit();
        editor.putBoolean(Constants.SP_KEY_INIT, isInit);
        editor.apply();
    }

    public static boolean isInit(Context context) {
        SharedPreferences sp = context.getSharedPreferences(Constants.SP_INIT, MODE_PRIVATE);
        return sp.getBoolean(Constants.SP_KEY_INIT, true);
    }
}

