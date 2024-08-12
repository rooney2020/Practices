package com.tsdl.practices.util;

public interface HttpCallbackListener {
    void onFinish(Object response);

    void onError(Exception e);
}