package com.auto.common;

import com.auto.config.TestConfig;
import com.auto.model.InterfaceName;
import com.auto.model.WeimobInterfaceName;
import com.auto.utils.UrlConfigFile;
import org.testng.annotations.BeforeTest;

public class BaseTest {

    @BeforeTest(description = "测试准备工作，获取httpclient对象")
    public void beforeTest(){
        TestConfig.addUserUrl = UrlConfigFile.getUrl(InterfaceName.ADDUSER);
        TestConfig.getUserInfoUrl = UrlConfigFile.getUrl(InterfaceName.GETUSERINFO);
        TestConfig.loginUrl = UrlConfigFile.getUrl(InterfaceName.LOGIN);
        TestConfig.getUserListUrl = UrlConfigFile.getUrl(InterfaceName.GETUSERLIST);
        TestConfig.updateUserInfoUrl = UrlConfigFile.getUrl(InterfaceName.UPDATEUSERINFO);
        TestConfig.bigwheelCreateId = UrlConfigFile.getWeimobUrl(WeimobInterfaceName.BIGWHEELCREATEID);
        TestConfig.bigwheelCreate = UrlConfigFile.getWeimobUrl(WeimobInterfaceName.BIGWHEELCREATE);
        TestConfig.bigwheelState = UrlConfigFile.getWeimobUrl(WeimobInterfaceName.BIGWHEELSTATE);
        TestConfig.bigwheelUpdate = UrlConfigFile.getWeimobUrl(WeimobInterfaceName.BIGWHEELUPDATE);
        TestConfig.createSnCode = UrlConfigFile.getWeimobUrl(WeimobInterfaceName.CREATESNCODE);
    }
}
