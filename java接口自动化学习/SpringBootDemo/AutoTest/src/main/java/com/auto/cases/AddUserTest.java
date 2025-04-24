package com.auto.cases;

import com.auto.common.BaseTest;
import com.auto.common.common;
import com.auto.config.TestConfig;
import com.auto.model.AddUserCase;
import com.auto.model.GetUserListCase;
import org.json.JSONArray;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import static com.auto.common.common.getResult;

public class AddUserTest extends BaseTest {
    public JSONObject addUserCaseParam(AddUserCase addUserCase){
        JSONObject param = new JSONObject();
        param.put("userName",addUserCase.getUserName());
        param.put("age",addUserCase.getAge());
        param.put("sex",addUserCase.getSex());
        param.put("password",addUserCase.getPassword());
        param.put("permission",addUserCase.getPermission());
        param.put("isDelete",addUserCase.getIsDelete());

        return param;
    }

    //实现从addUserCase表中取数实现接口入参的参数化
    @DataProvider(name = "dbAddUserCaseData")
    public Iterator<AddUserCase> provideGetUserListDataFromDb() throws IOException {
        Map<String, Object> params = new HashMap<>();
        return new common().<AddUserCase>provideDataFromDb("addUserInfoFromCase", AddUserCase.class, params);
    }

    @Test(dataProvider="dbAddUserCaseData",groups = "loginTrue", description = "用户登录成功后，新增用户接口测试")
    public void addUser(AddUserCase addUserCase) throws IOException {
        JSONObject addUserCaseParam = addUserCaseParam(addUserCase);
        JSONObject responseDataWithJson = getResult(addUserCaseParam, TestConfig.addUserUrl);

        Assert.assertEquals(responseDataWithJson.getJSONObject("data")
                .getJSONObject("data")
                .getString("userName"),addUserCase.getUserName());
        Assert.assertEquals(responseDataWithJson.getJSONObject("data")
                .getJSONObject("data")
                .getString("sex"),addUserCase.getSex());
        Assert.assertEquals(responseDataWithJson.getJSONObject("data")
                .getJSONObject("data")
                .getInt("age"),addUserCase.getAge());
        Assert.assertEquals(responseDataWithJson.getJSONObject("data")
                .getJSONObject("data")
                .getString("permission"),addUserCase.getPermission());
        Assert.assertEquals(responseDataWithJson.getJSONObject("data")
                .getJSONObject("data")
                .getString("isDelete"),addUserCase.getIsDelete());
    }
}
