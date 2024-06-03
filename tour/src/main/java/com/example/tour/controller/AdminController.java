package com.example.tour.controller;

import com.example.tour.dto.*;
import com.example.tour.entity.Banner;
import com.example.tour.entity.ScenicSpot;
import com.example.tour.entity.ScenicSpotLike;
import com.example.tour.entity.User;
import com.example.tour.mapper.CommentMapper;
import com.example.tour.result.PageResult;
import com.example.tour.result.Result;
import com.example.tour.service.ArticleService;
import com.example.tour.service.BannerService;
import com.example.tour.service.ScenicSpotService;
import com.example.tour.service.UserService;
import com.example.tour.utils.JwtUtils;
import com.example.tour.vo.ArticlePageQueryVO;
import com.example.tour.vo.ScenicSpotVO;
import io.jsonwebtoken.Claims;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.http.HttpServletRequest;
import lombok.Data;
import org.apache.ibatis.annotations.Delete;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.convert.PeriodUnit;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    UserService userService;
    @Autowired
    private ArticleService articleServiceimpl;
    @Autowired
    ScenicSpotService scenicSpotService;
    @Autowired
    CommentMapper commentMapper;

    @Autowired
    private BannerService bannerService;
    @Autowired
    private RedisTemplate redisTemplate;
    //用户删除
    @DeleteMapping("/user/delete/{id}")
    public Result deleteUser(@PathVariable  String id){
        userService.deleteUser(id);
        return Result.success("删除成功");
    }

    //修改用户
    @PostMapping("/user/update")
    public Result AdminUserUpdate(@RequestBody AdminUserUpdateDTO adminUserUpdateDTO){

        userService.AdminUserUpdate(adminUserUpdateDTO);
        return Result.success("修改成功");
    }
    //根据id查询用户信息用于回显

    @GetMapping("/user/{id}")
    public Result<User> getById(@PathVariable String id){
        User user=userService.getUserById(id);
        return  Result.success(user);
    }

    //根据用户名模糊查询用户
    @PostMapping("/user/pageQuery")
    public Result<PageResult> selectUserByUserName(@RequestBody AdminSelectUser adminSelectUser){
        PageResult pageResult=userService.selectUserByUsername(adminSelectUser);
        return Result.success(pageResult);
    }

    //统计用户数量
   /* @GetMapping("/user/total")
    public Result countAllUser(){
        int total=userService.countAllUser();
        return Result.success(total);

    }*/
    //删除文章
    @DeleteMapping("/article/delete/{id}")
    public Result delete(@PathVariable String id){
        articleServiceimpl.delete(id);
        return Result.success("删除成功");
    }


    //管理端分页查询文章
    @PostMapping("/article/page")
    public Result<PageResult> page(@RequestBody AdminarticlePageQueryDTO adminarticlePageQueryDTO) {

        PageResult page = articleServiceimpl.adminArticlePage(adminarticlePageQueryDTO);

            return Result.success(page);
        }





    //删除评论
    @DeleteMapping("/comment/delete/{id}")
    public Result deleteComment(@PathVariable String id){
        commentMapper.deleteById(id);
        return Result.success("删除成功");
    }


    //删除景点
    @DeleteMapping("/scenicSpot/delete/{id}")
    public Result deleteScenicSpot(@PathVariable String id){
        scenicSpotService.delete(id);
        return Result.success("删除成功");
    }


    //修改景点
    @PostMapping("/scenicSpot/update")
    public Result AdminScenicSpotUpdate(@RequestBody AdminScenicSpotUpdateDTO adminScenicSpotUpdateDTO){

        scenicSpotService.AdminScenicSpotUpdate(adminScenicSpotUpdateDTO);
        return Result.success("修改成功");
    }


    //增加景点
    @PostMapping("/scenicSpot/add")
    public  Result addScenicSpot(@RequestBody AdminScenicSpotUpdateDTO adminScenicSpotUpdateDTO){

        scenicSpotService.add(adminScenicSpotUpdateDTO);
        return Result.success();

    }

    //景点分页查询
    @PostMapping("/scenicSpot/page")
    public Result<PageResult> adminScenicSpotPage(@RequestBody AdminScenicSpotPageDTO adminScenicSpotPageDTO){
       PageResult page=scenicSpotService.adminScenicSpotPage(adminScenicSpotPageDTO);
        return Result.success(page);
    }
    //统计全部景点

    public Result countAllScenicSpot(@PathVariable String provinceId){
        System.out.println("adasda"+provinceId);
        int total=scenicSpotService.countAll(provinceId);
        return Result.success(total);
    }

    //查询轮播图
    @Cacheable(cacheNames = "banner",key="")
    @GetMapping("/banner/select")
    public Result<List<Banner>> bannerSelect(){
        List<Banner> list= bannerService.bannerSelect();
        return Result.success(list);
    }

    //删除轮播图
   // @CacheEvict(cacheNames = "banner",allEntries = true)
    @DeleteMapping("/banner/delete/{id}")
    public Result deleteById(@PathVariable String id){
        bannerService.deleteById(id);
        /*Set<String> keys = redisTemplate.keys("ban*");
        for (String key : keys) {
            redisTemplate.delete(key);
        }*/
        return Result.success();
    }
    //添加轮播图
    @CacheEvict(cacheNames = "banner",allEntries = true)
    @PostMapping("/banner/add")
    public Result add(@RequestBody Banner banner){
        bannerService.add(banner);
        return Result.success();
    }




}
