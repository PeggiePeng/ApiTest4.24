package com.auto.utils;

import org.json.JSONObject;

public class JsonMergeUtil {

    /**
     * 合并 JSON 数据的公共方法
     * @param target 目标 JSONObject，合并后的数据会更新到这个对象中
     * @param source 源数据，可以是 JSONObject 或者 String 类型的 JSON 字符串
     * @return 合并后的 JSONObject
     */
    public static JSONObject mergeJson(JSONObject target, Object source) {
        if (source instanceof JSONObject) {
            // 如果源数据是 JSONObject 类型，直接合并
            target = mergeJsonObject(target, (JSONObject) source);
        } else if (source instanceof String) {
            try {
                // 如果源数据是 String 类型，尝试将其转换为 JSONObject 后再合并
                JSONObject sourceJson = new JSONObject((String) source);
                target = mergeJsonObject(target, sourceJson);
            } catch (Exception e) {
                System.err.println("无法将字符串转换为 JSON 对象: " + e.getMessage());
            }
        }
        return target;
    }

    /**
     * 合并两个 JSONObject 的方法
     * @param target 目标 JSONObject
     * @param source 源 JSONObject
     * @return 合并后的 JSONObject
     */
    private static JSONObject mergeJsonObject(JSONObject target, JSONObject source) {
        for (String key : source.keySet()) {
            Object value = source.get(key);
            if (target.has(key)) {
                Object existingValue = target.get(key);
                if (existingValue instanceof JSONObject && value instanceof JSONObject) {
                    // 如果两个值都是 JSONObject，递归合并
                    target.put(key, mergeJsonObject((JSONObject) existingValue, (JSONObject) value));
                } else {
                    // 否则，直接覆盖目标对象中的值
                    target.put(key, value);
                }
            } else {
                // 如果目标对象中不存在该键，直接添加
                target.put(key, value);
            }
        }
        return target;
    }
}
