package com.example.tour.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.tour.entity.UserEntity;
import com.example.tour.mapper.UserMapper;
import com.example.tour.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.Serializable;


@Service
public class UserServiceimpl extends ServiceImpl<UserMapper,UserEntity> implements UserService {
    @Autowired UserMapper userMapper;


    @Override
    public UserEntity getUserById(String id) {
        return userMapper.getById(id);
    }
}
