package com.tsdl.practices.util;

import com.tsdl.practices.config.WebConfig;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;

public class HttpUtils {

    private static final String TAG = HttpUtils.class.getSimpleName();

    public static final int SUCCESS_CODE = 200;
    public static final int EXPIRE_CODE = 401;
    public static final int ERROR_CODE = 500;

    public static boolean isSuccess(int code) {
        return code == SUCCESS_CODE;
    }

    public static boolean isExpire(int code) {
        return code == EXPIRE_CODE;
    }

    public static void get(final String address, String body, final HttpCallbackListener listener) {
        sendHttpRequest(address, METHOD.GET, body, ShareUtils.getString(ShareUtils.SP_KEY_TOKEN), listener);
    }

    public static void post(final String address, String body, final HttpCallbackListener listener) {
        sendHttpRequest(address, METHOD.POST, body, ShareUtils.getString(ShareUtils.SP_KEY_TOKEN), listener);
    }

    public static void put(final String address, String body, final HttpCallbackListener listener) {
        sendHttpRequest(address, METHOD.PUT, body, ShareUtils.getString(ShareUtils.SP_KEY_TOKEN), listener);
    }

    public static void delete(final String address, String body, final HttpCallbackListener listener) {
        sendHttpRequest(address, METHOD.DELETE, body, ShareUtils.getString(ShareUtils.SP_KEY_TOKEN), listener);
    }

    public static void get(final String address, String body, String token, final HttpCallbackListener listener) {
        sendHttpRequest(address, METHOD.GET, body, token, listener);
    }

    public static void post(final String address, String body, String token, final HttpCallbackListener listener) {
        sendHttpRequest(address, METHOD.POST, body, token, listener);
    }

    public static void put(final String address, String body, String token, final HttpCallbackListener listener) {
        sendHttpRequest(address, METHOD.PUT, body, token, listener);
    }

    public static void delete(final String address, String body, String token, final HttpCallbackListener listener) {
        sendHttpRequest(address, METHOD.DELETE, body, token, listener);
    }

    public static void sendHttpRequest(final String address, METHOD method, String body, String token,
                                       final HttpCallbackListener listener) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                HttpURLConnection connection = null;
                try {
                    URL url = new URL(WebConfig.getRequestUrl(address));
                    connection = (HttpURLConnection) url.openConnection();
                    connection.setRequestMethod(method.name());
                    connection.setConnectTimeout(8000);
                    connection.setReadTimeout(8000);
                    connection.setDoInput(true);
                    connection.setDoOutput(true);

//                    connection.setRequestProperty("Content-Type", "application/json;charset=utf-8");
                    if (TypeUtils.isNotEmpty(token)) {
                        connection.setRequestProperty("token", token);
                    }
                    if (METHOD.POST.equals(method)) {
                        BufferedWriter writer = new BufferedWriter(
                                new OutputStreamWriter(connection.getOutputStream(), StandardCharsets.UTF_8));
                        writer.write(body);
                        writer.close();
                    }

                    InputStream in = connection.getInputStream();
                    BufferedReader reader = new BufferedReader(new InputStreamReader(in));
                    StringBuilder response = new StringBuilder();
                    String line;
                    while ((line = reader.readLine()) != null) {
                        response.append(line);
                    }
                    if (listener != null) {
                        // 回调onFinish()方法
                        listener.onFinish(response.toString());
                    }
                } catch (Exception e) {
                    if (listener != null) {
                        // 回调onError()方法
                        listener.onError(e);
                    }
                } finally {
                    if (connection != null) {
                        connection.disconnect();
                    }
                }
            }
        }).start();
    }

    public static enum METHOD {
        GET("GET"), POST("POST"), PUT("PUT"), DELETE("DELETE");

        METHOD(String name) {

        }
    }
}