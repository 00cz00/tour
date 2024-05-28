package com.example.tour.mapper;

import com.example.tour.entity.Collections;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
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
    @Delete("delete from collection where article_id=#{id}")
    void articleDelete(String id);
    @Insert("insert into collection(user_id,article_id) values (#{userId},#{id})")
    void collectArticle(String userId, String id);

    @Delete("delete from tour.collection where user_id=#{userId} and article_id=#{articleId}")
    void abolishCollect(String userId, String articleId);
}
