package com.example.tour.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.tour.entity.UserEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface UserMapper extends BaseMapper<UserEntity> {
    @Select("select * from user where id=#{id}")
    UserEntity getById(String id);
}
