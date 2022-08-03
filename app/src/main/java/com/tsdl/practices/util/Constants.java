package com.tsdl.practices.util;

import com.tsdl.practices.R;

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
    public static final List<Integer> PAGE_TITLE_LIST = Arrays.asList(R.string.fragment_home,
            R.string.fragment_asset, R.string.fragment_mine);
    public static final List<Integer> PAGE_ACTIVE_ICON_LIST = Arrays.asList(R.drawable.icon_home_active,
            R.drawable.icon_asset_active, R.drawable.icon_mine_active);
    public static final List<Integer> PAGE_INACTIVE_ICON_LIST = Arrays.asList(R.drawable.icon_home_inactive,
            R.drawable.icon_asset_inactive, R.drawable.icon_mine_inactive);
}
