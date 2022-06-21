package com.tsdl.practices.enums;

public enum CardColor {
    CARD_UNDEFINED(-1, 0xff3c3a32),
    CARD_0(0, 0x33ffffff),
    CARD_2(2, 0xffeee4da),
    CARD_4(4, 0xffede0c8),
    CARD_8(8, 0xfff2b179),
    CARD_16(16, 0xfff59563),
    CARD_32(32, 0xfff67c5f),
    CARD_64(64, 0xfff65e3b),
    CARD_128(128, 0xffedcf72),
    CARD_256(256, 0xffedcc61),
    CARD_512(512, 0xffedc850),
    CARD_1024(1024, 0xffedc53f),
    CARD_2048(2048, 0xffedc22e);

    private int num;
    private int backgroundColor;

    CardColor(int num, int backgroundColor) {
        this.num = num;
        this.backgroundColor = backgroundColor;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public static int getBackgroundColor(int num) {
        for (CardColor cardColor : CardColor.values()) {
            if (num == cardColor.getNum()) {
                return cardColor.getBackgroundColor();
            }
        }
        return CARD_UNDEFINED.getBackgroundColor();
    }

    public int getBackgroundColor() {
        return backgroundColor;
    }

    public void setBackgroundColor(int backgroundColor) {
        this.backgroundColor = backgroundColor;
    }
}