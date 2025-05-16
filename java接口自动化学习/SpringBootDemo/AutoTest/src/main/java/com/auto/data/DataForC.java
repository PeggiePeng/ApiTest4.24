package com.auto.data;

import com.alibaba.fastjson.JSON;
import com.auto.common.common;
import com.auto.config.GetApplicationData;
import com.auto.model.WeimobActivityPlayCase;
import com.auto.utils.GlobalVariableUtil;
import org.json.JSONObject;

public class DataForC {

    public static JSONObject commonParam(WeimobActivityPlayCase weimobActivityPlayCase){
        String basicInfo = common.replacePlaceholdersfromMap(weimobActivityPlayCase.getBasicInfo(), GetApplicationData.getShopInfo());
        String extendInfo = weimobActivityPlayCase.getExtendInfo();
        if(weimobActivityPlayCase.getTemplateKey().equals("bigwheel")){
            extendInfo =  extendInfo.replace("${activityId}", GlobalVariableUtil.activityId_bigwheel);
        }

        JSONObject params = new JSONObject();
        params.put("basicInfo", JSON.parseObject(basicInfo));
        params.put("i18n", JSON.parseObject(weimobActivityPlayCase.getI18n()));
        params.put("extendInfo", JSON.parseObject(extendInfo));

        return params;
    }

    public static String playParam(WeimobActivityPlayCase weimobActivityPlayCase){
        String otherInfo = weimobActivityPlayCase.getOtherInfo();

        if(weimobActivityPlayCase.getTemplateKey().equals("bigwheel")){
            otherInfo = otherInfo.replace("${activityId}", GlobalVariableUtil.activityId_bigwheel);
        }

        if (otherInfo != null) {
            otherInfo = common.replacePlaceholdersfromMap(otherInfo, GlobalVariableUtil.getActivityTime());
            otherInfo = common.replacePlaceholdersfromMap(otherInfo, GetApplicationData.getShopInfo());
        }

        return otherInfo;
    }
}
