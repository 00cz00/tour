package com.example.tour.controller;

import com.example.tour.dto.AdminScenicSpotUpdateDTO;
import com.example.tour.dto.AdminSelectUser;
import com.example.tour.dto.AdminUserUpdateDTO;
import com.example.tour.entity.User;
import com.example.tour.mapper.CommentMapper;
import com.example.tour.result.Result;
import com.example.tour.service.ArticleService;
import com.example.tour.service.ScenicSpotService;
import com.example.tour.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class AdminController {
    @Autowired
    UserService userService;
    @Autowired
    private ArticleService articleServiceimpl;
    @Autowired
    ScenicSpotService scenicSpotService;
    @Autowired
    CommentMapper commentMapper;
    //用户删除
    @PostMapping("/deleteUser/{id}")
    public Result deleteUser(@PathVariable  String id){
        userService.deleteUser(id);
        return Result.success("删除成功");
    }


    //删除文章
    @PostMapping("/article/delete/{id}")
    public Result delete(@PathVariable String id){
        articleServiceimpl.delete(id);
        return Result.success("删除成功");
    }


    //删除景点
    @PostMapping("/deleteScenicSpot/{id}")
    public Result deleteScenicSpot(@PathVariable String id){
        scenicSpotService.delete(id);
        return Result.success("删除成功");
    }

    //删除评论
    @PostMapping("/deleteComment/{id}")
    public Result deleteComment(@PathVariable String id){
        commentMapper.deleteById(id);
        return Result.success("删除成功");
    }


    //修改用户
    @PostMapping("/AdminUserUpdate")
    public Result AdminUserUpdate(@RequestBody AdminUserUpdateDTO adminUserUpdateDTO){

        userService.AdminUserUpdate(adminUserUpdateDTO);
        return Result.success("修改成功");
    }



    //修改景点
    @PostMapping("/AdminScenicSpotUpdate")
    public Result AdminScenicSpotUpdate(@RequestBody AdminScenicSpotUpdateDTO adminScenicSpotUpdateDTO){

        scenicSpotService.AdminScenicSpotUpdate(adminScenicSpotUpdateDTO);
        return Result.success("修改成功");
    }

   //根据用户名模糊查询用户
    @GetMapping("/selectUserByUserName")
    public Result<List<User>> selectUserByUserName(@RequestBody AdminSelectUser adminSelectUser){
       List<User> userList=userService.selectUserByUserName(adminSelectUser);
       return Result.success(userList);
    }



}
