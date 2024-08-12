package com.tsdl.practices.util;

public class TypeUtils {

    public static boolean isEmpty(String src) {
        return src == null || src.trim().isEmpty();
    }

    public static boolean isNotEmpty(String src) {
        return !isEmpty(src);
    }

}
