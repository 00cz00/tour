package com.example.tour.vo;

import com.example.tour.entity.User;
import lombok.Data;

@Data
public class UserLoginVO {
     private User user;
     private String jwt;
}
