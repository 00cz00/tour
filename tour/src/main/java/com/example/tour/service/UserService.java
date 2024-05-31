package com.example.tour.service;

import com.example.tour.dto.AdminSelectUser;
import com.example.tour.dto.AdminUserUpdateDTO;
import com.example.tour.dto.UserUpdateInfoDTO;
import com.example.tour.entity.User;
import com.example.tour.result.PageResult;
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

    void AdminUserUpdate(AdminUserUpdateDTO adminUserUpdateDTO);

    PageResult selectUserByUsername(AdminSelectUser adminSelectUser);

    int countAllUser();
}
