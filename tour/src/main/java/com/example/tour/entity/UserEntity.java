package com.example.tour.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

@Data
public class UserEntity {
    private String id;
    private String username;
    private String password;
    private String email;
    private Date createTime;
    private String url;
}
