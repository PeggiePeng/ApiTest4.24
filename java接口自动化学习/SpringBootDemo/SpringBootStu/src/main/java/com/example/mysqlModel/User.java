package com.example.mysqlModel;

import lombok.Data;

@Data
public class User {
    private String id;
    private String userName;
    private String sex;
    private int age;
    private String password;
    private String permission;
    private String isDelete;


    @Override
    public String toString(){
     return (
             "{id:"+id+","+
             "userName:"+userName+","+
             "sex:"+sex+","+
             "age:"+age+","+
             "password:"+password+","+
             "permission:"+permission+","+
             "isDelete:"+isDelete+"}"
             );
    }
}
