package com.auto.config;

import com.auto.model.WeimobInterfaceName;

import java.util.*;

public class GetApplicationData {

    //读取application.properties文件
    private static ResourceBundle bundle = ResourceBundle.getBundle("application", Locale.CHINA);

    //获取接口url
    public String address = bundle.getString("weimobqa.url");
    public String createIdUri=bundle.getString("createId.uri");
    public String createUri=bundle.getString("create.uri");
    public String stateUri=bundle.getString("state.uri");
    public String updateUri=bundle.getString("update.uri");
    public String createSnCodeUri=bundle.getString("createSnCode.uri");
    public String playUri=bundle.getString("play.uri");
    public String listWinnerByPageUri=bundle.getString("listWinnerByPage.uri");
    public String userList=bundle.getString("userList.uri");
    public String userPrizes=bundle.getString("userPrizes.uri");
    public String exchange=bundle.getString("exchange.uri");

    //获取店铺信息
    public static Map<String,Object> getShopInfo(){
        Map<String,Object> shopInfo = new HashMap<>();
        String appid = bundle.getString("appid");
        shopInfo.put("appId",appid);
        String vid = bundle.getString("vid");
        shopInfo.put("vid",vid);
        int vidType = Integer.parseInt(bundle.getString("vidType"));
        shopInfo.put("vidType", vidType);
        String bosId = bundle.getString("bosId");
        shopInfo.put("bosId",bosId);
        String merchantId = bundle.getString("merchantId");
        shopInfo.put("merchantId",merchantId);
        String cid = bundle.getString("cid");
        shopInfo.put("cid",cid);
        String openId = bundle.getString("openId");
        shopInfo.put("openId",openId);
        String wid = bundle.getString("wid");
        shopInfo.put("wid",wid);
        List<Integer> vidTypes = Collections.singletonList(Integer.valueOf(bundle.getString("vidType")));
        shopInfo.put("vidTypes", vidTypes);

        return shopInfo;
    }
}
