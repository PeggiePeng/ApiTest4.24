package com.auto.model;

import lombok.Data;

@Data
public class UpdateUserInfoCase {
    private String id;
    private String userid;
    private String userName;
    private String sex;
    private int age;
    private String permission;
    private String isDelete;
    private String excepted;
}
