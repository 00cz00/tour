package com.example.tour.controller;

import com.example.tour.entity.ScenicSpot;
import com.example.tour.result.Result;
import com.example.tour.service.ArticleService;
import com.example.tour.service.ScenicSpotService;
import com.example.tour.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AdminController {
    @Autowired
    UserService userService;
    @Autowired
    private ArticleService articleServiceimpl;
    @Autowired
    ScenicSpotService scenicSpotService;
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
}
