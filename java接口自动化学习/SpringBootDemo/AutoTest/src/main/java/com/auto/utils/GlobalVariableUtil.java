package com.auto.utils;

import com.auto.common.common;

import java.util.HashMap;
import java.util.Map;

public class GlobalVariableUtil {

    public static String activityId_bigwheel="2000000060569";
    public static Long poolId_bigwheel;
    public static Map<String,String> prizeId_bigwheel;

    public static String activityId_scratch;
    public static Long poolId_scratch;
    public static Map<String,String> prizeId_scratch;

    public static Map<String,String> activityTime = new HashMap<>();;

    public static Map<String,String> getActivityTime(){
        if(activityTime.isEmpty()) {
            activityTime.put("startTime", common.dateTimeFormat(0));
            activityTime.put("endTime", common.dateTimeFormat(7));
        }
        return activityTime;
    }
}
