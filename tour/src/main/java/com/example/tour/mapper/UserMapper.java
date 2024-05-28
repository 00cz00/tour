package com.example.tour.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.tour.dto.UserLoginDTO;
import com.example.tour.dto.UserUpdateInfoDTO;
import com.example.tour.entity.User;
import org.apache.ibatis.annotations.*;

import java.time.LocalDateTime;
import java.util.List;

@Mapper
public interface UserMapper {
    @Select("select * from user where id=#{id}")
    User getById(String id);
    @Select("select * from user where email=#{email} and password=#{password}")
    User login(String email,String password);
    @Insert("insert into user(email,password,username,create_time,url)values (#{email},#{password},#{username},#{now},#{url})")
    void reg(String email, String password, String username, LocalDateTime now,String url);
    @Delete("delete from user where id=#{id}")
    void deleteUser(String id);

    @Select("select * from tour.user where email=#{email}")
    User getByEmail(String email);
    @Update("update user set username=#{userUpdateInfoDTO.username} ,url=#{userUpdateInfoDTO.url} where id=#{userId}")
    void userUpdateInfo(String userId, UserUpdateInfoDTO userUpdateInfoDTO);
    @Update("update user set password=#{password} where id=#{userId}")
    void userUpdatePassword(String userId, String password);
}
