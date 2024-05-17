package com.example.tour.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.tour.dto.UserLoginDTO;
import com.example.tour.entity.EmailProperties;
import com.example.tour.entity.User;
import com.example.tour.entity.VerificationCode;
import com.example.tour.mapper.FolloweeMapper;
import com.example.tour.mapper.UserMapper;
import com.example.tour.result.Result;
import com.example.tour.service.UserService;
import com.example.tour.utils.MailUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.tour.utils.VerificationCodeUtil;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;


@Service
public class UserServiceimpl implements UserService {
    @Autowired UserMapper userMapper;
    @Autowired
    FolloweeMapper followeeMapper;
    @Autowired
    private EmailProperties emailProperties;

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

    @Override
    public List<User> selectFollowee(String userId) {
       return followeeMapper.selectFollowee(userId);
    }

    @Override
    public void deleteFollowee(String id,String userId) {
        followeeMapper.deleteFollowee(id,userId);
    }

    @Override
    public void deleteUser(String id) {
        userMapper.deleteUser(id);
        followeeMapper.deleteUser(id);
    }


    public Result send(String email) {
        String code = com.example.tour.utils.VerificationCodeUtil.generateCode();
        try {
            MailUtil.sendMail(emailProperties, email, "图迹", "你的验证码是：" + code + "\n验证码在一分钟内有效\n请勿泄露给他人");
            return Result.success(new VerificationCode(code, LocalDateTime.now()));
        } catch (Exception e) {
            return Result.error(e.getMessage());
        }
    }

    @Override
    public User getByEmail(String email) {
        User user =userMapper.getByEmail(email);
        return user;
    }
}
