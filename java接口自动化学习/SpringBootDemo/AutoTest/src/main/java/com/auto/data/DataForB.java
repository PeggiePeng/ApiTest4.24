package com.auto.data;

import com.alibaba.fastjson.JSON;
import com.auto.common.common;
import com.auto.config.GetApplicationData;
import com.auto.model.WeimobActivityCreateCase;
import com.auto.utils.GlobalVariableUtil;
import org.json.JSONObject;

public class DataForB {

    public static JSONObject commonParams(WeimobActivityCreateCase weimobActivityCreateCase){
        String extendInfo = weimobActivityCreateCase.getExtendInfo();
        if(weimobActivityCreateCase.getTemplateKey().equals("bigwheel")){
            extendInfo = extendInfo.replace("${activityId}",GlobalVariableUtil.activityId_bigwheel);
        }
        String basicInfo = common.replacePlaceholdersfromMap(weimobActivityCreateCase.getBasicInfo(), GetApplicationData.getShopInfo());

        JSONObject param =new JSONObject();
        param.put("basicInfo", JSON.parseObject(basicInfo));
        param.put("extendInfo",JSON.parseObject(extendInfo));
        param.put("i18n",JSON.parseObject(weimobActivityCreateCase.getI18n()));

        return param;
    }

    public static String otherInfo(WeimobActivityCreateCase weimobActivityCreateCase){
        String otherInfo = weimobActivityCreateCase.getOtherInfo();

        if(weimobActivityCreateCase.getTemplateKey().equals("bigwheel")){
            otherInfo = otherInfo.replace("${activityId}", GlobalVariableUtil.activityId_bigwheel);
        }

        if (otherInfo != null) {
            otherInfo = common.replacePlaceholdersfromMap(otherInfo, GlobalVariableUtil.getActivityTime());
            otherInfo = common.replacePlaceholdersfromMap(otherInfo, GetApplicationData.getShopInfo());
        }

        return otherInfo;
    }
}
