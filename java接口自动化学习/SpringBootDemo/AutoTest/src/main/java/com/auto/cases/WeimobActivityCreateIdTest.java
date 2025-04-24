package com.auto.cases;

import com.alibaba.fastjson.JSON;
import com.auto.common.BaseTest;
import com.auto.common.common;
import com.auto.config.TestConfig;
import com.auto.model.AddUserCase;
import com.auto.model.WeimobActivityCreateIdCase;
import com.auto.utils.GlobalVariableUtil;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import static com.auto.common.common.getResult;

public class WeimobActivityCreateIdTest extends BaseTest {

    public JSONObject activityCreateIdCaseParams(WeimobActivityCreateIdCase weimobActivityCreateIdCase){
        JSONObject params = new JSONObject();
        params.put("templateKey", weimobActivityCreateIdCase.getTemplateKey());
        params.put("basicInfo", JSON.parseObject(weimobActivityCreateIdCase.getBasicInfo()));
        params.put("i18n", JSON.parseObject(weimobActivityCreateIdCase.getI18n()));
        params.put("extendInfo", JSON.parseObject(weimobActivityCreateIdCase.getExtendInfo()));

        return params;
    }

    @DataProvider(name="activityCreateIdCaseData")
    public Iterator<WeimobActivityCreateIdCase> provideDateFromDb() throws IOException {
        Map<String, Object> params = new HashMap<>();
        return new common().<WeimobActivityCreateIdCase>provideDataFromDb("weimobActivityCreateIdCase", WeimobActivityCreateIdCase.class, params);
    }

    @Test(dataProvider = "activityCreateIdCaseData")
    public void activityCreateId(WeimobActivityCreateIdCase weimobActivityCreateIdCase) throws IOException {
        JSONObject activityCreateIdParam = activityCreateIdCaseParams(weimobActivityCreateIdCase);
        JSONObject responseData = getResult(activityCreateIdParam, TestConfig.bigwheelCreateId);

        String activityId = responseData.getJSONObject("data").getString("activityId");
        long poolId = responseData.getJSONObject("data").getLong("poolId");
        if(weimobActivityCreateIdCase.getTemplateKey().equals("bigwheel")){
            GlobalVariableUtil.activityId_bigwheel = activityId;
            GlobalVariableUtil.poolId_bigwheel = poolId;
        }if (weimobActivityCreateIdCase.getTemplateKey().equals("scratch")){
            GlobalVariableUtil.activityId_scratch = activityId;
            GlobalVariableUtil.poolId_scratch = poolId;
        }

        Assert.assertEquals(responseData.getString("errcode"), "0");
        Assert.assertEquals(responseData.getString("errmsg"), "处理成功");
    }
}
