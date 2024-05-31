package com.example.tour.service.impl;

import com.example.tour.dto.AdminSelectUser;
import com.example.tour.dto.AdminUserUpdateDTO;
import com.example.tour.dto.UserUpdateInfoDTO;
import com.example.tour.entity.Article;
import com.example.tour.entity.EmailProperties;
import com.example.tour.entity.User;
import com.example.tour.entity.VerificationCode;
import com.example.tour.mapper.*;
import com.example.tour.result.PageResult;
import com.example.tour.result.Result;
import com.example.tour.service.UserService;
import com.example.tour.utils.MailUtil;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.time.LocalDateTime;
import java.util.List;


@Service
public class UserServiceimpl implements UserService {
    @Autowired UserMapper userMapper;
    @Autowired
    FolloweeMapper followeeMapper;
    @Autowired
    private EmailProperties emailProperties;
    @Autowired
    ScenicSpotLikeMapper scenicSpotLikeMapper;
    @Autowired
    CommentMapper commentMapper;
    @Autowired
    ArticleMapper articleMapper;
    @Autowired
    ArticleLikeMapper articleLikeMapper;
    @Autowired
    CollectionMapper collectionMapper;
    @Autowired
    ArticleServiceimpl articleServiceimpl;

    @Override
    public User getUserById(String id) {
        return userMapper.getById(id);
    }

    @Override
    public User login( String email, String password) {
        String md5=DigestUtils.md5DigestAsHex(password.getBytes());
        return userMapper.login(email,md5);
    }

    @Override
    public void reg(String email, String password, String username,String url) {
        LocalDateTime now=LocalDateTime.now();
        String md5= DigestUtils.md5DigestAsHex(password.getBytes());
        userMapper.reg(email,md5,username,now,url);
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
        //删除与用户有关的所有东西
        userMapper.deleteUser(id);
        followeeMapper.deleteUser(id);
        scenicSpotLikeMapper.deleteByUser(id);
        commentMapper.deleteByUser(id);
        List<Article> articleList = articleMapper.selectMyArticle(id);
        for (Article a:
             articleList) {
            long id1 = a.getId();
            articleServiceimpl.delete(String.valueOf(id1));
        }
        articleMapper.deleteByUser(id);
        articleLikeMapper.deleteByUser(id);
        collectionMapper.deleteByUser(id);
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

    @Override
    public void userUpdateInfo(String userId, UserUpdateInfoDTO userUpdateInfoDTO) {
        userMapper.userUpdateInfo(userId,userUpdateInfoDTO);
    }

    @Override
    public void userUpdatePassword(String userId, String password) {
        String md5=DigestUtils.md5DigestAsHex(password.getBytes());
        userMapper.userUpdatePassword(userId,md5);
    }

    @Override
    public void AdminUserUpdate(AdminUserUpdateDTO adminUserUpdateDTO) {
        String md5Password = DigestUtils.md5DigestAsHex(adminUserUpdateDTO.getPassword().getBytes());
        adminUserUpdateDTO.setPassword(md5Password);
        userMapper.AdminUserUpdate(adminUserUpdateDTO);
    }

    @Override
    public PageResult selectUserByUsername(AdminSelectUser adminSelectUser) {
        /*int offset=(adminSelectUser.getPage()-1)*adminSelectUser.getPageSize();
        adminSelectUser.setOffset(offset);*/
        PageHelper.startPage(adminSelectUser.getPage(),adminSelectUser.getPageSize());
        Page<User> page= userMapper.selectUserByUserName(adminSelectUser);
        PageResult pageResult=new PageResult(page.getTotal(), page.getResult());
      return  pageResult;
    }

    @Override
    public int countAllUser() {
        int all=userMapper.countAll();
        return all;
    }
}
