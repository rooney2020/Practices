package com.tsdl.practices.config;

public class WebConfig {

    private static String BASE_URL = "";
    // 系统基础地址
    private final static String HTTP_PREFIX = "http://";
//    private final static String MODULE_NAME = "/renren-fast";
    // 静态文件地址
    public final static String URL_PROFILE = "/profile/";
    // 验证码图片地址
    public final static String URL_CAPTCHA = "/code";
    // 系统登录接口
    public final static String URL_LOGIN = "/auth/login";
    // 食谱列表接口
    public final static String URL_DISH_LIST = "/food/dish/list";
    public final static String URL_TYPE_LIST = "/food/type/list";

    public static void setBaseUrl(String baseUrl) {
        BASE_URL = HTTP_PREFIX + baseUrl;
    }

    public static String getBaseUrl() {
        return BASE_URL;
    }

    public static String getRequestUrl(String url) {
        return BASE_URL + url;
    }
}
