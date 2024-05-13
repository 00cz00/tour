package com.example.tour.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.tour.dto.UserLoginDTO;
import com.example.tour.entity.User;
import com.example.tour.mapper.FolloweeMapper;
import com.example.tour.mapper.UserMapper;
import com.example.tour.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.time.LocalDateTime;


@Service
public class UserServiceimpl implements UserService {
    @Autowired UserMapper userMapper;
    @Autowired
    FolloweeMapper followeeMapper;

    @Override
    public User getUserById(String id) {
        return userMapper.getById(id);
    }

    @Override
    public User login( String email, String password) {
        return userMapper.login(email,password);
    }

    @Override
    public void reg(String email, String password, String username,String url) {
        LocalDateTime now=LocalDateTime.now();
        userMapper.reg(email,password,username,now,url);
    }

    @Override
    public void followee(String userId,String id) {
        followeeMapper.followee(userId,id);
    }
}
