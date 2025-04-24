package com.example.server;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@RestController
public class MyGetMethod {

    /*
    获取get请求响应结果的cookies信息
     */
    @RequestMapping(value = "/getCookies", method = RequestMethod.GET)
    public String getCookies(HttpServletResponse response){

        Cookie cookie = new Cookie("login","true");
        response.addCookie(cookie);
        return "恭喜你获得cookies成功";
    }

    /*
    要求客户端携带cookies进行访问
     */
    @RequestMapping(value = "/get/withCookies",method = RequestMethod.GET)
    public String getWithCookies(HttpServletRequest request){
        Cookie[] cookies = request.getCookies();
        if(Objects.isNull(cookies)){
            return "必须携带cookies信息来访问";
        }
        for(Cookie cookie:cookies){
            if(cookie.getName().equals("login") && cookie.getValue().equals("true")){
                return "携带cookies信息访问成功";
            }
        }
        return "";
    }

    /**
     * 开发一个需要携带参数才能访问的get请求。
     * 第一种实现方式：url：ip:port/get/withparam/key=value&key=value
     * 模拟获取商品列表
     */
    @RequestMapping(value = "/get/withParamFirst",method = RequestMethod.GET)
    public Map<String,Integer> getListFirst(@RequestParam Integer start,@RequestParam Integer end){
        Map<String,Integer> myList = new HashMap<>();
        myList.put("鞋001",400);
        myList.put("裤子001",150);
        myList.put("衣服001",420);
        return myList;
    }

    /**
     * 第二种需要带参数访问的get请求。
     * 实现方式：url：ip:port/get/withparam/10/20
     */
    @RequestMapping(value = "/get/withParamSecond/{start}/{end}")
    public Map<String,Integer> getListSecond(@PathVariable Integer start,@PathVariable Integer end){

        Map<String,Integer> myList = new HashMap<>();
        myList.put("鞋001",400);
        myList.put("裤子001",150);
        myList.put("衣服001",420);
        return myList;
    }
}
