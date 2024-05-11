package com.example.tour.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("user")
public class UserEntity {
    private String id;
    private String username;
    private String password;
    private String email;
    private String createTime;
    private String url;
}
