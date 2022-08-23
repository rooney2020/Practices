package com.tsdl.common.util;

import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ShellUtils {

    public static StringBuffer shellExec(String cmd) {
        Runtime mRuntime = Runtime.getRuntime();
        try {
            Process mProcess = mRuntime.exec(cmd);
            BufferedReader mReader = new BufferedReader(new InputStreamReader(mProcess.getInputStream()));
            StringBuffer mRespBuff = new StringBuffer();
            char[] buff = new char[1024];
            int ch = 0;
            while ((ch = mReader.read(buff)) != -1) {
                mRespBuff.append(buff, 0, ch);
            }
            //结束缓冲
            mReader.close();
            Log.i("shell", "shellExec: " + mRespBuff);
            return mRespBuff;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
