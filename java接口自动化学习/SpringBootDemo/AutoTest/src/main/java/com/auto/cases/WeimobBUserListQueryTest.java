package com.auto.cases;

import com.auto.common.BaseTest;
import com.auto.common.common;
import com.auto.data.DataForB;
import com.auto.model.WeimobActivityCreateCase;
import com.auto.utils.JsonMergeUtil;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class WeimobBUserListQueryTest extends BaseTest {

    @DataProvider(name="userListCaseData")
    public Iterator<WeimobActivityCreateCase> provideDataFromDb() throws IOException {
        Map<String, Object> params = new HashMap<>();
        params.put("action","userQuery");
        return new common().<WeimobActivityCreateCase>provideDataFromDb("weimobActivityCreateCase", WeimobActivityCreateCase.class, params);
    }

    @Test(dataProvider = "userListCaseData")
    public void listWinnerByPage(WeimobActivityCreateCase weimobActivityCreateCase) throws IOException {
        JSONObject requestData = JsonMergeUtil.mergeJson(DataForB.commonParams(weimobActivityCreateCase), DataForB.otherInfo(weimobActivityCreateCase));
        JSONObject responseData = common.getResult(requestData,userlistUrl);

        System.out.println(responseData);
        Assert.assertEquals(responseData.getString("errmsg"),"处理成功");
    }
}
