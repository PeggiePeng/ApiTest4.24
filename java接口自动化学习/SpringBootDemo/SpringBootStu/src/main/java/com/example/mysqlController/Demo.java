package com.example.mysqlController;

import com.example.mysqlModel.UserModel;
import lombok.extern.log4j.Log4j2;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Log4j2
@RestController
@RequestMapping("v1")
public class Demo {
    //先获取衣蛾执行sql语句的对象
    @Autowired
    private SqlSessionTemplate template;

//    @RequestMapping(value = "/getUserCount",method = RequestMethod.GET)
//    public int getUserCount(){
//        return template.selectOne("getUserCount");
//    }
//
//    @RequestMapping(value = "/addUser",method = RequestMethod.POST)
//    public int addUser(@RequestBody UserModel user){
//
//        return template.insert("addUser",user);
//    }
//
//    //@RequestBody：接口传值以json格式
//    @RequestMapping(value = "/updateUser",method = RequestMethod.POST)
//    public int updateUser(@RequestBody UserModel user){
//
//        return template.update("updateUser",user);
//    }
//
//    //@RequestParam：接口传值以form-data格式
//    @RequestMapping(value = "/deleteUser",method = RequestMethod.POST)
//    public int delUser(@RequestParam int id){
//
//        return template.delete("deleteUser",id);
//    }
//
//    @RequestMapping(value = "/deleteUser1",method = RequestMethod.POST)
//    public int delUser1(@RequestBody UserModel user){
//
//        return template.delete("deleteUser1",user);
//    }

//    @RequestMapping(value = "/login",method = RequestMethod.POST)
//    public String login(@RequestParam int id){
//        return template.selectOne("loginCase");
//    }
}
