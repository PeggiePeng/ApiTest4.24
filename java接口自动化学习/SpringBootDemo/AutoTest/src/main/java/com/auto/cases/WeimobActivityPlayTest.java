package com.auto.cases;

import com.auto.common.BaseTest;
import com.auto.common.common;
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

import com.auto.data.DataForC;

public class WeimobActivityPlayTest extends BaseTest {

    @DataProvider(name="playCaseData")
    public Iterator<WeimobActivityPlayCase> provideDataFromDb() throws IOException {
        Map<String, Object> params = new HashMap<>();
        params.put("action","play");
        return new common().<WeimobActivityPlayCase>provideDataFromDb("weimobActivityPlayCase", WeimobActivityPlayCase.class, params);
    }

    @Test(dataProvider = "playCaseData")
    public void play(WeimobActivityPlayCase weimobActivityPlayCase) throws IOException {
        JSONObject requestData = JsonMergeUtil.mergeJson(DataForC.commonParam(weimobActivityPlayCase), DataForC.playParam(weimobActivityPlayCase));
        JSONObject responseData = common.getResult(requestData,playUrl);

        Assert.assertEquals(responseData.getString("errmsg"),"处理成功");
    }
}
