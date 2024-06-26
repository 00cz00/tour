package com.example.tour.mapper;

import com.example.tour.entity.Followee;
import com.example.tour.entity.User;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface FolloweeMapper {
    @Insert("insert into followee(user_id,followee_id)values (#{userId},#{id})")
    void followee(String userId,String id);
    @Select("SELECT * from user where id in (select followee_id from followee where user_id=#{userId} )order by id desc")
    List<User> selectFollowee(String userId);
    @Delete("delete from followee where followee_id=#{id} and user_id=#{userId}")
    void deleteFollowee(String id,String userId);
    @Delete("delete from followee where followee_id=#{id} or user_id=#{id}")
    void deleteUser(String id);

    @Select("select * from tour.followee where user_id=#{userId} and followee_id=#{followeeId} ")
    Followee isFollowed(String userId, String followeeId);
}
