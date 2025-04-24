
package com.example.server;

import com.alibaba.fastjson.JSONObject;
import com.example.common.CommonResponse;
import com.example.mysqlModel.User;
import jakarta.servlet.http.Cookie;
import org.mybatis.spring.SqlSessionTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/v1")
public class MyPostMethod {

    private static final Logger logger = LoggerFactory.getLogger(MyPostMethod.class);

    private static Cookie cookie;
    @Autowired
    private SqlSessionTemplate template;

    //模拟用户登陆成功获取到cookies，再访问其它接口获取到列表
    @RequestMapping(value="/login",method = RequestMethod.POST)
    public String login(@RequestBody User users){
        int getUserCount = template.selectOne("loginByUsernameAndPassword",users);
        if(getUserCount>=1){
            return "true";
        }
        return "false";
    }

    @RequestMapping(value = "/getUserList",method = RequestMethod.POST)
    public CommonResponse<JSONObject> getUserList(@RequestBody User users){
        try {
            logger.info("开始获取用户列表，传入参数: {}", users);

            int getUserlistCount = template.selectOne("getUserListCount", users);
            logger.info("获取用户列表数量结果: {}", getUserlistCount);
            List<Object> getUserList = template.selectList("getUserList", users);
            logger.info("获取用户列表结果: {}", getUserList);

            JSONObject userListResult = new JSONObject();
            userListResult.put("pageList",getUserList);
            userListResult.put("totalCount",getUserlistCount);

            return CommonResponse.success(userListResult);
        }catch (Exception e){
            logger.error("获取用户列表时发生错误", e);
            return CommonResponse.fail(500, "获取用户列表失败");
        }
    }

    @RequestMapping(value = "/addUser", method = RequestMethod.POST)
    public CommonResponse<JSONObject> addUser(@RequestBody User user){
        try {
            // 获取当前时间戳
            long timestamp = System.currentTimeMillis();
            // 将时间戳转换为字符串作为 ID
            String id = String.valueOf(timestamp);
            user.setId(id);
            logger.info("开始获取用户列表，传入参数: {}", user);

            int addUser = template.insert("addUser", user);
            logger.info("新增用户数量结果: {}", addUser);

            JSONObject addUserData = new JSONObject();
            addUserData.put("data", user);

            return CommonResponse.success(addUserData);
        }catch (Exception e){
            logger.error("获取用户列表时发生错误", e);
            return CommonResponse.fail(500, "新增用户失败");
        }
    }

}

