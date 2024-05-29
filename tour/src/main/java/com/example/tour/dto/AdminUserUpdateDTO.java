package com.example.tour.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class AdminUserUpdateDTO {
    private String id;
    private String username;
    private String password;
    private String email;
    private LocalDateTime createTime;
    private String url;
}
