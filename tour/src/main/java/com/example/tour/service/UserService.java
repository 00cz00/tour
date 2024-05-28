package com.example.tour.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.tour.dto.UserLoginDTO;
import com.example.tour.dto.UserUpdateInfoDTO;
import com.example.tour.entity.User;
import com.example.tour.result.Result;

import java.util.List;

public interface UserService  {


    User getUserById(String id);


    User login(String email, String password);

    void reg(String email, String password, String username,String url);

    void followee(String userId,String id);

    List<User> selectFollowee(String userId);

    void deleteFollowee(String id,String userId);

    void deleteUser(String id);

     Result send(String email);


    User getByEmail(String email);

    void userUpdateInfo(String userId, UserUpdateInfoDTO userUpdateInfoDTO);

    void userUpdatePassword(String userId, String password);
}
