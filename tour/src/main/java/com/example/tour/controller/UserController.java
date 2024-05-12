package com.example.tour.controller;

import com.example.tour.entity.UserEntity;
import com.example.tour.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.SQLOutput;


@RestController
public class UserController {
    @Autowired
    UserService userService;

    @GetMapping("/userById")
    public UserEntity getUserById(String id){

        System.out.println("");

       return userService.getUserById(id);

    }


}
