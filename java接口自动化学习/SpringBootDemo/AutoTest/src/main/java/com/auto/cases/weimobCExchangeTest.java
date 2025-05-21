package com.auto.cases;

import com.auto.common.BaseTest;
import com.auto.common.common;
import com.auto.data.DataForC;
import com.auto.model.WeimobActivityPlayCase;
import com.auto.utils.ContextListener;
import com.auto.utils.JsonMergeUtil;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class weimobCExchangeTest extends BaseTest {

    @DataProvider(name="playCaseData")
    public Iterator<WeimobActivityPlayCase> provideDataFromDb() throws IOException {
        Map<String, Object> params = new HashMap<>();
        params.put("action","exchange");
        return new common().<WeimobActivityPlayCase>provideDataFromDb("weimobActivityPlayCase", WeimobActivityPlayCase.class, params);
    }

    @Test(dataProvider = "playCaseData",dependsOnMethods = "com.auto.cases.WeimobCUserPrizeListTest.listWinnerByPage")
    public void exchange(WeimobActivityPlayCase weimobActivityPlayCase) throws IOException {

        ITestContext context = ContextListener.getContext();
        String otherInfo = common.replacePlaceholdersfromITestContext(DataForC.playParam(weimobActivityPlayCase),context);

        JSONObject requestData = JsonMergeUtil.mergeJson(DataForC.commonParam(weimobActivityPlayCase), otherInfo);
        JSONObject responseData = common.getResult(requestData,exchangeUrl);

        Assert.assertEquals(responseData.getString("errmsg"),"success");
    }
}
