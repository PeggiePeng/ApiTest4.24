package com.auto.model;

import lombok.Data;

@Data
public class GetUserListCase {
    private String id;
    private String userName;
    private Integer  age;
    private String sex;
    private String exception;
}
