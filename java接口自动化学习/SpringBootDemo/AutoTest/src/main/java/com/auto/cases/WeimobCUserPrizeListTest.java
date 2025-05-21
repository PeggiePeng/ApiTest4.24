package com.auto.cases;

import com.auto.common.BaseTest;
import com.auto.common.common;
import com.auto.data.DataForB;
import com.auto.data.DataForC;
import com.auto.model.WeimobActivityCreateCase;
import com.auto.model.WeimobActivityPlayCase;
import com.auto.utils.ContextListener;
import com.auto.utils.GlobalVariableUtil;
import com.auto.utils.JsonMergeUtil;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

@Listeners(ContextListener.class)
public class WeimobCUserPrizeListTest extends BaseTest {

    @DataProvider(name="userPrizeListCaseData")
    public Iterator<WeimobActivityPlayCase> provideDataFromDb() throws IOException {
        Map<String, Object> params = new HashMap<>();
        params.put("action","userPrizes");
        return new common().<WeimobActivityPlayCase>provideDataFromDb("weimobActivityPlayCase", WeimobActivityPlayCase.class, params);
    }

    @Test(dataProvider = "userPrizeListCaseData")
    public void listWinnerByPage(WeimobActivityPlayCase weimobActivityPlayCase, ITestContext context) throws IOException {
        try {
            Thread.sleep(5000); // 5秒
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        JSONObject requestData = JsonMergeUtil.mergeJson(DataForC.commonParam(weimobActivityPlayCase), DataForC.playParam(weimobActivityPlayCase));
        JSONObject responseData = common.getResult(requestData,userPrizesUrl);

        //定义局部变量保存用户中奖id
        long userPrizeId=responseData.getJSONObject("data").getJSONArray("data").getJSONObject(0).getLong("userPrizeId");
        long poolId=responseData.getJSONObject("data").getJSONArray("data").getJSONObject(0).getLong("poolId");
        long prizeId=responseData.getJSONObject("data").getJSONArray("data").getJSONObject(0).getLong("id");
        context.setAttribute(weimobActivityPlayCase.getTemplateKey()+"_userPrizeId", userPrizeId);
        context.setAttribute(weimobActivityPlayCase.getTemplateKey()+"_poolId", poolId);
        context.setAttribute(weimobActivityPlayCase.getTemplateKey()+"_prizeId", prizeId);

        Assert.assertEquals(responseData.getString("errmsg"),"处理成功");
    }
}
