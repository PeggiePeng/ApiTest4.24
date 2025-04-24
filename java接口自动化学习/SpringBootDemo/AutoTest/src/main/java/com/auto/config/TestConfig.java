package com.auto.config;

import org.apache.http.client.CookieStore;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

public class TestConfig {
    public static String loginUrl;
    public static String updateUserInfoUrl;
    public static String getUserListUrl;
    public static String getUserInfoUrl;
    public static String addUserUrl;
    public static String bigwheelCreateId;
    public static String bigwheelCreate;
    public static String bigwheelState;
    public static String bigwheelUpdate;
    public static String createSnCode;

    public static CookieStore store = new BasicCookieStore();
    public static CloseableHttpClient httpClient= HttpClients.custom().setDefaultCookieStore(store).build();
}
