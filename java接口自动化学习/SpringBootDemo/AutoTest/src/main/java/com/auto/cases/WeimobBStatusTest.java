package com.auto.cases;

import com.alibaba.fastjson.JSON;
import com.auto.common.BaseTest;
import com.auto.common.common;
import com.auto.model.WeimobActivityCreateCase;
import com.auto.utils.GlobalVariableUtil;
import com.auto.utils.JsonMergeUtil;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import static com.auto.common.common.getResult;

public class WeimobBStatusTest extends BaseTest {

    public JSONObject commonParams(WeimobActivityCreateCase weimobActivityCreateCase) {
        String extendInfoData = weimobActivityCreateCase.getExtendInfo();
        String extendInfo = null;
        JSONObject params = new JSONObject();

        if (weimobActivityCreateCase.getTemplateKey().equals("bigwheel")) {
            params.put("activityId", GlobalVariableUtil.activityId_bigwheel);
            extendInfo = extendInfoData.replace("${activityId}", GlobalVariableUtil.activityId_bigwheel);
        }if(weimobActivityCreateCase.getTemplateKey().equals("scratch")){
            params.put("activityId", GlobalVariableUtil.activityId_scratch);
            extendInfo = extendInfoData.replace("${activityId}", GlobalVariableUtil.activityId_scratch);
        }

        params.put("templateKey", weimobActivityCreateCase.getTemplateKey());
        params.put("basicInfo", JSON.parseObject(weimobActivityCreateCase.getBasicInfo()));
        params.put("i18n", JSON.parseObject(weimobActivityCreateCase.getI18n()));
        params.put("extendInfo", JSON.parseObject(extendInfo));

        return params;
    }

    public JSONObject activityEditCaseParams(WeimobActivityCreateCase weimobActivityCreateCase) {

        String basicSettingData = weimobActivityCreateCase.getBasicSettings();
        String basicSetting = null;
        String prizeSetting = null;
        String ruleSetting = null;
        if (weimobActivityCreateCase.getTemplateKey().equals("bigwheel")) {
            basicSetting = basicSettingData.replace("${activityId}", GlobalVariableUtil.activityId_bigwheel);

            //从全局变量读取奖品id，并传入数据串中
            prizeSetting = common.replacePlaceholdersfromMap(weimobActivityCreateCase.getPrizeSettings(),
                    GlobalVariableUtil.prizeId_bigwheel);
            ruleSetting = common.replacePlaceholdersfromMap(weimobActivityCreateCase.getRuleSettings(),
                    GlobalVariableUtil.prizeId_bigwheel);
        }if(weimobActivityCreateCase.getTemplateKey().equals("scratch")){
            basicSetting = basicSettingData.replace("${activityId}", GlobalVariableUtil.activityId_scratch);

            //从全局变量读取奖品id，并传入数据串中
            prizeSetting = common.replacePlaceholdersfromMap(weimobActivityCreateCase.getPrizeSettings(),
                    GlobalVariableUtil.prizeId_scratch);
            ruleSetting = common.replacePlaceholdersfromMap(weimobActivityCreateCase.getRuleSettings(),
                    GlobalVariableUtil.prizeId_scratch);
        }

        JSONObject params = new JSONObject();
        params.put("themeCode", weimobActivityCreateCase.getThemeCode());
        params.put("basicSettings", JSON.parseObject(common.replacePlaceholdersfromMap(basicSetting,GlobalVariableUtil.getActivityTime())));
        params.put("prizeSettings", JSON.parseObject(prizeSetting));
        params.put("ruleSettings", JSON.parseObject(ruleSetting));

        return params;
    }

    public JSONObject stateParams(WeimobActivityCreateCase weimobActivityCreateCase){
        JSONObject params = new JSONObject();
        params.put("event",weimobActivityCreateCase.getEvent());

        return params;
    }

    public JSONObject createSnCodeParams(WeimobActivityCreateCase weimobActivityCreateCase){
        JSONObject params =  new JSONObject();
        params.put("generateWay",weimobActivityCreateCase.getGenerateWay());
        params.put("number",weimobActivityCreateCase.getNumber());
        params.put("prizeId",String.valueOf(System.currentTimeMillis()));
        return params;
    }


