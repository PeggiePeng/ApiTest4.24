package com.auto.cases;

import com.auto.common.BaseTest;
import com.auto.common.common;
import com.auto.config.TestConfig;
import com.auto.model.LoginCase;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import static com.auto.common.common.getResult;

public class LoginTest extends BaseTest {

    public JSONObject setLoginParam(LoginCase loginCase){
        JSONObject param = new JSONObject();
        param.put("userName",loginCase.getUserName());
        param.put("password",loginCase.getPassword());

        return param;
    }

    //实现数据库取表取数参数化
    @DataProvider(name = "dbTrueData")
    public Iterator<LoginCase> provideLoginTrueDataFromDb() throws IOException {
        Map<String, Object> params = new HashMap<>();
        params.put("excepted", "true");
        return new common().<LoginCase>provideDataFromDb("loginCase", LoginCase.class, params);
    }

    @DataProvider(name = "dbFalseData")
    public Iterator<LoginCase> provideLoginFalseDataFromDb() throws IOException {
        Map<String, Object> params = new HashMap<>();
        params.put("excepted", "false");
        return new common().<LoginCase>provideDataFromDb("loginCase", LoginCase.class, params);
    }

    @Test(dataProvider = "dbTrueData", groups = "loginTrue", description = "用户登录成功接口测试")
    public void loginTrue(LoginCase loginCase) throws IOException {
        JSONObject actualResult;
        actualResult = getResult(setLoginParam(loginCase), TestConfig.loginUrl);
        System.out.println("login-true:  actualResult is "+actualResult+", param is:"+setLoginParam(loginCase));
        Assert.assertEquals(JSONObject.valueToString(actualResult), loginCase.getExcepted());
    }

    @Test(dataProvider = "dbFalseData", groups = "loginFalse", description = "用户登录成功接口测试")
    public void loginFalse(LoginCase loginCase) throws IOException {
        JSONObject actualResult;
        actualResult = getResult(setLoginParam(loginCase), TestConfig.loginUrl);
        System.out.println("login-false:  actualResult is "+actualResult+", param is:"+setLoginParam(loginCase));
        Assert.assertEquals(JSONObject.valueToString(actualResult), loginCase.getExcepted());
    }
}
