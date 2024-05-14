package com.example.tour.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface CollectionMapper {

    //根据文章id查询其收藏数
    @Select("select count(*) from tour.collection where article_id=#{id}")
     int countCollection(long id) ;
}
