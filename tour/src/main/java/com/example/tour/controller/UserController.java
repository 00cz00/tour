package com.example.tour.controller;

import com.example.tour.dto.UserLoginDTO;
import com.example.tour.dto.UserRegDTO;
import com.example.tour.entity.Article;
import com.example.tour.entity.User;
import com.example.tour.result.Result;
import com.example.tour.service.ArticleService;
import com.example.tour.service.UserService;
import com.example.tour.utils.JwtUtils;
import com.example.tour.vo.UserLoginVO;
import io.jsonwebtoken.Claims;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.http.HttpServletRequest;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@RestController
public class UserController {
    @Autowired
    UserService userService;
    @Autowired
    ArticleService articleService;
    //根据id查人
    @GetMapping("/userById")
    public User getUserById(String id){
       return userService.getUserById(id);
    }




    //注册
    @PostMapping("/reg")
    public Result<User> reg(@RequestBody UserRegDTO userRegDTO){
        User user=userService.login(userRegDTO.getEmail(),userRegDTO.getPassword());
        if(user!=null){
            return Result.error("账号密码重复");
        }
        if(userRegDTO.getEmail()==null||userRegDTO.getPassword()==null||userRegDTO.getUsername()==null||userRegDTO.getUrl()==null){
            return Result.error("请填写完整注册信息");
        }

        userService.reg(userRegDTO.getEmail(),userRegDTO.getPassword(),userRegDTO.getUsername(),userRegDTO.getUrl());
        User usernow=userService.login(userRegDTO.getEmail(),userRegDTO.getPassword());
        return Result.success(usernow);
    }




    //登录
    @PostMapping("/login")
    public Result<UserLoginVO> login(@RequestBody UserLoginDTO userLoginDTO){
        User user=userService.login(userLoginDTO.getEmail(),userLoginDTO.getPassword());
        System.out.println(user);
        if(user==null){
            System.out.println("账号密码错误");
            return Result.error("账号密码错误");
        }
        Map<String, Object> claims=new HashMap<>();
        claims.put("id",user.getId());
        claims.put("email",user.getEmail());
        claims.put("password",user.getPassword());
        claims.put("username",user.getUsername());
        claims.put("url",user.getUrl());
        String jwt = JwtUtils.createJwt(claims);
        UserLoginVO userLoginVO=new UserLoginVO();
        userLoginVO.setUser(user);
        userLoginVO.setJwt(jwt);
        return Result.success(userLoginVO);
    }





    //关注其他用户
    @PostMapping("/followee")
    public Result followee(ServletRequest servletRequest,String id){
        HttpServletRequest req=(HttpServletRequest) servletRequest;
        String jwt = req.getHeader("jwt");
        Claims claims = JwtUtils.parserJwt(jwt);
        String userId = (String) claims.get("id");
        userService.followee(userId,id);
        return Result.success("关注成功");
    }




    //查询用户关注的用户
    @GetMapping("/selectFollowee")
    public Result<List<User>> selectFollowee(ServletRequest servletRequest){
        HttpServletRequest req=(HttpServletRequest) servletRequest;
        String jwt = req.getHeader("jwt");
        Claims claims = JwtUtils.parserJwt(jwt);
        String userId = (String) claims.get("id");
       List<User> userList= userService.selectFollowee(userId);
        return Result.success(userList);
    }


    //取关用户
    @PostMapping("/deleteFollowee")
    public Result deleteFollowee(ServletRequest servletRequest,String id){
        HttpServletRequest req=(HttpServletRequest) servletRequest;
        String jwt = req.getHeader("jwt");
        Claims claims = JwtUtils.parserJwt(jwt);
        String userId = (String) claims.get("id");
        userService.deleteFollowee(id,userId);
        return Result.success("取关成功");
    }


    //用户删除
    @PostMapping("/deleteUser")
    public Result deleteUser(String id){
        userService.deleteUser(id);
        return Result.success("删除成功");
    }



    //查询用户关注的文章
    @GetMapping("/selectCollection")
    public Result<List<Article>> selectCollection(ServletRequest servletRequest){
        HttpServletRequest req=(HttpServletRequest) servletRequest;
        String jwt = req.getHeader("jwt");
        Claims claims = JwtUtils.parserJwt(jwt);
        String userId = (String) claims.get("id");
        List<Article> articleList=articleService.selectCollection(userId);
        return Result.success(articleList);
    }

    //查询我发布的文章
    @GetMapping("/selectMyArticle")
    public Result<List<Article>> selectMyArticle(ServletRequest servletRequest){
        HttpServletRequest req=(HttpServletRequest) servletRequest;
        String jwt = req.getHeader("jwt");
        Claims claims = JwtUtils.parserJwt(jwt);
        String userId = (String) claims.get("id");
        List<Article> articleList=articleService.selectMyArticle(userId);
        return  Result.success(articleList);
    }










    //关注文章
    @PostMapping("/collectArticle")
    public Result collectArticle(ServletRequest servletRequest,String id){
        HttpServletRequest req=(HttpServletRequest) servletRequest;
        String jwt = req.getHeader("jwt");
        Claims claims = JwtUtils.parserJwt(jwt);
        String userId = (String) claims.get("id");
        articleService.collectArticle(userId,id);
        return Result.success("关注文章成功");
    }




    //评论文章
    @PostMapping("/comment")
    public Result comment(String articleId,String content,ServletRequest servletRequest){
        HttpServletRequest req=(HttpServletRequest) servletRequest;
        String jwt = req.getHeader("jwt");
        Claims claims = JwtUtils.parserJwt(jwt);
        String userId = (String) claims.get("id");
        articleService.comment(articleId,content,userId);
        return  Result.success("评论成功");
    }
}
