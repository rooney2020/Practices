package com.tsdl.common.util;

import android.annotation.SuppressLint;

import java.text.SimpleDateFormat;
import java.util.Date;

public class CommonUtils {

    @SuppressLint({"SimpleDateFormat", "WeekBasedYear"})
    public static String getTime() {
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat(Constants.DATE_FORMAT);
        return sdf.format(date);
    }
}
