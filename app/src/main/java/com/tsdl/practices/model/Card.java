package com.tsdl.practices.model;

import android.content.Context;
import android.view.Gravity;

import androidx.annotation.NonNull;

import com.tsdl.practices.enums.CardColor;
import com.tsdl.common.util.Constants;

public class Card extends androidx.appcompat.widget.AppCompatTextView {

    private static final int TEXT_SIZE = 32;
    private int num = 0;

    public Card(@NonNull Context context) {
        super(context);
        setTextSize(TEXT_SIZE);
        setGravity(Gravity.CENTER);
        setBackgroundColor(CardColor.CARD_0.getBackgroundColor());
        setNum(num);
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
        setText(num <= 0 ? Constants.STRING_EMPTY : String.valueOf(num));
        setBackgroundColor(CardColor.getBackgroundColor(num));
    }
}
