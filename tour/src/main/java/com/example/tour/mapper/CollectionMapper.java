package com.example.tour.mapper;

import com.example.tour.entity.Collections;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.Collection;

@Mapper
public interface CollectionMapper {

    //根据文章id查询其收藏数
    @Select("select count(*) from tour.collection where article_id=#{id}")
     int countCollection(long id) ;
    @Select("select * from tour.collection where user_id=#{userId} and article_id=#{id}")
    Collections isCollected(String userId, long id);
}
