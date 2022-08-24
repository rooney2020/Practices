/*
 * Copyright © 2019-Now Human Horizons (Shanghai) Cloud Computing Technology Co., Ltd. All
 * Rights Reserved.
 */
package com.tmec.common.sdk.tab;

/**
 * author : xianqingzhang
 * e-mail : Xianqing_Zhang@human-horizons.com
 * time   : 19-12-27
 * desc   : This is SoundEffectEnum
 * version: 1.0
 */
public enum SoundEffectEnum {
    /**
     * HH widget
     */
    FX_KEY_NORMAL_REAL_BTN(0),
    FX_KEY_NORMAL_ICON_BTN(0),
    FX_KEY_NORMAL_GHOST_BTN(0),
    FX_KEY_NORMAL_FLAT_BTN(0),
    FX_KEY_NORMAL_TOGGLE_BTN(0),
    FX_KEY_NORMAL_DIALOG(0),
    FX_KEY_NORMAL_POP(0),
    FX_KEY_NORMAL_TAB(0),
    FX_KEY_NORMAL_SEGMENT(0),
    FX_KEY_NORMAL_PICK(0),
    FX_KEY_NORMAL_EDIT(0),
    FX_KEY_NORMAL_SWIP_FRESH_LY(0),
    FX_KEY_NORMAL_CHECK_BOX(0),
    FX_KEY_NORMAL_SWITCH(0),
    /**
     * android widget
     */
    FX_KEY_CLICK(0),
    FX_KEY_TV(0),
    FX_KEY_IMAGE(0),
    FX_KEY_CHECK_BOX(0),
    FX_KEY_SWITCH(0),
    FX_KEY_IMG_BTN(0),
    FX_KEY_RADIO_BTN(0),
    FX_KEY_3D_BTN(0),
    FX_KEY_CUSTOM_VIEW(0),
    /**
     * third sound type
     */
    FX_SFK_SUCCESS(0),
    FX_SFK_FAIL(0),
    FX_SFK_CALLING_ONE(0),
    FX_SFK_CALLING_TWO(0),
    FX_SFK_CALLING_THREE(0),
    FX_SFK_CALLING_FOUR(0),
    FX_SFK_CALLING_FIVE(0),
    FX_SFK_CALLING_SIX(0),
    FX_SFK_CALLING_SEVEN(0),
    FX_SFK_CALLING_EIGHT(0),
    FX_SFK_CALLING_NINE(0),
    FX_SFK_CALLING_TEN(0),
    FX_SFK_STAR(0),
    FX_SFK_NUMBER_SIGN(0),
    FX_SFK_CONFIRM(0),
    FX_SFK_DELET(0),
    FX_SFK_AML(0),
    FX_SFK_CALLING_INCOMMING(0),
    FX_SFK_CAMERA(0),
    FX_SFK_IROBOT_ONE(0),
    FX_SFK_IROBOT_TWO(0),
    FX_SFK_SCENE_TWO(0),
    FX_SFK_SCENE_ONE(0),
    FX_SFK_IROBOT_THREE(0),
    FX_SFK_SYS_WAR_H(0),
    FX_SFK_SYS_WAR_M(0),
    FX_SFK_SYS_WAR_L(0),
    FX_SFK_VOICE_RECOG_ONE(0),
    FX_SFK_VOICE_RECOG_TWO(0),
    FX_SFK_WAKEUP(0),
    FX_KEY_NOTIFICATION(0);


    private int value = 0;

    SoundEffectEnum(int value) {
        this.value = value;
    }

    public int value() {
        return this.value;
    }

    public static SoundEffectEnum valueOf(int value) {
        switch (value) {
            // CHECKSTYLE.OFF: MagicNumber
            case 0:
                return FX_KEY_CLICK;
            case 10:
                return FX_KEY_NORMAL_REAL_BTN;
            case 11:
                return FX_KEY_NORMAL_ICON_BTN;
            case 12:
                return FX_KEY_NORMAL_GHOST_BTN;
            case 13:
                return FX_KEY_NORMAL_FLAT_BTN;
            case 14:
                return FX_KEY_NORMAL_TOGGLE_BTN;
            case 15:
                return FX_KEY_NORMAL_DIALOG;
            case 16:
                return FX_KEY_NORMAL_POP;
            case 17:
                return FX_KEY_NORMAL_TAB;
            case 18:
                return FX_KEY_NORMAL_SEGMENT;
            case 19:
                return FX_KEY_NORMAL_PICK;
            case 20:
                return FX_KEY_NORMAL_EDIT;
            case 21:
                return FX_KEY_NORMAL_SWIP_FRESH_LY;
            case 22:
                return FX_KEY_NORMAL_CHECK_BOX;
            case 23:
                return FX_KEY_NORMAL_SWITCH;
            case 24:
                return FX_KEY_TV;
            case 25:
                return FX_KEY_IMAGE;
            case 26:
                return FX_KEY_CHECK_BOX;
            case 27:
                return FX_KEY_SWITCH;
            case 28:
                return FX_KEY_IMG_BTN;
            case 29:
                return FX_KEY_RADIO_BTN;
            case 30:
                return FX_KEY_3D_BTN;
            case 31:
                return FX_KEY_CUSTOM_VIEW;
            case 32:
                return FX_SFK_SUCCESS;
            case 33:
                return FX_SFK_FAIL;
            case 34:
                return FX_SFK_CALLING_ONE;
            case 35:
                return FX_SFK_CALLING_TWO;
            case 36:
                return FX_SFK_CALLING_THREE;
            case 37:
                return FX_SFK_CALLING_FOUR;
            case 38:
                return FX_SFK_CALLING_FIVE;
            case 39:
                return FX_SFK_CALLING_SIX;
            case 40:
                return FX_SFK_CALLING_SEVEN;
            case 41:
                return FX_SFK_CALLING_EIGHT;
            case 42:
                return FX_SFK_CALLING_NINE;
            case 43:
                return FX_SFK_CALLING_TEN;
            case 44:
                return FX_SFK_STAR;
            case 45:
                return FX_SFK_NUMBER_SIGN;
            case 46:
                return FX_SFK_CONFIRM;
            case 47:
                return FX_SFK_DELET;
            case 48:
                return FX_SFK_AML;
            case 49:
                return FX_SFK_CALLING_INCOMMING;
            case 50:
                return FX_SFK_CAMERA;
            case 51:
                return FX_SFK_IROBOT_ONE;
            case 52:
                return FX_SFK_IROBOT_TWO;
            case 53:
                return FX_SFK_SCENE_TWO;
            case 54:
                return FX_SFK_SCENE_ONE;
            case 55:
                return FX_SFK_IROBOT_THREE;
            case 56:
                return FX_SFK_SYS_WAR_H;
            case 57:
                return FX_SFK_SYS_WAR_M;
            case 58:
                return FX_SFK_SYS_WAR_L;
            case 59:
                return FX_SFK_VOICE_RECOG_ONE;
            case 60:
                return FX_SFK_VOICE_RECOG_TWO;
            case 61:
                return FX_SFK_WAKEUP;
            case 62:
                return FX_KEY_NOTIFICATION;
            // CHECKSTYLE.ON: MagicNumber
            default:
                return null;
        }
    }
}
