package com.auto.cases;

import com.auto.common.BaseTest;
import com.auto.common.common;
import com.auto.data.DataForB;
import com.auto.data.DataForC;
import com.auto.model.WeimobActivityCreateCase;
import com.auto.model.WeimobActivityPlayCase;
import com.auto.utils.JsonMergeUtil;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class WeimobActivityUserPrizeListTest extends BaseTest {

    @DataProvider(name="userPrizeListCaseData")
    public Iterator<WeimobActivityCreateCase> provideDataFromDb() throws IOException {
        Map<String, Object> params = new HashMap<>();
        params.put("action","prizeListQuery");
        return new common().<WeimobActivityCreateCase>provideDataFromDb("weimobActivityCreateCase", WeimobActivityCreateCase.class, params);
    }

    @Test(dataProvider = "userPrizeListCaseData")
    public void listWinnerByPage(WeimobActivityCreateCase weimobActivityCreateCase) throws IOException {
        JSONObject requestData = JsonMergeUtil.mergeJson(DataForB.commonParams(weimobActivityCreateCase), DataForB.otherInfo(weimobActivityCreateCase));
        JSONObject responseData = common.getResult(requestData,userlistUrl);

        Assert.assertEquals(responseData.getString("errmsg"),"处理成功");
    }
}