    //区分create、update、close、delete
    @DataProvider(name="activityCreateCaseData")
    public Iterator<WeimobActivityCreateCase> provideCreateDateFromDb() throws IOException {
        Map<String, Object> params = new HashMap<>();
        params.put("action","create");
        return new common().<WeimobActivityCreateCase>provideDataFromDb("weimobActivityCreateCase", WeimobActivityCreateCase.class, params);
    }

    @DataProvider(name="activityUpdateCaseData")
    public Iterator<WeimobActivityCreateCase> provideUpdateDateFromDb() throws IOException {
        Map<String, Object> params = new HashMap<>();
        params.put("action","update");
        return new common().<WeimobActivityCreateCase>provideDataFromDb("weimobActivityCreateCase", WeimobActivityCreateCase.class, params);
    }

    @Test(dataProvider = "activityCreateCaseData",dependsOnMethods = "com.auto.cases.WeimobBCreateIdTest.activityCreateId",description = "创建活动")
    public void create(WeimobActivityCreateCase weimobActivityCreateCase) throws IOException {

        //读取奖品id个数，将所有奖品id存入全局变量中
        if(weimobActivityCreateCase.getTemplateKey().equals("bigwheel")) {
            GlobalVariableUtil.prizeId_bigwheel = common.replacePlaceholdersToMap(weimobActivityCreateCase.getPrizeSettings(),"createPrizeId", GlobalVariableUtil.prizeId_bigwheel);
        }if(weimobActivityCreateCase.getTemplateKey().equals("scratch")){
            GlobalVariableUtil.prizeId_scratch = common.replacePlaceholdersToMap(weimobActivityCreateCase.getPrizeSettings(), "createPrizeId", GlobalVariableUtil.prizeId_scratch);
        }

        JSONObject createParam = JsonMergeUtil.mergeJson(commonParams(weimobActivityCreateCase),activityEditCaseParams(weimobActivityCreateCase));
        JSONObject responseData = getResult(createParam, createUrl);

        Assert.assertTrue(responseData.getBoolean("data"));
        Assert.assertEquals(responseData.getString("errmsg"), "处理成功");
    }

    @Test(dataProvider = "activityCreateCaseData",dependsOnMethods = "create",description = "活动创建成功后上架")
    public void state(WeimobActivityCreateCase weimobActivityCreateCase) throws IOException {

        JSONObject stateParam = JsonMergeUtil.mergeJson(commonParams(weimobActivityCreateCase),stateParams(weimobActivityCreateCase));
        JSONObject responseData = getResult(stateParam, stateUrl);

        Assert.assertTrue(responseData.getBoolean("data"));
        Assert.assertEquals(responseData.getString("errmsg"), "处理成功");
    }

//    @Test(dataProvider = "activityCreateCaseData", dependsOnMethods = "com.auto.cases.WeimobBCreateIdTest.activityCreateId", description = "生成虚拟奖品SN码")
//    public void createSnCode(WeimobActivityCreateCase weimobActivityCreateCase) throws IOException {
//        JSONObject createSnCodePeram = JsonMergeUtil.mergeJson(commonParams(weimobActivityCreateCase),createSnCodeParams(weimobActivityCreateCase));
//        JSONObject responseData = getResult(createSnCodePeram, createSnCodeUrl);
//
//        System.out.println(responseData);
//    }

    @Test(dataProvider = "activityUpdateCaseData",dependsOnMethods = "state",description = "编辑活动")
    public void update(WeimobActivityCreateCase weimobActivityCreateCase) throws IOException {

        //读取奖品id个数，将所有奖品id存入全局变量中
        if(weimobActivityCreateCase.getTemplateKey().equals("bigwheel")) {
            GlobalVariableUtil.prizeId_bigwheel = common.replacePlaceholdersToMap(weimobActivityCreateCase.getPrizeSettings(),"updatePrizeId", GlobalVariableUtil.prizeId_bigwheel);
        }if(weimobActivityCreateCase.getTemplateKey().equals("scratch")){
            GlobalVariableUtil.prizeId_scratch = common.replacePlaceholdersToMap(weimobActivityCreateCase.getPrizeSettings(), "updatePrizeId", GlobalVariableUtil.prizeId_scratch);
        }

        JSONObject updateParam = JsonMergeUtil.mergeJson(commonParams(weimobActivityCreateCase),activityEditCaseParams(weimobActivityCreateCase));
        JSONObject responseData = getResult(updateParam, updateUrl);
        Assert.assertTrue(responseData.getBoolean("data"));
        Assert.assertEquals(responseData.getString("errmsg"), "处理成功");
    }
}
