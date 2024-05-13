package com.example.tour.entity;

import lombok.Data;

import java.util.Date;

@Data
public class User {
    private String id;
    private String username;
    private String password;
    private String email;
    private Date createTime;
    private String url;
}
