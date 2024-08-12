package com.tsdl.practices.util;

import android.util.Log;

public class HttpCallback implements HttpCallbackListener {

    @Override
    public void onFinish(Object response) {
        Log.i("HttpResponse", String.valueOf(response));
    }

    @Override
    public void onError(Exception e) {
        Log.e("HttpError", e.toString());
    }

}
