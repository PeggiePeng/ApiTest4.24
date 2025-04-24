package com.auto.common;

import com.alibaba.fastjson.TypeReference;
import com.auto.config.TestConfig;
import com.auto.utils.DatabaseUtil;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.util.EntityUtils;
import org.apache.ibatis.session.SqlSession;
import org.json.JSONObject;
import org.testng.annotations.DataProvider;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.alibaba.fastjson.JSON;

public class common {

    //数据库取表取数的公共方法
    @DataProvider(name = "dbData")
    public <T> Iterator<T> provideDataFromDb(String sqlId, Class<T> clazz, Map<String, Object> params) throws IOException {
        SqlSession session = DatabaseUtil.getSqlSession();
        List<T> dataList = session.selectList(sqlId, params);
        return dataList.iterator();
    }

    //发起http请求并返回响应结果
    public static JSONObject getResult(JSONObject param, String url) throws IOException {

        HttpPost post = new HttpPost(url);
        post.setHeader("content-type","application/json");

        StringEntity entity = new StringEntity(param.toString(),"utf-8");
        post.setEntity(entity);

        String result;
        HttpResponse response = TestConfig.httpClient.execute(post);
        result = EntityUtils.toString(response.getEntity(),"utf-8");

        return new JSONObject(result);
    }

    //根据根据数据库Case表中的参数，通过连接数据库查询正确结果，得出期望结果list
    public static  <T> List<T> selectExpectedData(String sqlId, Class<T> clazz, Map<String, Object> params) throws IOException {
        Iterator<T> getUserListWithCase = new common().<T>provideDataFromDb(sqlId, clazz ,params);
        //将iterator类型的数据转化为list格式的数据
        List<T> exceptedResponse = new ArrayList<>();
        while (getUserListWithCase.hasNext()) {
            exceptedResponse.add(getUserListWithCase.next());
        }
        return exceptedResponse;
    }

    public static Map<String, Object> jsonToMap(JSONObject params){
        String jsonStr = params.toString();
        return JSON.parseObject(jsonStr, new TypeReference<Map<String, Object>>() {});
    }

    public static JSONObject listToJson(List<?> params) {
        String jsonStr = JSON.toJSONString(params);
        return JSON.parseObject(jsonStr, JSONObject.class);
    }

    public static String dateTimeFormat(int t){
        // 获取当前时间
        LocalDateTime currentTime = LocalDateTime.now();
        // 当前时间加t天
        LocalDateTime newTime = currentTime.plusDays(t);
        // 定义日期时间格式
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        // 将当前时间按照指定格式进行格式化
        return newTime.format(formatter);
    }

    public static JSONObject unionJsonObject(JSONObject JSONObject1,JSONObject JSONObject2){
        for (String key : JSONObject2.keySet()) {
            JSONObject1.put(key, JSONObject2.get(key));
        }
        return JSONObject1;
    }

    public static Map<String,String> replacePlaceholdersToMap(String input, String prizeIdStatus, Map<String,String> placeholdersMap) {
        // 检查 placeholdersMap 是否为 null
        if (placeholdersMap == null) {
            placeholdersMap = new HashMap<>();
        }

        String regex = "\\$\\{(" + Pattern.quote(prizeIdStatus) + "\\d)\\}";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(input);

        while (matcher.find()){
            String placeholder = matcher.group(1);

            long timestamp = System.currentTimeMillis();
            String timestampStr = String.valueOf(timestamp);

            placeholdersMap.put(placeholder, timestampStr);

            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        return placeholdersMap;
    }

    public static String replacePlaceholdersfromMap(String template, Map<String, String> placeholderMap) {
        String result = template;
        if (placeholderMap !=null) {
            for (Map.Entry<String, String> entry : placeholderMap.entrySet()) {
                String placeholder = "${" + entry.getKey() + "}";
                result = result.replace(placeholder, entry.getValue());
            }
        }
            return result;
    }
}
