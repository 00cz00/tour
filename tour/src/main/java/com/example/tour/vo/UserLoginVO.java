package com.example.tour.vo;

import com.example.tour.entity.User;
import lombok.Data;

import java.io.Serializable;

@Data
public class UserLoginVO implements Serializable {
     private User user;
     private String jwt;
}
