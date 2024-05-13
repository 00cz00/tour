package com.example.tour.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.tour.dto.UserLoginDTO;
import com.example.tour.entity.User;

public interface UserService  {


    User getUserById(String id);


    User login(String email, String password);

    void reg(String email, String password, String username,String url);

    void followee(String userId,String id);
}
