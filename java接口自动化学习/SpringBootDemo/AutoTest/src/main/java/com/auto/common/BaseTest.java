package com.auto.common;

import com.auto.config.TestConfig;
import com.auto.model.InterfaceName;
import com.auto.model.WeimobInterfaceName;
import com.auto.utils.DatabaseUtil;
import com.auto.utils.UrlConfigFile;
import org.apache.http.client.CookieStore;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.ibatis.session.SqlSession;
import org.testng.annotations.BeforeTest;

import java.io.IOException;

public class BaseTest {
    public static String createIdUrl;
    public static String createUrl;
    public static String stateUrl;
    public static String updateUrl;
    public static String createSnCodeUrl;
    public static String playUrl;
    public static String listWinnerByPageUrl;
    public static String userlistUrl;

    public static CookieStore store = new BasicCookieStore();
    public static CloseableHttpClient httpClient= HttpClients.custom().setDefaultCookieStore(store).build();

    public static SqlSession session;
    static {
        try {
            session = DatabaseUtil.getSqlSession();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @BeforeTest(description = "测试准备工作，初始化接口url")
    public void beforeTest(){
        TestConfig.addUserUrl = UrlConfigFile.getUrl(InterfaceName.ADDUSER);
        TestConfig.getUserInfoUrl = UrlConfigFile.getUrl(InterfaceName.GETUSERINFO);
        TestConfig.loginUrl = UrlConfigFile.getUrl(InterfaceName.LOGIN);
        TestConfig.getUserListUrl = UrlConfigFile.getUrl(InterfaceName.GETUSERLIST);
        TestConfig.updateUserInfoUrl = UrlConfigFile.getUrl(InterfaceName.UPDATEUSERINFO);
        createIdUrl = UrlConfigFile.getWeimobUrl(WeimobInterfaceName.CREATEID);
        createUrl = UrlConfigFile.getWeimobUrl(WeimobInterfaceName.CREATE);
        stateUrl = UrlConfigFile.getWeimobUrl(WeimobInterfaceName.STATE);
        updateUrl = UrlConfigFile.getWeimobUrl(WeimobInterfaceName.UPDATE);
        createSnCodeUrl = UrlConfigFile.getWeimobUrl(WeimobInterfaceName.CREATESNCODE);
        playUrl = UrlConfigFile.getWeimobUrl(WeimobInterfaceName.PLAY);
        listWinnerByPageUrl = UrlConfigFile.getWeimobUrl(WeimobInterfaceName.LISTWINNERBYPAGE);
        userlistUrl = UrlConfigFile.getWeimobUrl(WeimobInterfaceName.USERQUERY);
    }
}
