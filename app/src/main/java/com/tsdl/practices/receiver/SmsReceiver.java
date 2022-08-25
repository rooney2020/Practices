package com.tsdl.practices.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.telephony.gsm.SmsMessage;
import android.util.Log;

import com.tsdl.common.entity.Bill;
import com.tsdl.common.util.CommonUtils;
import com.tsdl.common.util.LogUtils;
import com.tsdl.practices.manager.DataManager;

public class SmsReceiver extends BroadcastReceiver {

    public static final String TAG = "ImiChatSMSReceiver";
    public static final String SMS_RECEIVED_ACTION = "android.provider.Telephony.SMS_RECEIVED";
    public static final String KEYWORD_BANK_1 = "[建设银行]";
    public static final String KEYWORD_BANK_2 = "【招商银行】";
    public static final String KEYWORD_TYPE_INCOME_1 = "存入人民币";
    public static final String KEYWORD_TYPE_OUTCOME_1 = "支出人民币";
    public static final String KEYWORD_TYPE_INCOME_2 = "入账";
    public static final String KEYWORD_TYPE_INCOME_3 = "实时转入";
    public static final String KEYWORD_TYPE_OUTCOME_2 = "扣款";
    public static final String KEYWORD_TYPE_OUTCOME_3 = "实时转至他行";
    public static final String KEYWORD_AMOUNT = "人民币";
    public static final String KEYWORD_AMOUNT_END_1 = "元,";
    public static final String KEYWORD_AMOUNT_END_2 = "，";
    public static final int DEFAULT_OUTCOME_TYPE = 7;
    public static final int DEFAULT_INCOME_TYPE = 22;

    @Override
    public void onReceive(Context context, Intent intent) {
        if (intent.getAction().equals(SMS_RECEIVED_ACTION)) {
            SmsMessage[] messages = getMessagesFromIntent(intent);
            for (SmsMessage message : messages) {
                Log.i(TAG, message.getOriginatingAddress() + " : " +
                        message.getDisplayOriginatingAddress() + " : " +
                        message.getDisplayMessageBody() + " : " +
                        message.getTimestampMillis());
                String messageBody = message.getDisplayMessageBody();
                parseMessage(context, messageBody);
            }
        }
    }

    public static void parseMessage(Context context, String messageBody) {
        if (messageBody == null || messageBody.isEmpty()) {
            LogUtils.logE(TAG, "Message parse error! Message body is null. Message body is : " + messageBody);
            return;
        }
        boolean isIncome = false;
        float amount = Integer.MIN_VALUE;

        if (messageBody.contains(KEYWORD_BANK_1) || messageBody.contains(KEYWORD_BANK_2)) {
            boolean isBank1 = messageBody.contains(KEYWORD_BANK_1);
            if (messageBody.contains(KEYWORD_TYPE_INCOME_1) || messageBody.contains(KEYWORD_TYPE_INCOME_2)
                    || messageBody.contains(KEYWORD_TYPE_INCOME_3)) {
                isIncome = true;
            } else if (!messageBody.contains(KEYWORD_TYPE_OUTCOME_1) && !messageBody.contains(KEYWORD_TYPE_OUTCOME_2)
                    && !messageBody.contains(KEYWORD_TYPE_OUTCOME_3)) {
                LogUtils.logE(TAG, "Message parse error! Income type is null. Message body is : " + messageBody);
                return;
            }
            String[] strings = messageBody.split(KEYWORD_AMOUNT);
            if (strings.length > 1) {
                String[] results = strings[1].split(isBank1 ? KEYWORD_AMOUNT_END_1 : KEYWORD_AMOUNT_END_2);
                if (results.length > 1) {
                    amount = isIncome ? Float.parseFloat(results[0]) : -Float.parseFloat(results[0]);
                    LogUtils.logE(TAG, "Message parse success! Amount is : " + amount);
                } else {
                    LogUtils.logE(TAG, "Message parse error! KEYWORD_AMOUNT_END is null. Message body is : " + messageBody);
                    return;
                }
            } else {
                LogUtils.logE(TAG, "Message parse error! KEYWORD_AMOUNT is null. Message body is : " + messageBody);
                return;
            }
        } else {
            LogUtils.logE(TAG, "Message parse error! It is not allow Bank. Message body is : " + messageBody);
            return;
        }
        Bill bill = new Bill(isIncome ? DEFAULT_INCOME_TYPE : DEFAULT_OUTCOME_TYPE, amount, "短信监听", CommonUtils.getTime());
        DataManager.getsInstance(context).insertBill(bill);
    }

    public final SmsMessage[] getMessagesFromIntent(Intent intent) {
        Object[] messages = (Object[]) intent.getSerializableExtra("pdus");
        byte[][] pduObjs = new byte[messages.length][];

        for (int i = 0; i < messages.length; i++) {
            pduObjs[i] = (byte[]) messages[i];
        }
        byte[][] pdus = new byte[pduObjs.length][];
        int pduCount = pdus.length;
        SmsMessage[] msgs = new SmsMessage[pduCount];
        for (int i = 0; i < pduCount; i++) {
            pdus[i] = pduObjs[i];
            msgs[i] = SmsMessage.createFromPdu(pdus[i]);
        }
        return msgs;
    }
}