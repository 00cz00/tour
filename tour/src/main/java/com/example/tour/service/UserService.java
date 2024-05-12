package com.example.tour.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.tour.entity.UserEntity;

public interface UserService extends IService<UserEntity> {


    UserEntity getUserById(String id);
}
