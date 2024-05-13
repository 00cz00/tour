package com.example.tour.entity;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.Date;

@Data
public class User {
    private String id;
    private String username;
    private String password;
    private String email;
    private LocalDateTime createTime;
    private String url;
}
