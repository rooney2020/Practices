package com.tmec.common.sdk.base;

public enum SkinMode {
    LIGHT(0), NIGHT(1);

    private int code;

    SkinMode(int code) {

    }

    public static SkinMode getNextMode(SkinMode skinMode) {
        if (SkinMode.values().length == 0) {
            return null;
        }
        boolean findFlag = false;
        for (SkinMode mode : SkinMode.values()) {
            if (findFlag) {
                return mode;
            }
            if (mode == skinMode) {
                findFlag = true;
            }
        }
        return SkinMode.values()[0];
    }
}
