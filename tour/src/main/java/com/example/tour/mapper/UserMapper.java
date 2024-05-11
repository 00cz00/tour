package com.example.tour.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.tour.entity.UserEntity;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper extends BaseMapper<UserEntity> {

}
