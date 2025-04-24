package com.auto.cases;

import com.auto.common.BaseTest;
import com.auto.common.common;
import com.auto.config.TestConfig;
import com.auto.model.GetUserListCase;
import com.auto.model.User;
import org.json.JSONArray;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.*;

import static com.auto.common.common.*;

public class GetUserListTest extends BaseTest {

    public JSONObject setGetUserListParam(GetUserListCase getUserListCase){
        JSONObject param = new JSONObject();
        param.put("userName",getUserListCase.getUserName());
        param.put("age",getUserListCase.getAge());
        param.put("sex",getUserListCase.getSex());

        return param;
    }

    //实现从GetUserListCase表中取数实现接口入参的参数化
    @DataProvider(name = "dbGetUserListCaseData")
    public Iterator<GetUserListCase> provideGetUserListDataFromDb() throws IOException {
        Map<String, Object> params = new HashMap<>();
        return new common().<GetUserListCase>provideDataFromDb("getUserListFromCase", GetUserListCase.class, params);
    }

    //通过连接数据库查询实际数据与接口返回数据做比对进行断言
    @Test(dataProvider = "dbGetUserListCaseData", groups = "loginTrue", description = "用户登录成功后，查询用户列表接口测试")
    public void getUserList(GetUserListCase getUserListCase) throws IOException {
        JSONObject userListCaseParamsWithJson = setGetUserListParam(getUserListCase);
        Map<String, Object> userListCaseParamsWithMap = jsonToMap(userListCaseParamsWithJson);

        //根据getUserListCase中的参数，调接口传参进行查询，得出实际结果
        JSONObject actualResponse = getResult(userListCaseParamsWithJson, TestConfig.getUserListUrl);
        JSONArray actualResponsePageList = actualResponse.getJSONObject("data").getJSONArray("pageList");

        //根据根据getUserListCase中的参数，从User表中查询期望结果
        List<User> exceptedResponse = selectExpectedData("getUserList", User.class, userListCaseParamsWithMap);

        //进行断言：按照case中的条件，数据库中实际查询出的条数与接口返回的条数一致
        Assert.assertEquals(actualResponse.getJSONObject("data").getInt("totalCount"), exceptedResponse.size());

        if(getUserListCase.getUserName()!=null){
            if(!actualResponsePageList.isEmpty()) {
                for (int i = 0; i < actualResponsePageList.length(); i++) {
                    JSONObject jsonObject = actualResponsePageList.getJSONObject(i);
                    String userName = jsonObject.getString("userName");
                    System.out.println("userName:"+userName+", expectUserName:"+getUserListCase.getUserName());
                    Assert.assertEquals(userName, getUserListCase.getUserName());
                }
            }
        }if(getUserListCase.getSex()!=null){
            if(!actualResponsePageList.isEmpty()) {
                for (int i = 0; i < actualResponsePageList.length(); i++) {
                    JSONObject jsonObject = actualResponsePageList.getJSONObject(i);
                    String sex = jsonObject.getString("sex");
                    System.out.println("sex:"+sex+", expectSex:"+getUserListCase.getSex());
                    Assert.assertEquals(sex, getUserListCase.getSex());
                }
            }
        }if(getUserListCase.getAge()!=null){
            if(!actualResponsePageList.isEmpty()) {
                for (int i = 0; i < actualResponsePageList.length(); i++) {
                    JSONObject jsonObject = actualResponsePageList.getJSONObject(i);
                    int age = jsonObject.getInt("age");
                    System.out.println("age:"+age+", expectAge:"+getUserListCase.getAge());
                    Assert.assertEquals(age, getUserListCase.getAge());
                }
            }
        }

        System.out.println("-------------------------------------------------------------");
    }
}
