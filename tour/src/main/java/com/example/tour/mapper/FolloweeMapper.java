package com.example.tour.mapper;

import com.example.tour.entity.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface FolloweeMapper {
    @Insert("insert into followee(user_id,followee_id)values (#{userId},#{id})")
    void followee(String userId,String id);
    @Select("SELECT * from user where id in (select followee_id from followee where user_id=#{userId})")
    List<User> selectFollowee(String userId);
}
