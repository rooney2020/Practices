package com.tsdl.practices.model;

import androidx.annotation.NonNull;

import com.tsdl.practices.exception.CalculateException;
import com.tsdl.practices.util.Constants;
import com.tsdl.practices.util.LogUtils;

import java.util.ArrayList;
import java.util.List;

public class Calculator {
    public static final String TAG = Calculator.class.getName();
    List<String> dataList = new ArrayList<>();
    String originalData;
    String calculateResult = Constants.STRING_EMPTY;

    public Calculator(String express) {
        originalData = express;
        int lastIndex = 0;
        boolean hasMinus = false;
        for (int i = 0; i < express.length(); i++) {
            if (Constants.CHARACTERS_SPLIT.contains(String.valueOf(express.charAt(i)))) {
                if (i > lastIndex) {
                    String subString = express.substring(lastIndex, i);
                    LogUtils.logD(TAG, "sub=" + subString + " lastIndex=" + lastIndex);
                    dataList.add(hasMinus ? Constants.KEY_VALUE_MINUS + subString : subString);
                    hasMinus = false;
                }
                lastIndex = i + 1;
                dataList.add(String.valueOf(express.charAt(i)));
            } else if (Constants.KEY_VALUE_MINUS.equals(String.valueOf(express.charAt(i)))) {
                if (i > lastIndex) {
                    String subString = express.substring(lastIndex, i);
                    dataList.add(hasMinus ? Constants.KEY_VALUE_MINUS + subString : subString);
                    hasMinus = false;
                }
                lastIndex = i + 1;
                dataList.add(Constants.KEY_VALUE_PLUS);
                hasMinus = true;
            } else if (Constants.KEY_VALUE_PERCENT.equals(String.valueOf(express.charAt(i)))) {
                try {
                    if (i > lastIndex) {
                        String subString = express.substring(lastIndex, i);
                        Double value = Double.valueOf(subString);
                        dataList.add(String.valueOf(hasMinus ? Constants.KEY_VALUE_MINUS + (value / 100) : value / 100));
                        hasMinus = false;
                        lastIndex = i + 1;
                    }
                } catch (Exception exception) {
                    exception.printStackTrace();
                    throw new CalculateException();
                }
            } else if (i == express.length() - 1) {
                if (i >= lastIndex) {
                    String subString = express.substring(lastIndex);
                    dataList.add(hasMinus ? Constants.KEY_VALUE_MINUS + subString : subString);
                    hasMinus = false;
                }
            }
        }
        LogUtils.logD(TAG, toString());
    }

    public String calculate() {
        if (Constants.STRING_EMPTY.equals(originalData)) {
            return Constants.STRING_EMPTY;
        }
        for (int i = 0; i < originalData.length(); i++) {
            char ch = originalData.charAt(i);
            String data = String.valueOf(ch);
            if (!Constants.CHARACTERS.contains(data) && (ch < 48 || ch > 57)) {
                return Constants.DISPLAY_ERROR;
            }
        }
        calculateInner(new ArrayList<>(dataList));
        String result = Constants.STRING_EMPTY.equals(calculateResult) ? toString() : calculateResult;
        if (result.length() > 10) {
            String head = result.substring(0, 1);
            String mid = result.substring(1, 11);
            String tail = "E" + (result.length() - 1);
            return head + "." + mid + tail;
        }
        return result;
    }

    public void calculateInner(List<String> express) {
        if (express.size() == 1) {
            calculateResult = express.get(0);
            return;
        }
        for (int i = 0; i < express.size(); i++) {
            if (Constants.CHARACTERS_HIGH_PRIOR.contains(express.get(i))) {
                if (i == 0 || i == express.size() - 1) {
                    throw new CalculateException();
                }
                try {
                    Double left = Double.valueOf(express.get(i - 1));
                    Double right = Double.valueOf(express.get(i + 1));
                    double value = 0;
                    if (Constants.KEY_VALUE_DIVIDE.equals(express.get(i))) {
                        value = left / right;
                    } else {
                        value = left * right;
                    }
                    List<String> list = express;
                    list.add(i, String.valueOf(value));
                    list.remove(i + 1);
                    list.remove(i + 1);
                    list.remove(i - 1);
                    calculateInner(list);
                    break;
                } catch (Exception exception) {
                    exception.printStackTrace();
                }
            }
        }
        for (int i = 0; i < express.size(); i++) {
            if (Constants.KEY_VALUE_PLUS.equals(express.get(i))) {
                if (i == 0 || i == express.size() - 1) {
                    throw new CalculateException();
                }
                try {
                    Double left = Double.valueOf(express.get(i - 1));
                    Double right = Double.valueOf(express.get(i + 1));
                    double value = left + right;
                    List<String> list = express;
                    list.add(i, String.valueOf(value));
                    list.remove(i + 1);
                    list.remove(i + 1);
                    list.remove(i - 1);
                    calculateInner(list);
                    break;
                } catch (Exception exception) {
                    exception.printStackTrace();
                }
            }
        }
    }

    @NonNull
    @Override
    public String toString() {
        StringBuilder str = new StringBuilder();
        for (String data : dataList) {
            str.append(data).append(" ");
        }
        return str.toString();
    }
}
