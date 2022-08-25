package com.tsdl.common.util;

import java.util.Arrays;
import java.util.List;

public class Constants {
    public static final String STRING_EMPTY = "";
    public static final String STRING_BLANK = "         ";
    public static final String KEY_VALUE_CLEAR = "C";
    public static final String KEY_VALUE_MORE = "...";
    public static final String KEY_VALUE_DELETE = "D";
    public static final String KEY_VALUE_EQUAL = "=";
    public static final String KEY_VALUE_PLUS = "+";
    public static final String KEY_VALUE_POINT = ".";
    public static final String KEY_VALUE_MINUS = "-";
    public static final String KEY_VALUE_TIMES = "×";
    public static final String KEY_VALUE_DIVIDE = "÷";
    public static final String KEY_VALUE_PERCENT = "%";
    public static final String KEY_VALUE_LEFT_BRACKET = "(";
    public static final String KEY_VALUE_RIGHT_BRACKET = ")";

    public static final String DISPLAY_ERROR = "错误";

    public static final String PARAMETER_END = "parameter_end";
    public static final String PARAMETER_NEXT_NUM = "parameter_next_num";

    public static final List<String> CHARACTERS = Arrays.asList(".", "%", "×", "÷", "+", "-");
    public static final List<String> CHARACTERS_SPLIT = Arrays.asList("×", "÷", "+");
    public static final List<String> CHARACTERS_HIGH_PRIOR = Arrays.asList("×", "÷");

    public static final String SP_NAME = "game2048data";
    public static final String SP_KEY_SEPARATOR = "-";
    public static final String SP_KEY_SCORE = "score";
    public static final int CARD_NUM = 4;
    public static final int GAME_VIEW_COLOR = 0xffbbadc0;

    public static final int PAGE_NUM = 3;

    public static final String SP_INIT = "sp_init";
    public static final String SP_KEY_INIT = "sp_key_init";
    public static final String DATABASE_NAME = "AccountBill.db";
    public static final String TABLE_BILL = "Bill";
    public static final String TABLE_BILL_TYPE = "BillType";
    public static final String COLUMN_ID = "id";
    public static final String COLUMN_BILL_TIME = "bill_time";
    public static final String COLUMN_AMOUNT = "amount";
    public static final String COLUMN_BILL_TYPE = "bill_type";
    public static final String COLUMN_DETAIL = "detail";
    public static final String COLUMN_NAME = "name";
    public static final String COLUMN_ICON_ID = "icon_id";
    public static final String COLUMN_IS_INCOME = "is_income";

    public static final String DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";
}
