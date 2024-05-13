package com.example.tour.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface FolloweeMapper {
    @Insert("insert into followee(user_id,followee_id)values (#{userId},#{id})")
    void followee(String userId,String id);
}
